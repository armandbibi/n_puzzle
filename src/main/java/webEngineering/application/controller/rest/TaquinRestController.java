package webEngineering.application.controller.rest;

import org.springframework.web.bind.annotation.*;
import webEngineering.application.form.NPuzzleForm;
import webEngineering.application.project.taquin.AStar;
import webEngineering.application.project.taquin.ExpectedSolutionCalculator;

@RestController
@RequestMapping("/v1/n-puzzle")
public class TaquinRestController {
	
	@PostMapping("/solvable")
	public boolean isSolvable() {
		return false;
	}
	
	@GetMapping("/board/solution{name}{dimension}")
	public int[][] getSolutionBoard(@RequestParam("name") String name, @RequestParam("dimension") int dimension) {
		
		ExpectedSolutionCalculator solution = ExpectedSolutionCalculator.valueOf(name);
		return solution.getSolution(dimension);
	}

	@PostMapping("/solve")
	public AStar solve(@RequestBody NPuzzleForm form) {

		AStar aStar = new AStar(form);
		aStar.resolve();
		return aStar;
	}
}