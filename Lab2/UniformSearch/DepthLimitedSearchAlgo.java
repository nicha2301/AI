package Lab2.UniformSearch;

import java.util.HashSet;
import java.util.Set;

public class DepthLimitedSearchAlgo implements ISearchAlgo{

    @Override
    public Node execute(Node root, String goal) {
        return null;
    }

    @Override
    public Node execute(Node root, String start, String goal) {
        return null;
    }

    public Node execute(Node root, String goal, int limitedDepth) {
        return depthLimitedSearch(root, goal, limitedDepth, new HashSet<>());
    }

    private Node depthLimitedSearch(Node node, String goal, int depthLimit, Set<Node> explored) {
        if (node.getLabel().equals(goal)) {
            return node; // Goal node found
        }
        if (depthLimit <= 0) {
            return null; // Reached depth limit, cannot continue
        }
        explored.add(node);

        for (Edge edge : node.getChildren()) {
            Node child = edge.getEnd();
            if (!explored.contains(child)) {
                Node result = depthLimitedSearch(child, goal, depthLimit - 1, explored);
                if (result != null) {
                    return result; // Goal node found in the child subtree
                }
            }
        }
        return null; // Goal node not found within depth limit
    }
}
