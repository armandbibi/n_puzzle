package webEngineering.application.project.taquin.heuristic;

import webEngineering.application.project.taquin.utils.Position;
import webEngineering.application.project.taquin.State;

public class EuclidianDist implements Heuristic<Position[]> {

    @Override
    public int estimate(State currentState, Position[] solutionPositionList) {

        int size = currentState.getDimension();
        int distance = 0;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int piece = currentState.find(i, j);
                Position p  = solutionPositionList[piece];
            //    int toAdd = sqrt( ((j - p.getY()) * (j - p.getX())) + ((i - p.getY()) * (i - p.getY())));
          //      distance +=  toAdd;
            }
        }
        return distance;
    }
}
