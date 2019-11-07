package webEngineering.application.controller.rest;

import org.springframework.web.bind.annotation.*;
import webEngineering.application.form.NPuzzleForm;
import webEngineering.application.project.taquin.Direction;
import webEngineering.application.project.taquin.ExpectedSolutionCalculator;
import webEngineering.application.project.taquinv2.NPuzzle;
import webEngineering.application.project.taquinv2.State;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
	public dataTaquin solve(@RequestBody NPuzzleForm form) {

		NPuzzle puzzle = new NPuzzle(form.getInitialBoard(), form.getExpectedBoard(), form.getDimension(), form.getHeuristicFunction());
		puzzle.initAlgo();
		dataTaquin datas = new dataTaquin();
		datas.finalState = puzzle.resolve();
		State index = datas.finalState;
		datas.totalMove = puzzle.getAlgo().getTotalMoves();
		datas.dimension = puzzle.getDimension();
		datas.indexes = new int[datas.totalMove][datas.dimension][datas.dimension];
		int i = datas.totalMove;
		while (index.getPreviousState() != null && i-- > 0) {
			datas.listDirection.add(index.getDirection());
			datas.indexes[i] = index.getBoardAsMatrix();
			index = index.getPreviousState();
		}
		Collections.reverse(datas.listDirection);

		return datas;
	}

	class dataTaquin{

		int dimension;

		State finalState;

		List<Direction> listDirection= new ArrayList<>();

		int totalMove;

		int[][][] indexes;

		public int[][][] getIndexes() {
			return indexes;
		}

		public void setIndexes(int[][][] indexes) {
			this.indexes = indexes;
		}

		public void setFinalState(State finalState) {
			this.finalState = finalState;
		}

		public void setListDirection(List<Direction> listDirection) {
			this.listDirection = listDirection;
		}

		public State getFinalState() {
			return finalState;
		}

		public List<Direction> getListDirection() {
			return listDirection;
		}

		public int getTotalMove() {
			return totalMove;
		}

		public int getDimension() {
			return dimension;
		}

	}
}