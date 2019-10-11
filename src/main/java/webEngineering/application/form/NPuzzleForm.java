package webEngineering.application.form;


import webEngineering.application.project.taquin.euristicFunction.HeuristicFunction;
import webEngineering.application.project.taquin.euristicFunction.ManhattanDistance;

public class NPuzzleForm {

    private int[][] initialBoard;
    private int[][] expectedBoard;
    private String heuristicFunction;
    private int dimension;

    public int[][] getExpectedBoard() {
        return expectedBoard;
    }

    public void setExpectedBoard(int[][] expectedBoard) {
        this.expectedBoard = expectedBoard;
    }

    public HeuristicFunction getHeuristicFunction() {
        if (heuristicFunction.equals("Manhattan distance"))
            return new ManhattanDistance();
        else
            return null;
    }

    public void setHeuristicFunction(String heuristicFunction) {
        this.heuristicFunction = heuristicFunction;
    }

    public int[][] getInitialBoard() {
        return initialBoard;
    }

    public void setInitialBoard(int[][] initialBoard) {
        this.initialBoard = initialBoard;
    }

    public int getDimension() {
        return dimension;
    }
}

