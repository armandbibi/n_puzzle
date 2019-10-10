package webEngineering.webEngineering;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import webEngineering.webEngineering.project.taquin.AStar;
import webEngineering.webEngineering.project.taquin.ExpectedSolutionCalculator;
import webEngineering.webEngineering.project.taquin.NPuzzle;
import webEngineering.webEngineering.project.taquin.Parser;

@SpringBootApplication
public class WebEngineeringApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(WebEngineeringApplication.class, args);
		
		Parser parser = new Parser(args);
		NPuzzle puzzle = parser.parse();
		int[][] solution = ExpectedSolutionCalculator.CLASSIC.getSolution(4);
		for (int i = 0; i < 4;i++) {
			for (int j = 0; j < 4; j++) {
				System.out.print(solution[i][j] + " ");
			}
			System.out.println();
		}
	}

}