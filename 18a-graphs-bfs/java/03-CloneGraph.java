// 133. Clone Graph
// https://leetcode.com/problems/clone-graph/
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
