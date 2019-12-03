package webEngineering.application.project.taquin.heuristic;

import webEngineering.application.project.taquin.State;

/**
 * Implement a function for an heuristic
 */
public interface Heuristic<T> {

    public int estimate(State currentState, T helper);
}
