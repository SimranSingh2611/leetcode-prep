# Java Solutions — 10 — Linked Lists

Compile/submit each file independently: most use LeetCode's `Solution` class; design problems retain their required class names. Alternative classes are clearly named and must be renamed to `Solution` when submitted instead. Keep only the selected implementation if the judge requires it.

`TreeNode`, `ListNode`, and problem-specific `Node` types are supplied by LeetCode. Local equivalents exist only in `../../tests/Models.java`; do not paste those into the judge. All examples use Java 8-compatible language/library features. Read each problem's input-mutation and auxiliary-space notes. Recursion space includes the call stack; skewed structures can exhaust Java's stack.

1. [Reverse Linked List](01-ReverseLinkedList.java) — Solution iterative; RecursiveSolution alternative. Both mutate links.
2. [Merge Two Sorted Lists](02-MergeTwoSortedLists.java) — Solution reuses and relinks input nodes.
3. [Linked List Cycle](03-LinkedListCycle.java) — Solution is the optimized Floyd method; no mutation.
4. [Remove Nth Node From End of List](04-RemoveNthFromEnd.java) — Solution mutates one link; n is valid per the statement.
5. [Reorder List](05-ReorderList.java) — Solution mutates links and returns void.
6. [Add Two Numbers](06-AddTwoNumbers.java) — Solution preserves both input lists.
7. [Copy List with Random Pointer](07-CopyListRandomPointer.java) — Solution preserves input throughout; InterleavingSolution temporarily mutates and restores it.
8. [LRU Cache](08-LRUCache.java) — LRUCache design class; explicit doubly linked list.
9. [Reverse Nodes in k-Group](09-ReverseNodesKGroup.java) — Solution mutates links, not node values.
10. [LFU Cache](10-LFUCache.java) — LFUCache design class; linked-order buckets plus hashing.

