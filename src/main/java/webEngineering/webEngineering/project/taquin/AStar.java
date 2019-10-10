package webEngineering.webEngineering.project.taquin;

import java.util.List;
import java.util.PriorityQueue;

import webEngineering.webEngineering.project.taquin.euristicFunction.HeuristicFunction;

public class AStar {
	
	private PriorityQueue<NPuzzle> pQueue = new PriorityQueue<NPuzzle>(new NPuzzleComparator());
	
	private int[][] goalState;
	
	private int maxBoardInMemory = 0;
	
	private int totalBoardDuringRun = 0;
	
	HeuristicFunction heuristic;
	
	
	public AStar(NPuzzle puzzle, int[][] goalState, HeuristicFunction heuristic) {
		puzzle.setDistance(0);
		this.goalState = goalState;
		pQueue.add(puzzle);
		this.heuristic = heuristic;
	}
	
	public NPuzzle resolve() {

		while (pQueue.size() > 0) {
			
			NPuzzle currentPuzzle = pQueue.remove();
			setMaxBoardInMemory(getMaxBoardInMemory() - 1);
			for (int i = 0; i < currentPuzzle.getDimension(); i++) {
				for (int j = 0; j < currentPuzzle.getDimension(); j++) {
					System.out.print(currentPuzzle.getBoard()[i][j] + " ");
				}
				System.out.println("");
			}
			System.out.println("");

			if (currentPuzzle.isAs(goalState)) {
				return currentPuzzle;
			}
			List<NPuzzle> children = currentPuzzle.visit();
			for (NPuzzle child: children) {
				child.setDistance(child.getDistance() + this.heuristic.estimate(child, goalState));
				pQueue.add(child);
				setMaxBoardInMemory(getMaxBoardInMemory() + 1);
				setTotalBoardDuringRun(getTotalBoardDuringRun() + 1);
			}
		}
		return null;
	}

	public int getTotalBoardDuringRun() {
		return totalBoardDuringRun;
	}

	public void setTotalBoardDuringRun(int totalBoardDuringRun) {
		this.totalBoardDuringRun = totalBoardDuringRun;
	}

	public int getMaxBoardInMemory() {
		return maxBoardInMemory;
	}

	public void setMaxBoardInMemory(int maxBoardInMemory) {
		this.maxBoardInMemory = maxBoardInMemory;
	}
	
}
