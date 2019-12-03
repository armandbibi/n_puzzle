package webEngineering.application.project.taquin.heuristic;

import webEngineering.application.project.taquin.State;

public class Manhathan implements Heuristic<State> {

    @Override
    public int estimate(State currentState, State expectedState) {

        int size = currentState.getDimension();
        int distance = 0;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {

                int piece = currentState.find(i, j);
                for (int k = 0; k < size; k++) {
                    for (int l = 0; l < size; l++) {
                        if (piece == expectedState.find(k, l))
                            distance += Math.abs(i -k) + Math.abs(j -l);

                    }
                }
            }
        }
        return distance;
    }
}
