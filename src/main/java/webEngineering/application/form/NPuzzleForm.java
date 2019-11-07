package webEngineering.application.form;


import webEngineering.application.project.taquin.algo.NPuzzleResolver;
import webEngineering.application.project.taquinv2.heuristic.Heuristic;
import webEngineering.application.project.taquinv2.heuristic.Manhathan;
import webEngineering.application.project.taquinv2.heuristic.MisplacedTile;
import webEngineering.application.project.taquinv2.heuristic.OptimizedManhattan;

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

    public Heuristic getHeuristicFunction() {
        if (heuristicFunction.equals("Manhattan distance"))
            return new Manhathan();
        else if (heuristicFunction.equals("misplaced tiles"))
            return new MisplacedTile();
        else if (heuristicFunction.equals("linear conflicts"))
            return new OptimizedManhattan();
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

    public void setnPuzzleResolver(String nPuzzleResolver) {
        this.nPuzzleResolver = nPuzzleResolver;
    }
}

