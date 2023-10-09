package Lab2.UniformSearch;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BreadthFirstSearchAlgo implements ISearchAlgo {
    @Override
    public Node execute(Node root, String goal) {
        Queue<Node> frontier = new LinkedList<Node>();
        frontier.add(root);
        List<Node> explored = new ArrayList<Node>();

        while (!frontier.isEmpty()) {
            Node currentNode = frontier.poll();
            if (currentNode.getLabel().equals(goal)) return currentNode;
            else {
                explored.add(currentNode);
            }
            List<Node> children = currentNode.getChildrenNodes();
            for (Node child : children) {
                if (!explored.contains(child) && !frontier.contains(child)) {
                    frontier.add(child);
                    child.setParent(currentNode);
                }
            }
        }
        return null;
    }

    @Override
    public Node execute(Node root, String start, String goal) {
        Queue<Node> frontier = new LinkedList<>();
        frontier.add(root);
        List<Node> explored = new ArrayList<>();

        while (!frontier.isEmpty()) {
            Node currentNode = frontier.poll();
            if (currentNode.getLabel().equals(goal)) {
                return currentNode;
            }
            explored.add(currentNode);

            List<Node> children = currentNode.getChildrenNodes();
            for (Node child : children) {
                if (!explored.contains(child) && !frontier.contains(child)) {
                    frontier.add(child);
                    child.setParent(currentNode);
                    if (child.getLabel().equals(goal)) {
                        return child;
                    }
                }
            }
        }

        return null;
    }
}
