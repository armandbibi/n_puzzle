package webEngineering.application.form;


import webEngineering.application.project.taquin.algo.AStar;
import webEngineering.application.project.taquin.algo.IDAStar;
import webEngineering.application.project.taquin.algo.NPuzzleResolver;
import webEngineering.application.project.taquin.euristicFunction.HeuristicFunction;
import webEngineering.application.project.taquin.euristicFunction.LinearConflict;
import webEngineering.application.project.taquin.euristicFunction.ManhattanDistance;
import webEngineering.application.project.taquin.euristicFunction.MisPlacedTiles;

public class NPuzzleForm {

    private int[][] initialBoard;
    private int[][] expectedBoard;
    private String heuristicFunction;
    private String nPuzzleResolver;
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
        else if (heuristicFunction.equals("misplaced tiles"))
            return new MisPlacedTiles();
        else if (heuristicFunction.equals("linear conflicts"))
            return new LinearConflict();
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

    public NPuzzleResolver getnPuzzleResolver() {
        if (nPuzzleResolver == "A Star")
            return new AStar(this);
        else if (nPuzzleResolver == "IDA Star")
            return new IDAStar(this);
        else
            return null;
    }

    public void setnPuzzleResolver(String nPuzzleResolver) {
        this.nPuzzleResolver = nPuzzleResolver;
    }
}

