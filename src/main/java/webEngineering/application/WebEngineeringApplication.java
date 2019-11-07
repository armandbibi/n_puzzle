package webEngineering.application;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import webEngineering.application.project.taquinv2.NPuzzle;
import webEngineering.application.project.taquinv2.Parser;
import webEngineering.application.project.taquinv2.State;

import java.io.IOException;

import static org.springframework.boot.SpringApplication.run;


@SpringBootApplication
public class WebEngineeringApplication {

	public static void main(String[] args) {

		Parser parser = new Parser(args);
		try {
			NPuzzle puzzle = parser.parse();
			puzzle.initAlgo();
			State resolved = puzzle.resolve();
			puzzle.displayConsole();

		} catch (IOException e) {
			System.out.println("wrong parameters : " + e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println("wrong parameters: " + e.getMessage());
		}

		run(WebEngineeringApplication.class, args);
	}
}