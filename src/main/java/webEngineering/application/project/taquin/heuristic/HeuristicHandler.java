package webEngineering.application.project.taquin.heuristic;

import webEngineering.application.project.taquin.State;

public class HeuristicHandler implements Heuristic<State> {

    List<Heuristic> heuristicList = new ArrayList<>();

    public HeuristicHandler ()
    {
        heuristicList.add(new OptimizedManhattan());
        heuristicList.add(new MisplacedTile());
        heuristicList.add(new EuclidianDist());
    }

    @Override
    public int estimate(State currentState, State expectedState) {
        return (heuristicList.get(0).estimate(currentState, expectedState) + heuristicList.get(1).estimate(currentState, expectedState) + heuristicList.get(2).estimate(currentState, expectedState));
    }
}
