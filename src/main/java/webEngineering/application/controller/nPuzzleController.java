package webEngineering.application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;

@Controller
@RequestMapping("/projects/taquin")
public class nPuzzleController {

	@GetMapping("")
	public String getViewForThis(Model model) {

		return "/layouts/main_layout";
	}

	@PostConstruct
	public void hello() {
		System.out.println("hello Controller");
	}
}