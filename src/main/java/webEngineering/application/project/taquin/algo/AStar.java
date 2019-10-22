package webEngineering.application.project.taquin.algo;

import webEngineering.application.form.NPuzzleForm;
import webEngineering.application.project.taquin.NPuzzle;
import webEngineering.application.project.taquin.euristicFunction.HeuristicFunction;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AStar extends NPuzzleResolverImp {

	public Set<Integer> closeList = new HashSet<>();

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
				int h = this.heuristic.estimate(child, this);
				child.setDistance(child.getDistance() + h);
				child.setHeuristicDistance(h);
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

