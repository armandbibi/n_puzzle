package webEngineering.application.project.taquin.euristicFunction;

import webEngineering.application.project.taquin.NPuzzle;
import webEngineering.application.project.taquin.algo.NPuzzleResolverImp;

public interface HeuristicFunction {

	public int estimate(NPuzzle puzzle, NPuzzleResolverImp resolver);
}
