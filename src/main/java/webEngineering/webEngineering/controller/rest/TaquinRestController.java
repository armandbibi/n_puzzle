package webEngineering.webEngineering.controller.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import webEngineering.webEngineering.project.taquin.ExpectedSolutionCalculator;

@RestController
@RequestMapping("/nPuzzle")
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
	
	
}
