package webEngineering.application.project.taquin.algo;

import webEngineering.application.form.NPuzzleForm;
import webEngineering.application.project.taquin.NPuzzle;
import webEngineering.application.project.taquin.NPuzzleComparator;
import webEngineering.application.project.taquin.euristicFunction.HeuristicFunction;

import java.util.PriorityQueue;

public abstract class NPuzzleResolverImp implements NPuzzleResolver {

    protected PriorityQueue<NPuzzle> pQueue;

    protected NPuzzle solvedPuzzle;

    protected int[][] goalState;

    protected int maxBoardInMemory;

    protected int totalBoardDuringRun;

    protected HeuristicFunction heuristic;

    protected int currentBoardInMemory;

    public NPuzzleResolverImp(NPuzzleForm form) {

        NPuzzle puzzle = new NPuzzle(form.getDimension());
        puzzle.setBoard(form.getInitialBoard());
        pQueue = new PriorityQueue<>(new NPuzzleComparator());
        pQueue.add(puzzle);
        this.goalState = form.getExpectedBoard();
        this.heuristic = form.getHeuristicFunction();
        currentBoardInMemory = 0;
        totalBoardDuringRun = 0;
        maxBoardInMemory = 0;
    }

    public NPuzzleResolverImp(NPuzzle puzzle, int[][] goalState, HeuristicFunction heuristic) {

        this.goalState = goalState;
        pQueue = new PriorityQueue<>(new NPuzzleComparator());
        pQueue.add(puzzle);
        this.heuristic = heuristic;
        currentBoardInMemory = 0;
        totalBoardDuringRun = 0;
        maxBoardInMemory = 0;
    }

    public abstract NPuzzle resolve();

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

    public void setGoalState(int[][] goalState) {
        this.goalState = goalState;
    }

    public int getTotalBoardDuringRun() {
        return totalBoardDuringRun;
    }

    public void setTotalBoardDuringRun(int totalBoardDuringRun) {
        this.totalBoardDuringRun = totalBoardDuringRun;
    }

    public int getCurrentBoardInMemory() {
        return currentBoardInMemory;
    }

    public void setCurrentBoardInMemory(int currentBoardInMemory) {
        this.currentBoardInMemory = currentBoardInMemory;
    }
}
