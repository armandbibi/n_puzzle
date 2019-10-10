package webEngineering.webEngineering.controller;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import webEngineering.webEngineering.project.taquin.AStar;
import webEngineering.webEngineering.project.taquin.ExpectedSolutionCalculator;
import webEngineering.webEngineering.project.taquin.NPuzzle;
import webEngineering.webEngineering.project.taquin.euristicFunction.ManhattanDistance;

@Controller
@RequestMapping("/taquin")
public class nPuzzleController {

	@GetMapping("/view")
	public String getProject() {
		return "hello i m here";
	}
	
	@GetMapping("")
	public String getTaquin(Model model) {
		
		NPuzzle puzzle = new NPuzzle(4);
		
	/*	int[][] startingPuzzle = {
				{
					0,1,2,4
				},
				{
					5,7,3,8
				},
				{
					9,6,15,11
				},
				{
					13,10,14,12
				}
				
		};*/

		/*int[][] startingPuzzle = {
				{
					5,1,2,3
				},
				{
					6,0,8,4
				},
				{
					9,10,7,12
				},
				{
					13,14,11,15
				}
		};*/
		
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
		
		
		
		puzzle.setBoard(startingPuzzle);
	/*	
		int[][] expectedPuzzle = {
				{
					1, 2, 3, 4
				},
				{
					5, 6,7,8
				},
				{
					9,10,11,12
				},
				{
					13,14,15,0
				}
		};
		*/
		
		
		int[][] expectedPuzzle = ExpectedSolutionCalculator.SORTEDTABLE.getSolution(4);
		
		AStar aStar = new AStar(puzzle, expectedPuzzle, new ManhattanDistance());
		
		model.addAttribute("taquin", puzzle);

		model.addAttribute("resolved", aStar.resolve());
		model.addAttribute("maxBoardsInMemory", aStar.getMaxBoardInMemory());
		model.addAttribute("totalBoardDuringResolution", aStar.getTotalBoardDuringRun());
		return "/taquin";
	}

	
	@GetMapping("/display")
	public String getViewForThis() {
		return "/template";
	}
	
	
	@PostConstruct
	protected void iamAlive(){
	    System.out.println("Hello AppEcosystemController");
	}
}
