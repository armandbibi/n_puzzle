package webEngineering.application.project.taquin;

import webEngineering.application.project.taquin.euristicFunction.HeuristicFunction;

import java.util.ArrayList;
import java.util.List;

public class NPuzzle implements Cloneable{

	private int dimension;

	private byte[] state;

	private byte blank;
	
	private int[][] board;
	
	private int lastMove;
	
	private Position blankSpot;

	private ArrayList<Integer> path;
	
	private Integer distance;


	public NPuzzle(int dimension) {

		this.distance = 0;
		this.dimension = dimension;
		this.lastMove = 0;
		this.path = new ArrayList<>();
		board = new int[dimension][dimension];
		this.state = new byte[dimension * dimension];
		blankSpot = null;
	}

	private byte getPieceAt(int col, int row) {
		return state[col + row * dimension];
	}

	private  byte getPieceAt(int index;) {
		return state[index];
	}

	private void putPieceAt(int col, int row, byte value) {
		state[col + row * dimension] = value;
	}

	private void putPieceAt(int index, byte value)  {
		state[index] = value;
	}

	private void swap(byte col, byte row) {

		byte tmp = getPieceAt(col, row);
		putPieceAt(col, row, getPieceAt(blank));
		putPieceAt(blank, tmp);
		blank = (byte) (col + row * dimension);
	}

	private void setBlankSpot() {
		for (byte i = 0; i < dimension * dimension; i++) {
			if (state[i] == 0) {
				blank = state[i % dimension];
			}
		}
	}

	
	
	private Position findBlankSpot() {
		for (int i = 0; i < dimension; i++) {
			for (int j = 0; j < dimension; j++)
				if (board[i][j] == 0)
					return new Position(i, j);
		}
		return null;
	}

	private void swap(Position blankSpot, int x, int y) {
		
		int tmp = board[blankSpot.getX()][blankSpot.getY()];
		board[blankSpot.getX()][blankSpot.getY()] = board[x][y];
		board[x][y] = tmp;
		blankSpot.setX(x);
		blankSpot.setY(y);
	}
	
	private Direction getMove(int piece) {
		
		Position blankSpotPosition = blankSpot;
		if (blankSpotPosition == null)
			throw new IllegalArgumentException("no blank on the field");
		int line = blankSpotPosition.getX();
		int column = blankSpotPosition.getY();
		
		 if (line > 0 && piece == this.board[line-1][column]) {
		      return Direction.DOWN;
		  } else if (line < this.dimension - 1 && piece == this.board[line+1][column]) {
		      return Direction.UP;
		  } else if (column > 0 && piece == this.board[line][column-1]) {
		      return Direction.RIGTH;
		  } else if (column < this.dimension - 1 && piece == this.board[line][column+1]) {
		      return Direction.LEFT;
		  }
		else
			return null;
	}
	
	public Direction move(int piece) {

		Direction direction = getMove(piece);
		if (direction == null || blankSpot == null)
			return null;
		int line = blankSpot.getX();
	     int column = blankSpot.getY();
	     switch (direction) {
	     case LEFT:
	    	 this.swap(blankSpot, line, column + 1);
	    	 break;
	     case RIGTH:
	    	 this.swap(blankSpot, line, column - 1);
	    	 break;
	     case UP:
	    	 this.swap(blankSpot, line + 1, column);
	    	 break;
	     case DOWN:
	    	 this.swap(blankSpot, line - 1, column);
	    	 break;
	      }
	    this.lastMove = piece;
	    return direction;
	}
	
	private List<Integer> getAllowedMove() {

		List<Integer> allowedMove = new ArrayList<>();

		for (int i = 0; i < dimension; i++) {
			for (int j = 0; j < dimension; j++) {
				int piece = board[i][j];
				if (getMove(piece) != null) {
					allowedMove.add(piece);
				}
			}
		}
		return allowedMove;
	}
	
	public ArrayList<NPuzzle>visit(HeuristicFunction heuristicFunction, int[][] goalState) {
		ArrayList<NPuzzle> children = new ArrayList<>();
		List<Integer> allowedMove = getAllowedMove();
		for (Integer position: allowedMove) {
			if (!position.equals(lastMove) ) {
				NPuzzle newPuzzle;
				newPuzzle = this.clone();
				newPuzzle.move(position);
				newPuzzle.path.add(position);
				newPuzzle.setDistance(newPuzzle.getDistance() + heuristicFunction.estimate(newPuzzle, goalState));
				children.add(newPuzzle);
			}
		}
		return children;
	}
	
	public int getDimension() {
		return dimension;
	}

	public int[][] getBoard() {
		return board;
	}

	public void setBoard(int[][] board) {
		this.board = board;
		if (blankSpot == null) blankSpot = findBlankSpot();
	}

	public Integer getDistance() {
		return distance;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
	}

	public ArrayList<Integer> getPath() {
		return path;
	}

	public boolean isAs(int[][] goalState) {
		for (int i = 0; i < dimension; i++) {
			for (int j = 0; j < dimension;j++) {
				if (board[i][j] != goalState[i][j])
					return false;
			}
		}
		return true;
	}

	public NPuzzle clone() {

		NPuzzle newPuzzle = new NPuzzle(this.dimension);
		newPuzzle.blankSpot = new Position(blankSpot.getX(), blankSpot.getY());

		for (var i = 0; i < this.dimension; i++)
			System.arraycopy(this.board[i], 0, newPuzzle.board[i], 0, this.dimension);
		newPuzzle.distance = distance;
		newPuzzle.dimension = dimension;
		for (var i = 0; i < this.path.size(); i++) newPuzzle.path.add(path.get(i));
		return newPuzzle;
	}

	public Position getBlankSpot() {
		return blankSpot;
	}

	public void setBlankSpot(Position blankSpot) {
		this.blankSpot = blankSpot;
	}
}
