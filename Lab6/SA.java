package Lab6;

public class SA {
    public Node execute(Node innitialNode) throws CloneNotSupportedException {
        Node current = innitialNode, neighbor = null;
        int T = 1000;
        while (current.getH() != 0) {
            if (T == 0) break;
            neighbor = current.selectNextRandomCandidate();
            int deltaE = neighbor.getH() - current.getH();
            if (deltaE < 0) {
                current = neighbor;
            } else {
                if (Math.exp(deltaE / T) > 0) {
                    current = neighbor;
                }
            }
        }
        return current;
    }
}
