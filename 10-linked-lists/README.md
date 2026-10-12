# 10 — Linked Lists

Practice reference identity and pointer rewiring. The final cache designs combine hashing with linked ordering; they are not simply array simulations.

## How to recognize this pattern

**Keywords / hints:** “head”, “next”, “in-place reversal”, “nth from end”, “cycle”, “random pointer”, “O(1) insert/remove”, “least recently used”.

**Structural test:** Must you change links without random indexing, find relative positions in one pass, or remove a known node while preserving an order?

**Invariant / mental model:** Save the next link before overwriting it. Define exactly which prefix is processed and which references still reach the unprocessed suffix. Dummy nodes remove head-edge cases.

**When not to use it:** If random access dominates, an array may be better. A singly linked list cannot remove an arbitrary known node in O(1) when its predecessor is unavailable.

**Prerequisites:** Two pointers and stacks. Recursive reversal is optional and references the next folder; the primary implementation is iterative.

Keywords suggest a candidate technique; the structural test and constraints decide whether it actually works.

## Practice progression

Solve in numbered order. 3 Easy / 5 Medium / 2 Hard. The mix reflects genuine pattern fit rather than a forced quota. Hard exercises are stretch work; revisit them after the required supporting topics.

[Java solution index](./java/README.md) · [Full roadmap](../README.md) · [Recognition guide](../PATTERN_RECOGNITION.md)

## 1. [Reverse Linked List](https://leetcode.com/problems/reverse-linked-list/) — Easy

**Source:** Blind 75 + NeetCode 150 · **Practice role:** Core list selection

**Recognize it:** Reverse links in place while keeping access to the remaining suffix.

**Solution:** Maintain previous/current/next. Save current.next, reverse the link toward previous, then advance. The recursive alternative reverses the suffix and attaches the current node behind it; the iterative version avoids the linear call stack.

**Complexity:** Both O(n) time; iterative O(1) versus recursive O(n) auxiliary space.

**Java:** [ReverseLinkedList](./java/01-ReverseLinkedList.java) — Solution iterative; RecursiveSolution alternative. Both mutate links.

**Potential interviewer follow-ups:**

- How do you reverse only a sublist? Preserve its predecessor and reconnect both boundaries.
- Why set head.next=null recursively? Otherwise a two-node cycle remains.

## 2. [Merge Two Sorted Lists](https://leetcode.com/problems/merge-two-sorted-lists/) — Easy

**Source:** Blind 75 + NeetCode 150 · **Practice role:** Core list selection

**Recognize it:** Two sorted chains can be merged by repeatedly linking their smaller current head.

**Solution:** Use a dummy head and tail. Link the smaller node, advance only that input, and move the tail. Attach the entire remaining suffix once one input ends.

**Complexity:** O(n+m) time; O(1) auxiliary space.

**Java:** [MergeTwoSortedLists](./java/02-MergeTwoSortedLists.java) — Solution reuses and relinks input nodes.

**Potential interviewer follow-ups:**

- What if inputs must remain independent? Allocate fresh result nodes.
- How does merging k lists differ? Use a heap or divide-and-conquer; your existing folder 05 has that exercise.

## 3. [Linked List Cycle](https://leetcode.com/problems/linked-list-cycle/) — Easy

**Source:** Blind 75 + NeetCode 150 · **Practice role:** Core list selection

**Recognize it:** Following next references may revisit the same node identity.

**Solution:** A set records visited nodes directly. Floyd's method uses slow/fast pointers: inside a cycle their relative gap changes by one each step, so they eventually meet. Reaching null proves the list terminates.

**Complexity:** Both O(n) time; HashSetSolution O(n) space, Solution O(1) space.

**Java:** [LinkedListCycle](./java/03-LinkedListCycle.java) — Solution is the optimized Floyd method; no mutation.

**Potential interviewer follow-ups:**

- How do you find the cycle entry? Reset one meeting pointer to head and advance both one step.
- Why compare node identities rather than values? Distinct nodes may carry the same value.

## 4. [Remove Nth Node From End of List](https://leetcode.com/problems/remove-nth-node-from-end-of-list/) — Medium

**Source:** Blind 75 + NeetCode 150 · **Practice role:** Core list selection

**Recognize it:** Maintain a fixed gap so one pointer reaches the end when another reaches the removal predecessor.

**Solution:** Start both pointers at a dummy node and advance fast by n+1 links. Move both until fast is null. Slow then precedes the nth node from the end, including when that node is the head.

**Complexity:** O(length) time; O(1) auxiliary space.

**Java:** [RemoveNthFromEnd](./java/04-RemoveNthFromEnd.java) — Solution mutates one link; n is valid per the statement.

**Potential interviewer follow-ups:**

- Why use a dummy? Removing the original head follows the same operation.
- What if n could be invalid? Validate fast-pointer advancement explicitly.

## 5. [Reorder List](https://leetcode.com/problems/reorder-list/) — Medium

**Source:** Blind 75 + NeetCode 150 · **Practice role:** Core list selection

**Recognize it:** Alternate nodes from the front and back of a singly linked list without indexing.

**Solution:** Find the midpoint with slow/fast pointers, split the list, reverse its second half, then weave the two halves. Splitting first prevents the old middle link from creating a cycle.

**Complexity:** O(n) time; O(1) auxiliary space.

**Java:** [ReorderList](./java/05-ReorderList.java) — Solution mutates links and returns void.

**Potential interviewer follow-ups:**

- Why not repeatedly search for the last node? That is quadratic.
- What happens for odd length? The first half retains the extra middle node.

## 6. [Add Two Numbers](https://leetcode.com/problems/add-two-numbers/) — Medium

**Source:** NeetCode 150 · **Practice role:** Core list selection

**Recognize it:** Digits arrive least-significant first as linked nodes, requiring a carry across positions.

**Solution:** Walk both lists while either has digits or a carry remains. Add present digits and carry, append sum%10, and carry sum/10. Do not parse whole numbers into a primitive: list length can exceed its capacity.

**Complexity:** O(max(n,m)) time; O(1) auxiliary space excluding the new output list.

**Java:** [AddTwoNumbers](./java/06-AddTwoNumbers.java) — Solution preserves both input lists.

**Potential interviewer follow-ups:**

- If digits are most-significant first? Use stacks or reverse copies.
- If output must reuse nodes? Track ownership and be explicit about input mutation.

## 7. [Copy List with Random Pointer](https://leetcode.com/problems/copy-list-with-random-pointer/) — Medium

**Source:** NeetCode 150 · **Practice role:** Core list selection

**Recognize it:** A deep copy must preserve arbitrary cross-links by node identity.

**Solution:** First map every original node to a clone, then wire clone next/random links through that map. The optimized method inserts clones beside originals, uses original.random.next to locate random clones, then separates the two lists and restores the input.

**Complexity:** O(n) expected time and O(n) auxiliary map for Solution; O(n) time and O(1) auxiliary space for InterleavingSolution, excluding clones.

**Java:** [CopyListRandomPointer](./java/07-CopyListRandomPointer.java) — Solution preserves input throughout; InterleavingSolution temporarily mutates and restores it.

**Potential interviewer follow-ups:**

- Why not map by value? Values need not be unique.
- When is interleaving unsafe? When other readers can observe the temporarily modified list.

## 8. [LRU Cache](https://leetcode.com/problems/lru-cache/) — Medium

**Source:** NeetCode 150 · **Practice role:** Core list selection

**Recognize it:** O(1) lookup plus moving/removing known keys in recency order.

**Solution:** A hash map locates each entry's doubly linked node. Sentinel head/tail nodes bound an order from most to least recent. Move accessed/updated entries to the front; when full, evict the tail predecessor from both the list and map.

**Complexity:** O(1) expected get/put; O(capacity) space.

**Java:** [LRUCache](./java/08-LRUCache.java) — LRUCache design class; explicit doubly linked list.

**Potential interviewer follow-ups:**

- Why a doubly linked list? Removing a known node needs its predecessor in O(1).
- Can LinkedHashMap simplify implementation? Yes, with access-order enabled and controlled eviction.

## 9. [Reverse Nodes in k-Group](https://leetcode.com/problems/reverse-nodes-in-k-group/) — Hard

**Source:** NeetCode 150 · **Practice role:** Core list selection

**Recognize it:** Reverse fixed-size linked blocks, leaving a short final block untouched.

**Solution:** Use a dummy predecessor and first locate the kth node. If absent, stop without mutating that block. Save the block's successor, reverse links until that successor, reconnect the predecessor, and move it to the old block head (now the tail).

**Complexity:** O(n) time; O(1) auxiliary space.

**Java:** [ReverseNodesKGroup](./java/09-ReverseNodesKGroup.java) — Solution mutates links, not node values.

**Potential interviewer follow-ups:**

- Why find kth before reversing? The final incomplete block must stay unchanged.
- For alternating reversed blocks? Keep the same boundaries and toggle whether to reverse.

## 10. [LFU Cache](https://leetcode.com/problems/lfu-cache/) — Hard

**Source:** Supplemental · **Practice role:** Advanced stretch

**Recognize it:** Evict by minimum usage count, breaking ties with recency, while retaining O(1) operations.

**Solution:** Map keys to value/frequency records and frequencies to insertion-ordered key sets. LinkedHashSet supplies linked recency order within each bucket. Access moves a key to the next frequency's newest position. Track the minimum nonempty frequency; evict its oldest key when full.

**Complexity:** O(1) expected get/put; O(capacity) space after removing empty buckets.

**Java:** [LFUCache](./java/10-LFUCache.java) — LFUCache design class; linked-order buckets plus hashing.

**Potential interviewer follow-ups:**

- Why isn't one global LRU list sufficient? Frequency takes priority over recency.
- Does updating an existing value increase its frequency? Yes, as required by this problem.

