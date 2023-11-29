package Lab8;

import java.util.Arrays;

public class testNode {
    public static void main(String[] args) {
        Node node = new Node();
        Integer[] data = { 3, 2, 1, 4};
        node.addAll(Arrays.asList(data));

//		MinimaxAlgo algo = new MinimaxAlgo();
//		algo.execute(node);

        System.out.println(node.getSuccessors());
    }
}
