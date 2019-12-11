package webEngineering.application.project.taquin.heuristic;

import webEngineering.application.project.taquin.utils.Position;
import webEngineering.application.project.taquin.State;

public class OptimizedManhattan implements Heuristic<Position[]> {

    public OptimizedManhattan(Position[] solutionPositionList) {
        this.solutionPositionList = solutionPositionList;
    }

    Position[] solutionPositionList;


    @Override
    public int estimate(State currentState, Position[] solutionPositionLise) {

        int size = currentState.getDimension();
        int distance = 0;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {

                int piece = currentState.find(i, j);
                Position p  = solutionPositionList[piece];
                int toAdd = Math.abs(i
                        - p.getX())
                        + Math.abs(j - p.getY());
                distance +=  toAdd;
                if (toAdd != 0)
                    distance++;
            }
        }
        return distance;
    }
}
