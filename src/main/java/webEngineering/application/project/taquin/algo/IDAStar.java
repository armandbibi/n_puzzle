package webEngineering.application.project.taquin.algo;

import webEngineering.application.form.NPuzzleForm;
import webEngineering.application.project.taquin.NPuzzle;
import webEngineering.application.project.taquin.NPuzzleComparator;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class IDAStar extends NPuzzleResolverImp {

    int exploredNode = 0;
    List<NPuzzle> visitedNodeDuringRecursion = new ArrayList<>();


    public IDAStar(NPuzzleForm form) {
        super(form);
    }

    @Override
    public NPuzzle resolve() {

        NPuzzle start = pQueue.peek();
        int nextCostBound = heuristic.estimate(start, goalState);
        long exploredPuzzle = 0;

        while (solvedPuzzle == null) {
            int currentBound = nextCostBound;
            solvedPuzzle = depthFirstSearch(start, currentBound, goalState);
            nextCostBound += 2;
        }
        return solvedPuzzle;
    }

    private NPuzzle depthFirstSearch(NPuzzle puzzle, int currentBound, int[][] goalState) {
        if (puzzle.isAs(goalState)) {
            return puzzle;
        }

        exploredNode++;

        ArrayList<NPuzzle> children = puzzle.visit(heuristic, goalState);

        for (NPuzzle child: children) {
                if (!haveBeenVisited(child)) {
                visitedNodeDuringRecursion.add(child);
                int value = child.getDistance();
                if (value <= currentBound) {
                    NPuzzle result = depthFirstSearch(child, currentBound, goalState);
                    if (result != null) {
                        return result;
                    }
                }
                visitedNodeDuringRecursion.remove(child);
            }
        }
        return null;
    }

    private boolean haveBeenVisited(NPuzzle child) {
        for(NPuzzle p : visitedNodeDuringRecursion)
            if (p.isAs(child.getBoard()))
                return true;
            return false;
    }
}
