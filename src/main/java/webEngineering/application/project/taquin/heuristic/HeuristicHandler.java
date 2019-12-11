package webEngineering.application.project.taquin.heuristic;

import webEngineering.application.project.taquin.State;
import webEngineering.application.project.taquin.utils.Position;

import java.util.ArrayList;
import java.util.List;

public class HeuristicHandler implements Heuristic<State> {

    List<Heuristic> heuristicList = new ArrayList<>();

    public HeuristicHandler (State expectedState)
    {
        heuristicList.add(new OptimizedManhattan(expectedState.getInternPosition()));
        heuristicList.add(new MisplacedTile());
        heuristicList.add(new EuclidianDist());
    }

    @Override
    public int estimate(State currentState, State expectedState) {
        return (heuristicList.get(0).estimate(currentState, expectedState) + heuristicList.get(1).estimate(currentState, expectedState) + heuristicList.get(2).estimate(currentState, expectedState));
    }
}
