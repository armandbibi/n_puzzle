package webEngineering.application.project.taquinv2.heuristic;

import webEngineering.application.project.taquinv2.State;

/**
 * Implement a function for an heuristic
 */
public interface Heuristic<T> {

    public int estimate(State currentState, T helper);
}
