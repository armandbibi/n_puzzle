package webEngineering.application.project.taquin.algo;

import webEngineering.application.form.NPuzzleForm;
import webEngineering.application.project.taquin.NPuzzle;
import webEngineering.application.project.taquin.euristicFunction.HeuristicFunction;

import java.util.List;

public class AStar extends NPuzzleResolverImp {

	public AStar(NPuzzle puzzle, int[][] goalState, HeuristicFunction heuristic) {
		super(puzzle, goalState, heuristic);
	}

	public AStar(NPuzzleForm form) {
		super(form);
	}

	public NPuzzle resolve() {

		while (pQueue.size() > 0) {

			currentBoardInMemory--;
			NPuzzle currentPuzzle = pQueue.remove();
			if (currentPuzzle.isAs(goalState)) {
				solvedPuzzle = currentPuzzle;
				return currentPuzzle;
			}
			List<NPuzzle> children = currentPuzzle.visit();
			for (NPuzzle child: children) {
				child.setDistance(child.getDistance() + this.heuristic.estimate(child, goalState));
				pQueue.add(child);
				currentBoardInMemory++;
				if(currentBoardInMemory > maxBoardInMemory)
					maxBoardInMemory++;
				totalBoardDuringRun++;
			}
		}
		return null;
	}
}
