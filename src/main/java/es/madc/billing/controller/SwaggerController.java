package es.madc.billing.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class SwaggerController {

	@RequestMapping("/")
	public ModelAndView swaggerUI() {
		return new ModelAndView("redirect:swagger-ui.html");
	}
	
}
