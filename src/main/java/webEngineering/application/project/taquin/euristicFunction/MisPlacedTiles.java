package webEngineering.application.project.taquin.euristicFunction;

import webEngineering.application.project.taquin.NPuzzle;
import webEngineering.application.project.taquin.algo.NPuzzleResolverImp;

public class MisPlacedTiles implements HeuristicFunction {
    @Override
    public int estimate(NPuzzle puzzle, NPuzzleResolverImp resolver) {

        int cost = 0;
        int[][] solution = resolver.getGoalState();

        for (int i = 0; i < solution.length; i++) {
            for (int j = 0; j < solution.length; j++) {
                if (puzzle.getBoard()[i][j] != solution[i][j])
                    cost++;
            }
        }
        return cost;
    }
}
