package webEngineering.application.controller.rest;

import org.springframework.web.bind.annotation.*;
import webEngineering.application.form.NPuzzleForm;
import webEngineering.application.project.taquin.ExpectedSolutionCalculator;
import webEngineering.application.project.taquin.algo.IDAStar;
import webEngineering.application.project.taquin.algo.NPuzzleResolver;

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
	public NPuzzleResolver solve(@RequestBody NPuzzleForm form) {

		NPuzzleResolver resolver = new IDAStar(form);
		resolver.resolve();
		return resolver;
	}
}