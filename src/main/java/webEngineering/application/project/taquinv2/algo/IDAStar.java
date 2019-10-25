package webEngineering.application.project.taquinv2.algo;

import webEngineering.application.project.taquin.Position;
import webEngineering.application.project.taquinv2.State;
import webEngineering.application.project.taquinv2.StateHashMap;
import webEngineering.application.project.taquinv2.heuristic.Heuristic;

import java.util.*;

public class IDAStar implements Algo {

    Heuristic heuristic;

    State initalState;

    State expectedState;

    State finalState;

    Position[] solutionPositionLis;

    int currentBound;

    int depth = 0;

    int exploredNode;

    StateHashMap visitedNode = new StateHashMap();

    int nextCostBound;

    Comparator<State> comparator =  Comparator.comparingInt(State::getHeuristicDistance);

    public IDAStar(Heuristic heuristic, State initalState, State expectedState, Position[] solutionPositionLis) {
        this.heuristic = heuristic;
        this.initalState = initalState;
        this.expectedState = expectedState;
        this.solutionPositionLis = solutionPositionLis;
        finalState = null;
        exploredNode = 0;
        nextCostBound = heuristic.estimate(initalState, solutionPositionLis);
    }

    public State resolve() {

        long start = System.currentTimeMillis();
        while (finalState == null) {
            currentBound = nextCostBound;
            finalState = depthFirstSearch(initalState, 0);
            initalState.setTotalDistance(0);
            System.out.println("seen " + exploredNode + " nodes and going for bound of " + nextCostBound);
        }
        long end = System.currentTimeMillis();
        //finding the time difference and converting it into seconds
        int size = 0;
        while (finalState.getPreviousState() != null) {
            size++;
            finalState = finalState.getPreviousState();
        }
        float sec = (end - start) / 1000F; System.out.println(sec + " seconds & " + size + "shots");

        return finalState;

    }

    private State depthFirstSearch(State currentState, int realBound) {
        if (currentState.getHeuristicDistance() == 0) {
            return currentState;
        }
        exploredNode++;
        List<State> children = currentState.visit();
        List<State> validChildren = new ArrayList<>(children);
        for (State child : children) {

            int h = heuristic.estimate(child, solutionPositionLis);
            int hash = StateHashMap.hashKey(child.getBoard());
            if (!visitedNode.containsKey(hash)) {
                visitedNode.put(hash, child);
                int value = child.getRealDistance() + h;
                child.setTotalDistance(value);
                child.setHeuristicDistance(h);
                if (nextCostBound < value)
                    nextCostBound = value;

            }
            else
                validChildren.remove(child);
        }

        Collections.sort(validChildren, comparator.reversed());

        for (State child: validChildren) {

                int value = child.getTotalDistance();

                if (child.getTotalDistance() <= currentBound) {
                    State result = depthFirstSearch(child, realBound + 1);
                    if (result != null) {
                        return result;
                    }
                }

                visitedNode.remove(StateHashMap.hashKey(child.getBoard()), child);
            }
        return null;
    }

    public Heuristic getHeuristic() {
        return heuristic;
    }

    public void setHeuristic(Heuristic heuristic) {
        this.heuristic = heuristic;
    }
}
