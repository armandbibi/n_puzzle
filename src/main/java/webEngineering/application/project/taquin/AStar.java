package webEngineering.application.project.taquin;

import webEngineering.application.form.NPuzzleForm;
import webEngineering.application.project.taquin.euristicFunction.HeuristicFunction;

import java.util.List;
import java.util.PriorityQueue;

public class AStar {
	
	private PriorityQueue<NPuzzle> pQueue;

	private NPuzzle solvedPuzzle;

	private int[][] goalState;
	
	private int maxBoardInMemory = 0;
	
	private int totalBoardDuringRun = 0;
	
	HeuristicFunction heuristic;
	
	
	public AStar(NPuzzle puzzle, int[][] goalState, HeuristicFunction heuristic) {

		this.goalState = goalState;
		pQueue = new PriorityQueue<>(new NPuzzleComparator());
		pQueue.add(puzzle);
		this.heuristic = heuristic;
	}

	public AStar(int [][] initialBoard, int[][] goalBoard, HeuristicFunction function, int m) {

		NPuzzle puzzle = new NPuzzle(m);
		puzzle.setBoard(initialBoard);
		pQueue = new PriorityQueue<>(new NPuzzleComparator());
		pQueue.add(puzzle);
		this.goalState = goalBoard;
		this.heuristic = function;

	}

	public AStar(NPuzzleForm form) {

		NPuzzle puzzle = new NPuzzle(form.getDimension());
		puzzle.setBoard(form.getInitialBoard());
		pQueue = new PriorityQueue<>(new NPuzzleComparator());
		pQueue.add(puzzle);
		this.goalState = form.getExpectedBoard();
		this.heuristic = form.getHeuristicFunction();
	}

	public NPuzzle resolve() {

		while (pQueue.size() > 0) {
			
			NPuzzle currentPuzzle = pQueue.remove();
			if (currentPuzzle.isAs(goalState)) {
				solvedPuzzle = currentPuzzle;
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

	public NPuzzle getSolvedPuzzle() {
		return solvedPuzzle;
	}

	public void setSolvedPuzzle(NPuzzle solvedPuzzle) {
		this.solvedPuzzle = solvedPuzzle;
	}
}
