package webEngineering.webEngineering.project.taquin.euristicFunction;

import webEngineering.webEngineering.project.taquin.NPuzzle;

public interface HeuristicFunction {

	public int estimate(NPuzzle puzzle, int[][] solution);
}
