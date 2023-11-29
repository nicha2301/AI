package Lab8;

import java.util.*;

public class Node {
    private String label;
    private int value;
    private List<Node> children = new ArrayList<Node>();
    private static List<Integer> data = new ArrayList<Integer>();

    // use for non-terminal node
    public Node() {
        super();
        this.label = label;
    }

    // use for terminal node
    public Node(String label, int value) {
        super();
        this.label = label;
        this.value = value;
    }

    public void add(Integer val) {
        this.data.add(val);
    }

    public void addAll(List<Integer> data) {
        this.data.addAll(data);
    }


    public static List<Node> getSuccessors() {

        Set<Node> child = new HashSet<Node>();
        Collections.sort(data, Collections.reverseOrder());

        for (int i = 0; i < data.size(); i++) {
            if (data.get(i) <= 2) break;
            for (int j = 1; j <= data.get(i) / 2; j++) {
                if (j == data.get(i) - j) continue;
                Node e = new Node();
                e.add(j);
                e.add(data.get(i) - j);

                if (i == 0) e.addAll(data.subList(i + 1, data.size()));
                if (i == data.size() - 1) e.addAll(data.subList(0, i - 1));
                if (i != 0) {
                    e.addAll(data.subList(0, i));
                    e.addAll(data.subList(i + 1, data.size()));
                }
                child.add(e);
            }
        }

        return List.copyOf(child);
    }

    public String getLabel() {
        return label;
    }

    public List<Node> getChildren() {
        return children;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    // add a child to this node
    public void addChild(Node that) {
        this.children.add(that);
    }

    // check whether this node is terminal or not. The terminal node is assigned a
    // value.
    public boolean isTerminal() {
        return this.children.size() == 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Node))
            return false;
        Node that = (Node) obj;
        return this.getLabel().equals(that.getLabel());
    }

    @Override
    public String toString() {
        return this.label;
    }

    // Defined comparator which is used for sorting children by alphabetical order
    public static Comparator<Node> LabelComparator = new Comparator<Node>() {
        @Override
        public int compare(Node o1, Node o2) {
            return o1.getLabel().compareTo(o2.getLabel());
        }
    };
}
