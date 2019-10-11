package webEngineering.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import webEngineering.application.project.taquin.AStar;
import webEngineering.application.project.taquin.ExpectedSolutionCalculator;
import webEngineering.application.project.taquin.NPuzzle;
import webEngineering.application.project.taquin.euristicFunction.ManhattanDistance;

@SpringBootApplication
public class WebEngineeringApplication {

	public static void main(String[] args) {

		int[][] startingPuzzle = {
				{
						5,1,8,3
				},
				{
						6,7,2,4
				},
				{
						0,9,10,12
				},
				{
						13,14,11,15
				}
		};


		NPuzzle puzzle = new NPuzzle(4);
		puzzle.setBoard(startingPuzzle);
		int[][] expectedPuzzle = ExpectedSolutionCalculator.SORTEDTABLE.getSolution(4);
		AStar aStar = new AStar(puzzle, expectedPuzzle, new ManhattanDistance());
		aStar.resolve();

		SpringApplication.run(WebEngineeringApplication.class, args);
	}
}