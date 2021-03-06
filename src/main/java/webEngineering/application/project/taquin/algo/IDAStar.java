package webEngineering.application.project.taquin.algo;

import webEngineering.application.project.taquin.utils.Position;
import webEngineering.application.project.taquin.State;
import webEngineering.application.project.taquin.StateHashMap;
import webEngineering.application.project.taquin.heuristic.Heuristic;

import java.util.*;

public class IDAStar implements Algo {

    Heuristic heuristic;

    State initalState;

    State expectedState;

    State finalState;

    int totalMoves;

    int allState = 0;

    float timeComplexity;

    Position[] solutionPositionLis;

    boolean uniformCost = false;

    private boolean greedySearch = false;

    int currentBound;

    int maxBoardInMemory = 0;

    int currentBoardInMemory = 0;

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
        State finalState = this.finalState;
        while (finalState.getPreviousState() != null) {
            size++;
            finalState = finalState.getPreviousState();
        }
        totalMoves = size;
        timeComplexity = (end - start) / 1000F;
        return this.finalState;
    }

    private State depthFirstSearch(State currentState, int realBound) {
        if (currentState.getHeuristicDistance() == 0) {
            return currentState;
        }
        exploredNode++;
        List<State> children = currentState.visit();
        PriorityQueue<State> validChildren = new PriorityQueue<>(comparator);
        for (State child : children) {

            int hash = StateHashMap.hashKey(child.getBoard());
            if (!visitedNode.containsKey(hash)) {
                currentBoardInMemory++;
                allState++;
                if (currentBoardInMemory > maxBoardInMemory)
                    maxBoardInMemory = currentBoardInMemory;
                visitedNode.put(hash, child);
                int h = heuristic.estimate(child, solutionPositionLis);
                int value;
                if (uniformCost)
                    value = child.getRealDistance();
                else if (greedySearch)
                    value = h;
                else
                    value = child.getRealDistance() + h;
                child.setTotalDistance(value);
                child.setHeuristicDistance(h);
                if (nextCostBound < value)
                    nextCostBound = value;
                validChildren.add(child);

            }
        }

        for (State child: validChildren) {
            if (child.getTotalDistance() <= currentBound  && child.getTotalDistance() < 90) {
                State result = depthFirstSearch(child, realBound + 1);
                if (result != null) {
                    return result;
                }
            }
            visitedNode.remove(StateHashMap.hashKey(child.getBoard()), child);
            currentBoardInMemory--;
        }
        return null;
    }

    public Heuristic getHeuristic() {
        return heuristic;
    }

    public void setHeuristic(Heuristic heuristic) {
        this.heuristic = heuristic;
    }

    public int getTotalMoves() {
        return totalMoves;
    }

    public State getFinalState() {
        return this.finalState;
    }

    public float getTimeComplexity() {
        return timeComplexity;
    }

    public int getMaxBoardInMemory() {
        return maxBoardInMemory;
    }

    public int getAllState() {
        return allState;
    }
}
