#!/usr/bin/env ruby
# Static validation; no network requests or file mutations.
require 'json'
require 'pathname'
root = File.expand_path('..', __dir__)
catalog = JSON.parse(File.read(File.join(__dir__, 'catalog.json'), encoding: 'UTF-8'))
errors = []
folders = Dir.children(root).select { |name| name.match?(/\A(?:0[1-9]|1[0-7]|18[ab]|19|20|21)-/) && File.directory?(File.join(root,name)) }.sort
expected_prefixes = (1..17).map { |n| format("%02d",n) } + %w[18a 18b 19 20 21]
errors << "Expected steps 01–21 with 18 split into 18a/18b" unless folders.map { |name| name.split("-",2).first } == expected_prefixes
# Alternate implementations share the original metadata and tests, without adding
# new exercise selections to the core roadmap's counts or duplicate inventory.
variant_folders = {'09a-monotonic-stacks-custom-template' => '09-monotonic-stacks'}
intentional_repeats = {
  'trapping-rain-water' => %w[03-two-pointers 09-monotonic-stacks],
  'merge-intervals' => %w[05-sorting-based-techniques 21-intervals],
  'non-overlapping-intervals' => %w[05-sorting-based-techniques 21-intervals],
  'insert-interval' => %w[16-greedy-and-intervals 21-intervals],
  'minimum-interval-to-include-each-query' => %w[16-greedy-and-intervals 21-intervals]
}
seen = {}
total = 0
new_total = 0
template_total = 0
(folders + variant_folders.keys).each do |folder|
  catalog_folder = variant_folders.fetch(folder, folder)
  variant = variant_folders.key?(folder)
  dir = File.join(root,folder)
  text = File.read(File.join(dir,'README.md'), encoding: 'UTF-8')
  entries = text.scan(/^## (\d+)\. \[([^\]]+)\]\((https:\/\/leetcode\.com\/[^)]+)\) — (Easy|Medium|Hard|Template)$/)
  expected = if folder.start_with?('06-')
    16
  elsif folder.start_with?('11-')
    8
  elsif folder.start_with?('12-')
    20
  elsif catalog_folder.start_with?('09-')
    18
  elsif folder.match?(/\A18[ab]-/)
    14
  elsif folder.start_with?('21-')
    10
  else
    10
  end
  errors << "#{folder}: expected #{expected} problems, got #{entries.length}" unless entries.length == expected
  errors << "#{folder}: broken numbering" unless entries.map { |e| e[0].to_i } == (1..expected).to_a
  %w[Keywords Structural Invariant Prerequisites].each { |term| errors << "#{folder}: missing recognition #{term}" unless text.include?(term) }
  errors << "#{folder}: missing when-not guidance" unless text.include?('When not to use it')
  files = Dir.glob(File.join(dir,'java','[0-9][0-9]-*.java')).sort
  errors << "#{folder}: Java count does not match problem count" unless files.length == expected
  entries.each_with_index do |entry,i|
    number,title,url,difficulty = entry
    template = difficulty == 'Template'
    slug = url.sub(%r{\Ahttps://leetcode.com/problems/},'').sub(%r{/\z},'')
    # Allow only the documented two-pointer/stack repeat and the four Intervals reviews.
    intentional_repeat = intentional_repeats[slug] == [seen[slug], folder]
    unless template || variant
      errors << "Duplicate problem #{slug}: #{seen[slug]} and #{folder}" if seen.key?(slug) && !intentional_repeat
      seen[slug] = folder
    end
    file = files[i]
    errors << "#{folder}/#{number}: missing/misnumbered Java" unless file && File.basename(file).start_with?(format('%02d-',i+1))
    if folder[0,2].to_i >= 8
      if template
        template_total += 1 unless variant
      else
        new_total += 1 unless variant
      end
      collection = template ? catalog.fetch('templates', []) : catalog['problems']
      meta = collection.find { |p| p['folder'] == catalog_folder && p['order'] == i+1 }
      if meta.nil?
        errors << "#{folder}/#{number}: missing catalog metadata"
        next
      end
      fields = {'title'=>title,'url'=>url}
      fields.merge!('difficulty'=>difficulty,'slug'=>slug) unless template
      fields.each do |key,value|
        errors << "#{folder}/#{number}: catalog #{key} mismatch" unless meta[key] == value
      end
      unless template
        errors << "#{folder}/#{number}: missing boolean access metadata" unless [true,false].include?(meta['premium'])
      end
      errors << "#{folder}/#{number}: Java filename mismatch" unless file && File.basename(file) == meta['java']
      if file
        java = File.read(file, encoding: 'UTF-8')
        if template
          errors << "#{file}: missing template title/link" unless java.include?("// #{title} — standalone template exercise") && java.include?(url)
          signature = /public\s+int\[\]\s+#{Regexp.escape(meta['method'])}\s*\(\s*int\[\]\s+[A-Za-z_$][A-Za-z0-9_$]*\s*\)/
          errors << "#{file}: missing template method" unless java.match?(signature)
          errors << "#{file}: standalone template must not have a LeetCode ID" if meta.key?('id')
        else
          errors << "#{file}: missing correct ID/link" unless java.include?("// #{meta['id']}. ") && java.include?(url)
        end
        errors << "#{file}: no submission class" unless java.match?(/class (Solution|MinStack|FreqStack|StockSpanner|LRUCache|LFUCache|Codec|BSTIterator|KthLargest|Twitter|MedianFinder|Trie|MapSum|WordDictionary|WordFilter|MyCalendar|MyCalendarTwo|MyCalendarThree)\b/)
      end
      test = File.join(root,'tests','cases',"#{catalog_folder.split("-",2).first}-#{format('%02d',i+1)}.java")
      errors << "#{folder}/#{number}: missing test case" unless File.file?(test)
      section = text.split(/^## \d+\. /)[i+1].to_s
      if folder.match?(/\A(?:18[ab]|21)-/)
        access = meta['premium'] ? '**Access:** LeetCode Premium' : '**Access:** Free problem'
        errors << "#{folder}/#{number}: access label mismatch" unless section.include?(access)
      end
      if folder.match?(/\A18[ab]-/)
        errors << "#{folder}/#{number}: missing selection provenance" unless section.include?('**Selection:**')
      end
      ['**Source:** '+meta['source'],'**Practice role:**','**Recognize it:**','**Solution:**','**Complexity:**','**Java:**','**Potential interviewer follow-ups:**'].each do |label|
        errors << "#{folder}/#{number}: missing #{label}" unless section.include?(label)
      end
      errors << "#{folder}/#{number}: fewer than two follow-ups" unless section.scan(/^- /).length >= 2
    end
  end
  total += entries.length unless variant
end
errors << "Expected 250 exercise entries, found #{total}" unless total == 250
errors << "Expected 4 standalone templates, found #{template_total}" unless template_total == 4
templates = catalog.fetch('templates', [])
expected_templates = [[1, 'Next Greater'], [2, 'Previous Greater'], [3, 'Next Smaller'], [4, 'Previous Smaller']]
errors << 'Incorrect standalone template catalog/order' unless templates.map { |t| [t['order'], t['title']] } == expected_templates && templates.all? { |t| t['folder'] == '09-monotonic-stacks' }
errors << "Expected 170 catalog entries, found #{new_total}" unless new_total == 170
errors << 'Catalog must have exactly 170 entries / 168 unique IDs' unless catalog['problems'].length == 170 && catalog['problems'].map { |p| p['id'] }.uniq.length == 168
errors << 'Catalog folder/order mappings must be unique' unless catalog['problems'].map { |p| [p['folder'], p['order']] }.uniq.length == catalog['problems'].length
errors << "Expected 241 unique problems, found #{seen.length}" unless seen.length == 241
links = 0
Dir.glob(File.join(root,'**','*.md')).each do |file|
  text = File.read(file, encoding: 'UTF-8')
  text.scan(/\[[^\]]*\]\(([^)]+)\)/).flatten.each do |target|
    next if target.match?(%r{\A(?:https?://|mailto:|#)})
    path = target.split('#',2).first
    next if path.nil? || path.empty?
    links += 1
    resolved = File.expand_path(path,File.dirname(file))
    errors << "#{file}: broken local link #{target}" unless File.exist?(resolved)
  end
end
if errors.empty?
  puts "PASS: 22 core pattern sections; 250 entries / 241 unique problems; 170 problem + 4 template catalog/Java/test mappings; 18 alternate implementations; #{links} local links."
else
  warn errors.join("\n")
  abort "#{errors.length} static validation error(s)"
end
