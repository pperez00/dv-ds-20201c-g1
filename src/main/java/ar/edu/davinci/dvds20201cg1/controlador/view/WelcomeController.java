package ar.edu.davinci.dvds20201cg1.controlador.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.davinci.dvds20201cg1.controlador.MyApp;

@Controller
public class WelcomeController extends MyApp{

	@GetMapping()
	public ModelAndView welcome(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("welcome");
		return modelAndView;
	}
}
