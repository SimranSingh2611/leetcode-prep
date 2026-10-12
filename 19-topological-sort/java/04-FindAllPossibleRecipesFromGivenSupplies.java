/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd map each ingredient to the recipes waiting for it and count their missing ingredients.
 * - Available supplies enter a queue; completing a recipe makes its name available to unlock
 *   later recipes.
 * Time complexity: Expected O(I + R + S) for ingredient references I, recipes R and supplies S,
 *   with bounded-length names.
 * Space complexity: O(I + R + S) auxiliary dependency/count/queue storage, plus up to O(R)
 *   output.
 */

// 2115. Find All Possible Recipes from Given Supplies
// https://leetcode.com/problems/find-all-possible-recipes-from-given-supplies/
// Solution preserves inputs. The queue starts with actual supplies, not every graph node of
// indegree zero.
import java.util.*;

class Solution {
    public List<String> findAllRecipes(
            String[] recipes, List<List<String>> ingredients, String[] supplies) {
        Map<String, List<Integer>> waiting = new HashMap<>();
        int[] missing = new int[recipes.length];
        for (int i = 0; i < recipes.length; i++) {
            missing[i] = ingredients.get(i).size();
            for (String ingredient : ingredients.get(i)) {
                waiting.computeIfAbsent(ingredient, k -> new ArrayList<>()).add(i);
            }
        }
        ArrayDeque<String> queue = new ArrayDeque<>();
        Set<String> available = new HashSet<>();
        for (String supply : supplies) {
            if (available.add(supply)) {
                queue.offer(supply);
            }
        }
        List<String> out = new ArrayList<>();
        for (int i = 0; i < recipes.length; i++) {
            if (missing[i] == 0) {
                out.add(recipes[i]);
                if (available.add(recipes[i])) {
                    queue.offer(recipes[i]);
                }
            }
        }
        while (!queue.isEmpty()) {
            String item = queue.poll();
            for (int recipe : waiting.getOrDefault(item, Collections.emptyList())) {
                if (--missing[recipe] == 0) {
                    out.add(recipes[recipe]);
                    if (available.add(recipes[recipe])) {
                        queue.offer(recipes[recipe]);
                    }
                }
            }
        }
        return out;
    }
}
