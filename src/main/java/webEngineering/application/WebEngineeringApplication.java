package webEngineering.application;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import webEngineering.application.project.taquin.NPuzzle;
import webEngineering.application.project.taquin.Parser;

import java.io.IOException;

import static org.springframework.boot.SpringApplication.run;


@SpringBootApplication
public class WebEngineeringApplication {

	public static void main(String[] args) {

		Parser parser = new Parser(args);
		NPuzzle puzzle = null;
		try {
			puzzle = parser.parse();
			puzzle.initAlgo();
			puzzle.resolve();
			puzzle.displayConsole();

		} catch (IOException e) {
			System.out.println("wrong parameters - file troubles: " + e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println("wrong parameters - parse troubles: " + e.getMessage());
		}

	//if (args.length == 0 || (puzzle != null && puzzle.shallStartServer()))
			run(WebEngineeringApplication.class, args);
	}
}