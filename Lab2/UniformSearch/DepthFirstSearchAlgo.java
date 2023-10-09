package Lab2.UniformSearch;

import java.util.*;

public class DepthFirstSearchAlgo implements ISearchAlgo {
    @Override
    public Node execute(Node root, String goal) {
        Queue<Node> frontier = new PriorityQueue<Node>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.getLabel().compareTo(o2.getLabel());
            }
        });
        List<Node> explore = new ArrayList<>();

        frontier.add(root);
        while (!frontier.isEmpty()) {
            Node current = frontier.poll();
            if (current.getLabel().equals(goal)) {
                return current;
            }
            explore.add(current);

            for (Edge edge : current.getChildren()) {
                Node child = edge.getEnd();
                if (!explore.contains(child)) {
                    child.setParent(current);
                    explore.add(child);
                    frontier.add(child);
                }
            }
        }
        return null;
    }


    @Override
    public Node execute(Node root, String start, String goal) {
        Stack<Node> frontier = new Stack<>();
        List<Node> explored = new ArrayList<>();
        Node startNode = findNode(root, start);
        if (startNode == null) {
            return null; // Start node not found
        }

        frontier.push(startNode);
        while (!frontier.isEmpty()) {
            Node current = frontier.pop();
            if (current.getLabel().equals(goal)) {
                return current;
            }
            explored.add(current);

            for (Edge edge : current.getChildren()) {
                Node child = edge.getEnd();
                if (!explored.contains(child) && !frontier.contains(child)) {
                    child.setParent(current);
                    frontier.push(child);
                }
            }
        }
        return null; // Goal node not found
    }

    private Node findNode(Node root, String label) {
        if (root.getLabel().equals(label)) {
            return root;
        }
        for (Edge edge : root.getChildren()) {
            Node foundNode = findNode(edge.getEnd(), label);
            if (foundNode != null) {
                return foundNode;
            }
        }
        return null;
    }
}
