package webEngineering.application.project.taquin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NPuzzle implements Cloneable{

	private int dimension;

	private int[][] board;
	
	private int lastMove;
	
	private Position blankSpot;

	private ArrayList<Integer> path;
	
	private Integer distance;

	private int heuristicDistance;

	private NPuzzle() {}

	public NPuzzle(int dimension) {

		this.distance = 0;
		this.dimension = dimension;
		this.lastMove = 0;
		heuristicDistance = -1;
		this.path = new ArrayList<>();
		board = new int[dimension][dimension];
		blankSpot = null;
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

		if (blankSpot == null)
			blankSpot = findBlankSpot();
		List<Integer> allowedMove = new ArrayList<>();
		int piece;

		if (blankSpot.getX() > 0) {
			piece = board[blankSpot.getX() - 1][blankSpot.getY()];
			if (getMove(piece) != null)
				allowedMove.add(piece);
		}
		if (blankSpot.getX() < dimension - 1) {
			piece = board[blankSpot.getX() + 1][blankSpot.getY()];
			if (getMove(piece) != null)
				allowedMove.add(piece);
		}
		if (blankSpot.getY() > 0) {
			piece = board[blankSpot.getX()][blankSpot.getY() - 1];
			if (getMove(piece) != null)
				allowedMove.add(piece);
		}
		if (blankSpot.getY() < dimension - 1) {
			piece = board[blankSpot.getX()][blankSpot.getY() + 1];
			if (getMove(piece) != null)
				allowedMove.add(piece);
		}
		return allowedMove;
	}
	
	public ArrayList<NPuzzle>visit() {
		ArrayList<NPuzzle> children = new ArrayList<>();
		List<Integer> allowedMove = getAllowedMove();
		for (Integer position: allowedMove) {
			if (!position.equals(lastMove) ) {
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
		NPuzzle newPuzzle = null;
		try {
			newPuzzle = (NPuzzle) super.clone();


		newPuzzle.board = new int[dimension][];
		newPuzzle.blankSpot = new Position(blankSpot.getX(), blankSpot.getY());

		for (var i = 0; i < this.dimension; i++)
			newPuzzle.board[i] = this.board[i].clone();
		newPuzzle.distance = distance;
		newPuzzle.dimension = dimension;
		newPuzzle.path = (ArrayList<Integer>) this.path.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return newPuzzle;

	}

	public Position getBlankSpot() {
		return blankSpot;
	}

	public void setBlankSpot(Position blankSpot) {
		this.blankSpot = blankSpot;
	}


	public int getHeuristicDistance() {
		return heuristicDistance;
	}

	public void setHeuristicDistance(int heuristicDistance) {
		this.heuristicDistance = heuristicDistance;
	}

	@Override
	public boolean equals(Object o) {
		if (((NPuzzle) o).heuristicDistance == heuristicDistance)
			return Arrays.deepEquals(this.board, ((NPuzzle) o).board);
		return false;
	}
}
