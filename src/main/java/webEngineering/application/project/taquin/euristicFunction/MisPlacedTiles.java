package webEngineering.application.project.taquin.euristicFunction;

import webEngineering.application.project.taquin.NPuzzle;

public class MisPlacedTiles implements HeuristicFunction {
    @Override
    public int estimate(NPuzzle puzzle, int[][] solution) {

        int cost = 0;

        for (int i = 0; i < solution.length; i++) {
            for (int j = 0; j < solution.length; j++) {
                if (puzzle.getBoard()[i][j] != solution[i][j])
                    cost++;
            }
        }
        return cost;
    }
}
