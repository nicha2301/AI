package Lab5;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class AStar implements IPuzzleAlgo {

	PriorityQueue<Node> frontier = new PriorityQueue<>(PuzzleUtils.HeuristicComparatorByF);
	List<Node> explored = new ArrayList<>();

	@Override
	public Node execute(Puzzle model) {
		Node goalState = model.getGoalState();
		Node init = model.getInitialState();
		int count = 0;
		if (init.equals(goalState))
			return init;
		init.setG(count);
		init.setH(model.computeH1(init));
		frontier.add(init);
		while (!frontier.isEmpty()) {
			Node currentState = frontier.poll();
			if (currentState.equals(goalState)) {
				return currentState;
			} else {
				count += 1;
				explored.add(currentState);
				List<Node> resumeState = model.getSuccessors(currentState);
				for (Node state : resumeState) {
					if (!frontier.contains(state) && !explored.contains(state)) {
						state.setH(model.computeH1(state));
						state.setG(count);
						frontier.add(state);
					}
				}
			}
		}
		return null;
	}
}
