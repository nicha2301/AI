package Lab7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GA_NQueenAlgo {
    public static final int POP_SIZE = 100;//Population size
    public static final double MUTATION_RATE = 0.03;
    public static final int MAX_ITERATIONS = 1000;
    List<Node> population = new ArrayList<Node>();
    Random rd = new Random();

    // initialize the individuals of the population
    public void initPopulation() {
        for (int i = 0; i < POP_SIZE; i++) {
            Node ni = new Node();
            ni.generateBoard();
            population.add(ni);
        }
    }

    public Node execute() {
        initPopulation();

        for (int generation = 0; generation < MAX_ITERATIONS; generation++) {
            List<Node> newPopulation = new ArrayList<>();

            for (int i = 0; i < POP_SIZE; i++) {
                // Select parents using tournament or random selection
                Node parent1 = getParentByRandomSelection();
                Node parent2 = getParentByRandomSelection();

                // Perform crossover to create a child
                Node child = reproduce(parent1, parent2);

                // Perform mutation on the child
                if (rd.nextDouble() < MUTATION_RATE) mutate(child);
                if (child.getH() == 0) return child;

                // Add the child to the new population
                newPopulation.add(child);
            }

            // Replace the old population with the new one
            population = newPopulation;
        }
        Collections.sort(population);
        // Return the best solution in the final population
        return population.get(0);
    }

    // Mutate an individual by selecting a random Queen and
    //move it to a random row.
    public void mutate(Node node) {
        int queenIndex = rd.nextInt(Node.N); // Select a random queen
        int newRow = rd.nextInt(Node.N); // Select a random row

        // Mutate the selected queen's position
        node.setRow(queenIndex, newRow);
    }

    //Crossover x and y to reproduce a child
    public Node reproduce(Node x, Node y) {
        Node re = new Node();
        int c = rd.nextInt(Node.N);
        re.generateBoard();
        for (int i = 0; i < c; i++) {
            re.setRow(i, x.getRow(i));
        }
        for (int i = c; i < Node.N; i++) {
            re.setRow(i, y.getRow(i));
        }
        return re;
    }

    // Select K individuals from the population at random and
    //select the best out of these to become a parent.
    public Node getParentByTournamentSelection() {
        int tournamentSize = 5; // Adjust the tournament size as needed
        Node bestParent = null;
        for (int i = 0; i < tournamentSize; i++) {
            Node contender = population.get(rd.nextInt(POP_SIZE));
            if (bestParent == null || contender.getH() < bestParent.getH()) {
                bestParent = contender;
            }
        }
        return bestParent;
    }

    //Select a random parent from the population
    public Node getParentByRandomSelection() {
        return population.get(rd.nextInt(POP_SIZE));
    }
}
