package Lab6;


public class HillClimbing {

    public Node execute(Node innitialNode) throws CloneNotSupportedException {
        Node current = innitialNode, neighbor = null;
        while (current.getH() > 0) {
            neighbor = current.getBestCandidate();
            if (current.getH() > neighbor.getH()) {
                current = neighbor;
            } else {
                System.out.println("H: " + current.getH());
                return current;
            }
        }
        System.out.println("H: " + current.getH());
        return current;
    }

    public Node executeUntilOK(Node innitialNode) throws CloneNotSupportedException {
        Node current = innitialNode, neighbor = null;
        while (current.getH() != 0) {
            while (current.getH() > 0) {
                neighbor = current.getBestCandidate();
                if (current.getH() > neighbor.getH()) {
                    current = neighbor;
                } else {
                    break;
                }
            }
            current = new Node();
        }
        System.out.println("H: " + current.getH());
        return current;
    }

}