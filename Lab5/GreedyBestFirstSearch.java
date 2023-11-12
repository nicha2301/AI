package Lab5;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class GreedyBestFirstSearch implements IPuzzleAlgo {

	PriorityQueue<Node> frontier = new PriorityQueue<>(PuzzleUtils.HeuristicComparatorByH);
	List<Node> explored = new ArrayList<>();

//	@Override
//	public Node execute(Puzzle model) {
//		Node goalState = model.getGoalState();
//		Node init = model.getInitialState();
//		int count = 0;
//		if (init.equals(goalState))
//			return init;
//		init.setH(model.computeH1(init));
//		frontier.add(init);
//		while (!frontier.isEmpty()) {
//			System.out.println("So lan lap: " + count);
//			Node currentState = frontier.poll();
//			System.err.println(currentState.getH());
//			System.out.println(currentState);
//			if (currentState.equals(goalState)) {
//				return currentState;
//			} else {
//				explored.add(currentState);
//				List<Node> resumeState = model.getSuccessors(currentState);
//				for (Node state : resumeState) {
//					count += 1;
//					if (!frontier.contains(state) && !explored.contains(state)) {
//						state.setH(model.computeH1(state));
//						frontier.add(state);
//					}
//					System.out.println(state.getH());
//					System.out.println(state);
//				}
//			}
//			System.out.println("------------------");
//		}
//		return null;
//	}
	
	
	
	@Override
	public Node execute(Puzzle model) {
		Node goalState = model.getGoalState();
		Node init = model.getInitialState();
		int count = 0;
		if (init.equals(goalState))
			return init;
		init.setH(model.computeH1(init));
		frontier.add(init);
		while (!frontier.isEmpty()) {
			System.out.println("So lan lap: " + count);
			Node currentState = frontier.poll();
			System.err.println(currentState.getH());
			System.out.println(currentState);
			if (currentState.equals(goalState)) {
				return currentState;
			} else {
				explored.add(currentState);
				List<Node> resumeState = model.getSuccessors(currentState);
				for (Node state : resumeState) {
					count += 1;
					if (!frontier.contains(state) && !explored.contains(state)) {
						state.setH(model.computeH2(state));
						frontier.add(state);
					}
					System.out.println(state.getH());
					System.out.println(state);
				}
			}
			System.out.println("------------------");
		}
		return null;
	}

}
