package webEngineering.application.project.taquin;

import java.util.ArrayList;
import java.util.List;

public class NPuzzle implements Cloneable{

	private int dimension;
	
	private int board[][];
	
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
	
	public Position findBlanckSpot() {
		for (int i = 0; i < dimension; i++) {
			for (int j = 0; j < dimension; j++)
				if (board[i][j] == 0)
					return new Position(i, j);
		}
		return null;
	}
	
	
	
	public void swap(Position pos1, Position pos2) {
		
		int tmp = board[pos1.getX()][ pos1.getY()];
		board[pos1.getX()][ pos1.getY()] = board[pos2.getX()][ pos2.getY()];
		board[pos2.getX()][ pos2.getY()] = tmp;
	}
	
	public Direction getMove(int piece) {
		
		Position blanckSpotPosition = findBlanckSpot();
		int line = blanckSpotPosition.getX();
		int column = blanckSpotPosition.getY();
		
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
		Position blanckSpot = findBlanckSpot();
		if (direction == null)
			return null;
		 int line = blanckSpot.getX();
	     int column = blanckSpot.getY();
	     switch (direction) {
	     case LEFT:
	    	 this.swap(blanckSpot, new Position(line, column + 1));
	    	 break;
	     case RIGTH:
	    	 this.swap(blanckSpot, new Position(line, column - 1));
	    	 break;
	     case UP:
	    	 this.swap(blanckSpot, new Position(line + 1, column));
	    	 break;
	     case DOWN:
	    	 this.swap(blanckSpot, new Position(line - 1, column));
	    	 break;
	      }
	    this.lastMove = piece;
	    return direction;
	}
	
	public  List<Integer> getAllowedMove() {
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
		ArrayList<NPuzzle> children =  new ArrayList<NPuzzle>();
		List<Integer> allowedMove = getAllowedMove();
		for (Integer position: allowedMove) {
			if (!position.equals(lastMove)) {
				NPuzzle newPuzzle;
				newPuzzle = (NPuzzle) this.clone();
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

	public void setDimension(int dimension) {
		this.dimension = dimension;
	}

	public int[][] getBoard() {
		return board;
	}

	public void setBoard(int board[][]) {
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
		for (var i = 0; i < this.dimension; i++) {
			for (var j = 0; j < this.dimension; j++) {
				newPuzzle.board[i][j] = this.board[i][j];
			}
		}
		
		newPuzzle.distance = distance;
		newPuzzle.dimension = dimension;
		
		for (var i = 0; i < this.path.size(); i++) {
			newPuzzle.path.add(path.get(i));
		}
		return newPuzzle;
	};
}
