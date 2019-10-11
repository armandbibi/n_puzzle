package webEngineering.application.project.taquin.euristicFunction;

import webEngineering.application.project.taquin.NPuzzle;
import webEngineering.application.project.taquin.Position;

public class ManhattanDistance implements HeuristicFunction {

	@Override
	public int estimate(NPuzzle puzzle, int[][] solution) {
		
		int distance = 0;
		
		for (int i = 0; i < puzzle.getDimension(); i++) {
			for (int j = 0; j < puzzle.getDimension(); j++) {
				
				int piece = puzzle.getBoard()[i][j];
				
				if (piece != 0) {

					Position expectedPosition = null;
					for (int k = 0; k < puzzle.getDimension(); k++)
						for (int l = 0; l < puzzle.getDimension(); l++)
							if (solution[k][l] == piece)
								expectedPosition = new Position(k, l);
						
					distance += Math.abs(i - expectedPosition.getX()) + Math.abs(j - expectedPosition.getY() );
				}
			}
		}
		
		return distance;
	}
}
