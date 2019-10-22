package webEngineering.application.project.taquin.euristicFunction;

import webEngineering.application.project.taquin.NPuzzle;
import webEngineering.application.project.taquin.Position;
import webEngineering.application.project.taquin.algo.NPuzzleResolverImp;

public class ManhattanDistance implements HeuristicFunction {

	@Override
	public int estimate(NPuzzle puzzle, NPuzzleResolverImp resolver) {
		
		int distance = 0;
		int dimension = puzzle.getDimension();
		int[][] currentBoard = puzzle.getBoard();
		Position[] solutionPositionList = resolver.getPositionSolutionList();
		
		for (int i = 0; i < dimension; i++) {
			for (int j = 0; j < dimension; j++) {
				
				int piece = currentBoard[i][j];
				Position p  = solutionPositionList[piece];
				distance += Math.abs(i
						- p.getX())
						+ Math.abs(j - p.getY());
			}
		}
		return distance;
	}
}
