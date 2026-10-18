/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd map each original node object to exactly one clone before exploring its neighbors.
 * - BFS then copies each edge through that map, preventing repeated clones and infinite
 *   traversal around cycles.
 * Time complexity: O(V + E) expected for reachable vertices V and edges E.
 * Space complexity: O(V) auxiliary map/queue, plus O(V + E) cloned output.
 */

// 133. Clone Graph
// https://leetcode.com/problems/clone-graph/
// NeetCode 150: https://neetcode.io/problems/clone-graph/question?list=neetcode150
// Solution uses IdentityHashMap to make object identity explicit. LeetCode supplies Node with val
// and neighbors; input is unchanged.
import java.util.*;

class Solution {
    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }
        Map<Node, Node> copies = new IdentityHashMap<>();
        ArrayDeque<Node> queue = new ArrayDeque<>();
        copies.put(node, new Node(node.val));
        queue.offer(node);
        while (!queue.isEmpty()) {
            Node original = queue.poll();
            for (Node neighbor : original.neighbors) {
                if (!copies.containsKey(neighbor)) {
                    copies.put(neighbor, new Node(neighbor.val));
                    queue.offer(neighbor);
                }
                copies.get(original).neighbors.add(copies.get(neighbor));
            }
        }
        return copies.get(node);
    }
}
