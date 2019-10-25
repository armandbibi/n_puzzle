package webEngineering.application.project.taquinv2.heuristic;

import webEngineering.application.project.taquin.Position;
import webEngineering.application.project.taquinv2.State;

public class OptimizedManhattan implements Heuristic<Position[]> {

    @Override
    public int estimate(State currentState, Position[] solutionPositionList) {

        int size = currentState.getDimension();
        int distance = 0;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {

                int piece = currentState.find(i, j);
                Position p  = solutionPositionList[piece];
                distance += Math.abs(i
                        - p.getX())
                        + Math.abs(j - p.getY());
            }
        }
        return distance;
    }
}
