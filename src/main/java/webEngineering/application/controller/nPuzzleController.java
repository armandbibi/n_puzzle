package webEngineering.application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/taquin")
public class nPuzzleController {

	@GetMapping("/display")
	public String getViewForThis(Model model) {

		return "/layouts/main_layout";
	}
}