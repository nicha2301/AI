package Lab8;

import java.util.Collections;
import java.util.List;

public class MiniMaxSearchAlgo implements ISearchAlgo {

    // function MINIMAX-DECISION(state) returns an action
    // inputs: state, current state in game
    // v <- MAX-VALUE(state)
    // return the action in SUCCESSORS(state) with value v
    @Override
    public void execute(Node node) {
        // Enter your code here
        int v = maxValue(node);
        System.out.println(v);
    }

    // function MAX-VALUE(state) returns a utility value
    // if TERMINAL-TEST(state) then return UTILITY(state)
    // v <- Integer.MIN_VALUE
    // for each s in SUCCESSORS(state) do
    // v <- MAX(v, MIN-VALUE(s))
    // return v
    public int maxValue(Node node) {
        // Enter your code here
        if (node.isTerminal()) return 0;
        int v = Integer.MIN_VALUE;
        List<Node> sucessors = Node.getSuccessors();
        for (Node s : sucessors) {
            v = Math.max(v, minValue(s));
        }
        return v;
    }
    // function MIN-VALUE(state) returns a utility value
    // if TERMINAL-TEST(state) then return UTILITY(state)
    // v <- Integer.MAX_VALUE
    // for each s in SUCCESSORS(state) do
    // v <- MIN(v, MAX-VALUE(s))
    // return v

    public int minValue(Node node) {
        // Enter your code here
        if (node.isTerminal()) return 1;
        int v = Integer.MAX_VALUE;
        List<Node> sucessors = Node.getSuccessors();
        for (Node s : sucessors) {
            v = Math.min(v, maxValue(s));
        }
        return v;
    }
}
