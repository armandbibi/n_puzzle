package webEngineering.application.project.taquin.algo;

import webEngineering.application.form.NPuzzleForm;
import webEngineering.application.project.taquin.NPuzzle;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class IDAStar extends NPuzzleResolverImp {

    int exploredNode = 0;
    List<NPuzzle> visitedNodeDuringRecursion = new ArrayList<>();

    public IDAStar(NPuzzleForm form) {
        super(form);
    }

    @Override
    public NPuzzle resolve() {

        NPuzzle start = pQueue.peek();
        int nextCostBound = heuristic.estimate(start, this);


        while (solvedPuzzle == null) {
            int currentBound = nextCostBound;
            solvedPuzzle = depthFirstSearch(start, currentBound, goalState);
            nextCostBound += 2;
        }
        return solvedPuzzle;
    }

    private NPuzzle depthFirstSearch(NPuzzle puzzle, int currentBound, int[][] goalState) {
        if (puzzle.getHeuristicDistance() == 0) {
            return puzzle;
        }

        exploredNode++;

        ArrayList<NPuzzle> children = puzzle.visit();
        for (NPuzzle child : children) {

            int h = heuristic.estimate(child, this);
            child.setDistance(h + child.getDistance());
            child.setHeuristicDistance(h);
        }

        children.sort(new Comparator<NPuzzle>() {
            @Override
            public int compare(NPuzzle nPuzzle, NPuzzle t1) {
                return  nPuzzle.getHeuristicDistance() - t1.getHeuristicDistance();
            }
        });

        for (NPuzzle child: children) {
                if (!visitedNodeDuringRecursion.contains(child)) {

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
}
