package Lab4;


import java.util.*;

public class GreedySearchAlgo implements IInformedSearchAlgo {

    class NodeComparatorByHn implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            Double h1 = o1.getH();
            Double h2 = o2.getH();
            int result = h1.compareTo(h2);
            if (result == 0)
                return o1.getLabel().compareTo(o2.getLabel());
            else
                return result;
        }
    }


    @Override
    public Node execute(Node root, String goal) {
        List<Node> explored = new ArrayList<Node>();
        Queue<Node> frontier = new PriorityQueue<Node>(new NodeComparatorByHn());
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
                    child.setG(currentNode.getG() + edge.getWeight());
                    child.setParent(currentNode);
                    frontier.add(child);
                }
            }
        }
        return null;
    }

    @Override
    public Node execute(Node root, String start, String goal) {
        Set<Node> explored = new HashSet<>();
        Queue<Node> frontier = new PriorityQueue<Node>(new NodeComparatorByHn());
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
                    child.setG(currentNode.getG() + edge.getWeight());
                    child.setParent(currentNode);
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
