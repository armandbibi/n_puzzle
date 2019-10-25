package webEngineering.application;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import webEngineering.application.project.taquin.ExpectedSolutionCalculator;
import webEngineering.application.project.taquinv2.NPuzzle;
import webEngineering.application.project.taquin.algo.AStar;
import webEngineering.application.project.taquin.euristicFunction.ManhattanDistance;
import webEngineering.application.project.taquinv2.heuristic.Manhathan;
import webEngineering.application.project.taquinv2.heuristic.OptimizedManhattan;

import static org.springframework.boot.SpringApplication.run;

@SpringBootApplication
public class WebEngineeringApplication {

	public static void main(String[] args) {

		int[][] startingPuzzle = {
				{
						0,14,13,6
				},
				{
						11,7,2,5
				},
				{
						15,9,10, 1
				},
				{
						12,4,3, 8
				}
		};

		for (int i = 0; i < 15; i++) {

			//int[][] startingPuzzle = ExpectedSolutionCalculator.EASYSHUFFLED_SORTEDTABLE.getSolution(4);
			int[][] expectedPuzzle = ExpectedSolutionCalculator.SORTEDTABLE.getSolution(4);
			NPuzzle puzzle = new NPuzzle(startingPuzzle, expectedPuzzle, 4, new OptimizedManhattan());
			puzzle.initAlgo();
			puzzle.resolve();
		}
		run(WebEngineeringApplication.class, args);
	}
}