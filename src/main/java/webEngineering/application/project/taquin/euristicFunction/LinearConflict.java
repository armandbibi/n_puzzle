package webEngineering.application.project.taquin.euristicFunction;

import webEngineering.application.project.taquin.NPuzzle;

public class LinearConflict extends ManhattanDistance{


    @Override
    public int estimate(NPuzzle puzzle, int[][] solution) {

        int cost = super.estimate(puzzle, solution);

        cost += linearVerticalConflict(puzzle.getBoard(), solution);
        cost += linearHorizontalConflict(puzzle.getBoard(), solution);
        return cost;
    }

    private int linearVerticalConflict(int [][]currentState, int[][]solution) {
        int dimension = currentState.length;
        int linearConflict = 0;

        for (int row = 0; row < dimension; row++){
            int max = -1;
            for (int column = 0;  column < dimension; column++){
                int cellValue = currentState[row][column];
                //is tile in its goal row ?
                if (cellValue != 0 && isInGoalRow(cellValue, row, solution)){
                    if (cellValue > max){
                        max = cellValue;
                    }else {
                        //linear conflict, one tile must move up or down to allow the other to pass by and then back up
                        //add two moves to the manhattan distance
                        linearConflict += 2;
                    }
                }
            }
        }
        return linearConflict;
    }

    private boolean isInGoalRow(int value, int row, int[][] goalState) {
        for (int k = 0; k < goalState.length; k++) {
            if (goalState[row][k] == value)
                return true;
        }
        return false;
    }

    private int linearHorizontalConflict(int[][] currentState, int[][] goalState) {

        int dimension = currentState.length;
        int linearConflict = 0;

        for (int column = 0; column < dimension; column++){
            int max = -1;
            for (int row = 0;  row < dimension; row++){
                int cellValue = currentState[row][column];
                //is tile in its goal row ?
                if (cellValue != 0 && isInGoalRow(cellValue, column, goalState)){
                    if (cellValue > max){
                        max = cellValue;
                    }else {
                        //linear conflict, one tile must move left or right to allow the other to pass by and then back up
                        //add two moves to the manhattan distance
                        linearConflict += 2;
                    }
                }
            }
        }
        return linearConflict;
    }

    private boolean isInGoalColumn(int value, int column, int[][] goalState) {
        for (int k = 0; k < goalState.length; k++) {
            if (goalState[k][column] == value)
                return true;
        }
        return false;
    }
}
