package webEngineering.application.project.taquinv2.algo;

import webEngineering.application.project.taquin.Direction;
import webEngineering.application.project.taquin.Position;
import webEngineering.application.project.taquinv2.State;
import webEngineering.application.project.taquinv2.StateHashMap;
import webEngineering.application.project.taquinv2.heuristic.Heuristic;

import java.util.HashMap;
import java.util.Map;

public class SmartIDAStar extends IDAStar {

    Direction[] savedDirections = new Direction[10000];

    Direction[] allowedDirection;

    Map<Integer, int[]> seenElement = new HashMap<>();

    public SmartIDAStar(Heuristic heuristic, State initalState, State expectedState, Position[] solutionPositionLis) {
        super(heuristic, initalState, expectedState, solutionPositionLis);

        allowedDirection = new Direction[4];
        allowedDirection[1] = Direction.RIGTH;
        allowedDirection[2] = Direction.DOWN;
        allowedDirection[3] = Direction.LEFT;
        allowedDirection[0] = Direction.UP;
    }

    @Override
    public State resolve() {

        while (finalState == null) {
            int currentBound = nextCostBound;
            finalState = depthFirstSearch(initalState, currentBound, 0);
            nextCostBound += 2;
        }
        return  finalState;
    }

    private State depthFirstSearch(State currentState, int currentBound, int index) {

        //return if a solution is found
        int heuristicResult = heuristic.estimate(currentState, solutionPositionLis);
        currentState.setHeuristicDistance(heuristicResult);
        currentState.setTotalDistance(heuristicResult + currentState.getTotalDistance());
        if (heuristicResult == 0)
            return currentState;
        if (currentState.getTotalDistance() > nextCostBound)
            nextCostBound = currentState.getTotalDistance();

        Direction previousDirection = savedDirections[index];

        index++;
        for (Direction d : allowedDirection) {

            if (currentState.canSwap(d, previousDirection)) {
                if (currentState.getTotalDistance() <= currentBound) {
                    int hash = StateHashMap.hashKey(currentState.getBoard());
                    if (!seenElement.containsKey(hash)) {
                        seenElement.put(hash, currentState.getBoard());
                        currentState.swap(d);
                        savedDirections[index] = d;
                        if (depthFirstSearch(currentState, currentBound, index) != null)
                            return currentState;
                        currentState.swap(d.getRevese());
                        seenElement.remove(hash, currentState.getBoard());
                    }
                }
            }
        }
        index--;

        return null;
    }


}
