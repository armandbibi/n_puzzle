package webEngineering.application.project.taquin.euristicFunction;

import webEngineering.application.project.taquin.NPuzzle;

public interface HeuristicFunction {

	public int estimate(NPuzzle puzzle, int[][] solution);
}
