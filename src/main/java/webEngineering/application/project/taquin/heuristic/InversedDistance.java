package webEngineering.application.project.taquin.heuristic;

import webEngineering.application.project.taquin.State;

public class InversedDistance   implements Heuristic<State> {

    @Override
    public int estimate(State currentState, State helper) {

        int size = currentState.getDimension();

     /*   for (int i = 0; i < ; i++) {

        }
    */
    return 0;}
}
