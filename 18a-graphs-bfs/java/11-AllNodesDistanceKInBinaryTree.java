/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd first record parent links so the tree can be traversed in both directions.
 * - A BFS from the target over child and parent links leaves exactly the distance-k nodes after
 *   k layers.
 * Time complexity: O(n) for n nodes.
 * Space complexity: O(n) auxiliary parent/visited/queue storage, plus up to O(n) output.
 */

// 863. All Nodes Distance K in Binary Tree
// https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/
// Solution preserves the tree and uses the actual target node reference, not just its value.
import java.util.*;

class Solution {
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        Map<TreeNode, TreeNode> parent = new IdentityHashMap<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        parent.put(root, null);
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.left != null) {
                parent.put(node.left, node);
                queue.offer(node.left);
            }
            if (node.right != null) {
                parent.put(node.right, node);
                queue.offer(node.right);
            }
        }
        Set<TreeNode> seen = Collections.newSetFromMap(new IdentityHashMap<TreeNode, Boolean>());
        queue.offer(target);
        seen.add(target);
        for (int distance = 0; distance < k && !queue.isEmpty(); distance++) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                for (TreeNode next : new TreeNode[] {node.left, node.right, parent.get(node)}) {
                    if (next != null && seen.add(next)) {
                        queue.offer(next);
                    }
                }
            }
        }
        List<Integer> result = new ArrayList<>();
        for (TreeNode node : queue) {
            result.add(node.val);
        }
        return result;
    }
}
