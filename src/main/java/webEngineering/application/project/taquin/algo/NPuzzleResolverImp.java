package webEngineering.application.project.taquin.algo;

import webEngineering.application.form.NPuzzleForm;
import webEngineering.application.project.taquin.NPuzzle;
import webEngineering.application.project.taquin.NPuzzleDistanceComparator;
import webEngineering.application.project.taquin.Position;
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

    protected Position[] positionSolutionList;

    public NPuzzleResolverImp(NPuzzleForm form) {

        NPuzzle puzzle = new NPuzzle(form.getDimension());
        puzzle.setBoard(form.getInitialBoard());
        pQueue = new PriorityQueue<>(new NPuzzleDistanceComparator());
        pQueue.add(puzzle);
        this.goalState = form.getExpectedBoard();
    //    this.heuristic = form.getHeuristicFunction();
        currentBoardInMemory = 0;
        totalBoardDuringRun = 0;
        maxBoardInMemory = 0;
        positionSolutionList = createPositionSolutionList(goalState);
    }

    public NPuzzleResolverImp(NPuzzle puzzle, int[][] goalState, HeuristicFunction heuristic) {

        this.goalState = goalState;
        pQueue = new PriorityQueue<>(new NPuzzleDistanceComparator());
        pQueue.add(puzzle);
        this.heuristic = heuristic;
        currentBoardInMemory = 0;
        totalBoardDuringRun = 0;
        maxBoardInMemory = 0;
        positionSolutionList = createPositionSolutionList(goalState);

    }

    public abstract NPuzzle resolve();

    private Position[] createPositionSolutionList(int[][] goalState) {

        positionSolutionList = new Position[goalState.length * goalState.length];

        for (int i = 0; i < goalState.length; i++) {
            for (int j = 0; j < goalState.length;j++)  {
                positionSolutionList[goalState[i][j]] =new Position(i, j);
            }
        }
        return positionSolutionList;
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

    public Position[] getPositionSolutionList() {
        return positionSolutionList;
    }

    public void setPositionSolutionList(Position[] positionSolutionList) {
        this.positionSolutionList = positionSolutionList;
    }

    public int[][] getGoalState() {
        return goalState;
    }

    public void setpQueue(PriorityQueue<NPuzzle> pQueue) {
        this.pQueue = pQueue;
    }
}
