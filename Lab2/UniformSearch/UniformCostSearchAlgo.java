package Lab2.UniformSearch;

import java.util.*;

public class UniformCostSearchAlgo implements ISearchAlgo {
    @Override
    public Node execute(Node root, String goal) {
        Set<Node> explored = new HashSet<>();
        Queue<Node> frontier = new PriorityQueue<Node>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return Double.compare(o1.getPathCost(), o2.getPathCost());
            }

        });
        frontier.add(root);

        while (!frontier.isEmpty()) {
            Node currentNode = frontier.poll();
            if (currentNode.getLabel().equals(goal)) {
                return currentNode;
            }
            explored.add(currentNode);

            for (Edge edge : currentNode.getChildren()) {
                Node child = edge.getEnd();
                if (!explored.contains(child) && !frontier.contains(child)) {
                    child.setPathCost(currentNode.getPathCost() + edge.getWeight());
                    child.setParent(currentNode);
                    frontier.add(child);
                } else if (frontier.contains(child) && child.getPathCost() > currentNode.getPathCost() + edge.getWeight()) {
                    child.setPathCost(currentNode.getPathCost() + edge.getWeight());
                    child.setParent(currentNode);
                }
            }
        }
        return null;
    }

    @Override
    public Node execute(Node root, String start, String goal) {
        Set<Node> explored = new HashSet<>();
        PriorityQueue<Node> frontier = new PriorityQueue<>(Comparator.comparingDouble(Node::getPathCost));
        Node startNode = findNode(root, start);
        if (startNode == null) {
            return null;
        }

        frontier.add(startNode);

        while (!frontier.isEmpty()) {
            Node currentNode = frontier.poll();
            if (currentNode.getLabel().equals(goal)) {
                return currentNode;
            }
            explored.add(currentNode);
            for (Edge edge : currentNode.getChildren()) {
                Node child = edge.getEnd();
                if (!explored.contains(child) && !frontier.contains(child)) {
                    child.setPathCost(currentNode.getPathCost() + edge.getWeight());
                    child.setParent(currentNode);
                    frontier.add(child);
                } else if (frontier.contains(child) && child.getPathCost() > currentNode.getPathCost() + edge.getWeight()) {
                    child.setPathCost(currentNode.getPathCost() + edge.getWeight());
                    child.setParent(currentNode);
                    frontier.remove(child);
                    frontier.add(child);
                }
            }
        }
        return null;
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
