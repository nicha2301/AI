package Lab6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Node {
    public static final int N = 8;
    private Queen[] state;

    public Node() {
        // generateBoard();
        state = new Queen[N];
    }

    public Node(Queen[] state) {
        this.state = new Queen[N];
        for (int i = 0; i < state.length; i++) {
            this.state[i] = new Queen(state[i].getRow(), state[i].getColumn());
        }
    }

    public Node(Node n) {
        this.state = new Queen[N];
        for (int i = 0; i < N; i++) {
            Queen qi = n.state[i];
            this.state[i] = new Queen(qi.getRow(), qi.getColumn());
        }
    }

    public void generateBoard() {
        Random random = new Random();
        for (int i = 0; i < N; i++) {
            state[i] = new Queen(random.nextInt(N), i);
        }
    }

    public int getH() {
        int heuristic = 0;
        for (int i = 0; i < N - 1; i++)
            for (int j = i + 1; j < N; j++)
                if (state[i].isConflict(state[j])) heuristic++;
        return heuristic;
    }

    public List<Node> generateAllCandidates() throws CloneNotSupportedException {
        List<Node> result = new ArrayList<Node>();
        Queen[] stateNew;
        for (int i = 0; i < state.length; i++) {
            stateNew = copyState();
            stateNew[i].move();
            result.add(new Node(stateNew));
        }
        return result;
    }

    public Queen[] copyState() throws CloneNotSupportedException {
        Queen[] stateNew = new Queen[state.length];
        for (int i = 0; i < state.length; i++) {
            stateNew[i] = (Queen) state[i].clone();
        }
        return stateNew;
    }

    public Node selectNextRandomCandidate() throws CloneNotSupportedException {
        Random rd = new Random();
        Queen[] stateNew = copyState();
        Queen q = stateNew[rd.nextInt(stateNew.length)];
        int num = rd.nextInt(stateNew.length);
        for (int i = 0; i < num; i++)
            q.move();
        return new Node(stateNew);
    }

    public Node getBestCandidate() throws CloneNotSupportedException {
        List<Node> list = generateAllCandidates();
        Node res = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if (res.getH() > list.get(i).getH()) {
                res = list.get(i);
            }
        }
        return res;
    }

    public void displayBoard() {
        int[][] board = new int[N][N];
        // set queen position on the board
        for (int i = 0; i < N; i++) {
            board[state[i].getRow()][state[i].getColumn()] = 1;
        }
        // print board
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 1) {
                    System.out.print("Q" + " ");
                } else {
                    System.out.print("-" + " ");
                }
            }
            System.out.println();
        }
    }
}
