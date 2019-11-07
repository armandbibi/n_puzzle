package webEngineering.application.project.taquinv2;

import webEngineering.application.project.taquin.Position;
import webEngineering.application.project.taquinv2.algo.IDAStar;
import webEngineering.application.project.taquinv2.heuristic.Heuristic;

public class NPuzzle {


    int[][] initBoard;

    int[][] expectedBoard;

    int dimension;

    Heuristic heuristic;

    IDAStar algo;

    public NPuzzle(int[][] initBoard, int[][] expectedBoard, int dimension, Heuristic heuristic) {
        this.initBoard = initBoard;
        this.expectedBoard = expectedBoard;
        this.dimension = dimension;
        this.heuristic = heuristic;
    }

    public void initAlgo() {

        State newState = new State(dimension, null);
        newState.setBoardFromMatrix(initBoard);
        State expectedState = new State(dimension, null);
        expectedState.setBoardFromMatrix(expectedBoard);
        Position[] solutionlist = expectedState.getInternPosition();

        this.algo = new IDAStar(heuristic, newState, expectedState, solutionlist);
    }

    public State resolve() {
        return algo.resolve();
    }

    public NPuzzle() {

    }

    public int[][] getInitBoard() {
        return initBoard;
    }

    public void setInitBoard(int[][] initBoard) {
        this.initBoard = initBoard;
    }

    public int[][] getExpectedBoard() {
        return expectedBoard;
    }

    public void setExpectedBoard(int[][] expectedBoard) {
        this.expectedBoard = expectedBoard;
    }

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public Heuristic getHeuristic() {
        return heuristic;
    }

    public void setHeuristic(Heuristic heuristic) {
        this.heuristic = heuristic;
    }

    public IDAStar getAlgo() {
        return algo;
    }

    public void setAlgo(IDAStar algo) {
        this.algo = algo;
    }

    public void displayConsole() {
        State s = algo.getFinalState();
        do {
            int[][] matrix = s.getBoardAsMatrix();
            for (int i = 0; i < dimension; i++) {
                for (int j = 0; j < dimension; j++) {
                    System.out.print("| " + matrix[i][j]+ " ");
                }
                System.out.println("|");
            }
            System.out.println("");
            System.out.println("");
            s = s.getPreviousState();
        } while (s != null);
        System.out.println("");
    }
}
