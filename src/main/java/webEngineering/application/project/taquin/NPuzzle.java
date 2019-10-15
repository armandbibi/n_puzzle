package webEngineering.application.project.taquin;

import java.util.ArrayList;
import java.util.List;

public class NPuzzle implements Cloneable{

	private int dimension;
	
	private int[][] board;
	
	private int lastMove;
	
	
	private ArrayList<Integer> path;
	
	private Integer distance;

	
	public NPuzzle(int dimension) {

		this.distance = 0;
		this.dimension = dimension;
		this.lastMove = 0;
		this.path = new ArrayList<>();
		
		board = new int[dimension][dimension];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
					board[i][j] = dimension * i + j;
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
	
	
	
	private void swap(Position pos1, Position pos2) {
		
		int tmp = board[pos1.getX()][ pos1.getY()];
		board[pos1.getX()][ pos1.getY()] = board[pos2.getX()][ pos2.getY()];
		board[pos2.getX()][ pos2.getY()] = tmp;
	}
	
	private Direction getMove(int piece) {
		
		Position blankSpotPosition = findBlankSpot();
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
		Position blankSpot = findBlankSpot();
		if (direction == null || blankSpot == null)
			return null;
		int line = blankSpot.getX();
	     int column = blankSpot.getY();
	     switch (direction) {
	     case LEFT:
	    	 this.swap(blankSpot, new Position(line, column + 1));
	    	 break;
	     case RIGTH:
	    	 this.swap(blankSpot, new Position(line, column - 1));
	    	 break;
	     case UP:
	    	 this.swap(blankSpot, new Position(line + 1, column));
	    	 break;
	     case DOWN:
	    	 this.swap(blankSpot, new Position(line - 1, column));
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
	
	public ArrayList<NPuzzle>visit() {
		ArrayList<NPuzzle> children = new ArrayList<>();
		List<Integer> allowedMove = getAllowedMove();
		for (Integer position: allowedMove) {
			if (!position.equals(lastMove)) {
				NPuzzle newPuzzle;
				newPuzzle = this.clone();
				newPuzzle.move(position);
				newPuzzle.path.add(position);
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
		for (var i = 0; i < this.dimension; i++)
			System.arraycopy(this.board[i], 0, newPuzzle.board[i], 0, this.dimension);
		newPuzzle.distance = distance;
		newPuzzle.dimension = dimension;
		for (var i = 0; i < this.path.size(); i++) newPuzzle.path.add(path.get(i));
		return newPuzzle;
	}
}
