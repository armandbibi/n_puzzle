package webEngineering.application.project.taquin;

import webEngineering.application.project.taquin.utils.Direction;
import webEngineering.application.project.taquin.utils.Position;

import java.util.ArrayList;
import java.util.List;

public class State implements Cloneable {


    int[] board;

    private State previousState;

    private int dimension;

    private int blankX;

    private int blankY;

    private Direction direction;

    private int totalDistance;

    private int heuristicDistance;

    private int realDistance;

    public State(int dimension, State state) {
        this.dimension = dimension;
        this.previousState = state;
        totalDistance = 0;
        heuristicDistance = -1;
        realDistance = 0;
        this.direction = Direction.NONE;
    }

    private int pick(int x, int y) {

        int mask = 0b11111 << (x * 5);

        int value = (board[y] & mask) >> (x * 5);
        board[y] = (board[y] & ~mask);
        return value;
    }

    private void put(int x, int y, int value) {
        int mask = 0b11111 << ((x) * 5);
        board[y] = (board[y] & ~mask) | (value << (x * 5));
    }

    public int find(int x, int y) {
        int mask = 0b11111 << (x * 5);

        return (board[y] & mask) >> (x * 5);
    }

    public void swap(Direction direction) {

        if (direction == Direction.DOWN)
            swapDown();
        else if (direction == Direction.LEFT)
            swapLeft();
        else if (direction == Direction.RIGTH)
            swapRight();
        else if (direction == Direction.UP)
            swapUp();
    }

    private void swapUp() {
        int val = pick(blankX, blankY - 1);
        put(blankX, blankY, val);
        blankY--;
    }

    private void swapDown() {
        int val = pick(blankX, blankY + 1);
        put(blankX, blankY, val);
        blankY++;
    }

    private void swapRight() {
        int val = pick(blankX - 1, blankY);
        put(blankX, blankY, val);
        blankX--;
    }

    private void swapLeft() {
        int val = pick(blankX + 1, blankY);
        put(blankX, blankY, val);
        blankX++;
    }

    private List<State> getAllowedMove() {

        List<State> allowedDirection = new ArrayList<>();
            getMoveDown(allowedDirection);
            getMoveUp(allowedDirection);
            getMoveLeft(allowedDirection);
            getMoveRight(allowedDirection);
        return allowedDirection;
    }

    private void getMoveUp(List<State> allowedDirection) {
        if (blankY > 0 && this.direction.isNotInverse(Direction.UP)) {
            State cloned = this.clone();
            cloned.direction = Direction.UP;
            cloned.swap(cloned.direction);
            cloned.previousState = this;
            allowedDirection.add(cloned);

        }
    }

    private void getMoveDown(List<State> allowedDirection){
        if (blankY < dimension - 1 && this.direction.isNotInverse(Direction.DOWN)) {
            State cloned = this.clone();
            cloned.direction = Direction.DOWN;
            cloned.swap(cloned.direction);
            cloned.previousState = this;
            allowedDirection.add(cloned);
        }
    }


    private void getMoveRight(List<State> allowedDirection){
        if (blankX > 0 && this.direction.isNotInverse(Direction.RIGTH)) {
            State cloned = this.clone();
            cloned.direction = Direction.RIGTH;
            cloned.swap(cloned.direction);
            cloned.previousState = this;

            allowedDirection.add(cloned);
        }
    }

    private void getMoveLeft(List<State> allowedDirection){
        if (blankX < dimension - 1 && this.direction.isNotInverse(Direction.LEFT)) {
            State cloned = this.clone();
            cloned.direction = Direction.LEFT;
            cloned.swap(cloned.direction);
            cloned.previousState = this;

            allowedDirection.add(cloned);
        }
    }

    public boolean canSwap(Direction d, Direction previous) {
        if (previous == null)
            return true;
        else if (d == Direction.DOWN && blankY < dimension - 1 && d.isNotInverse(previous))
            return true;
        else if (d == Direction.UP && (blankY > 0 && d.isNotInverse(previous)))
            return  true;
        else if(d == Direction.LEFT && blankX < dimension - 1 && d.isNotInverse(previous))
            return true;
        else if (d== Direction.RIGTH && blankX > 0 && d.isNotInverse(previous))
            return true;
        return false;
    }

    public List<State> visit() {
        return getAllowedMove();
    }


    public void setBoardFromMatrix(int[][] matrix) {

        board = new int[dimension];

        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                int k = matrix[i][j];
                board[i] |= (k << ((dimension - j - 1) * 5));
                if (matrix[i][j] == 0) {
                    blankX = dimension - j - 1;
                    blankY = i;
                }
            }
        }
    }

    public int[][] getBoardAsMatrix() {

        int[][] matrix = new int[dimension][dimension];

        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                matrix[i][j] = (board[i] >> ((dimension - j - 1) * 5)) & 0b11111;
            }
        }
        return  matrix;

    }

    public int[] getBoard() {
        return board;
    }

    public State getPreviousState() {
        return previousState;
    }

    public int getDimension() {
        return dimension;
    }

    public void setBoard(int[] board) {
        this.board = board;
    }

    public void setPreviousState(State previousState) {
        this.previousState = previousState;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public int getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(int totalDistance) {
        this.totalDistance = totalDistance;
    }

    public int getHeuristicDistance() {
        return heuristicDistance;
    }

    public void setHeuristicDistance(int heuristicDistance) {
        this.heuristicDistance = heuristicDistance;
    }

    public int getRealDistance() {
        return realDistance;
    }

    public void setRealDistance(int realDistance) {
        this.realDistance = realDistance;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public State clone() {
        try {
            State newState = (State) super.clone();
            newState.board = this.board.clone();
            newState.previousState = this;
            newState.realDistance++;
            return newState;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Position[] getInternPosition() {

        Position[] p  = new Position[dimension * dimension];

        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                p[this.find(i, j)] = new Position(i, j);
            }
        }
        return p;
    }

}
