package webEngineering.application.project.taquin.heuristic;

import webEngineering.application.project.taquin.State;

public class MisplacedTile implements Heuristic<State>{
    @Override
    public int estimate(State currentState, State expectedState) {

        int distance = 0;
        for (int i = 0; i < currentState.getDimension(); i++) {
            for (int j = 0; j < currentState.getDimension(); j++) {
                if(currentState.find(i,j) == expectedState.find(i, j))
                    distance++;
            }
        }
        return distance;
    }
}
