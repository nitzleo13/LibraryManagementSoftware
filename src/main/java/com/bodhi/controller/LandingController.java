package com.bodhi.controller;
 
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
 
@Controller
public class LandingController {
	
	@RequestMapping(value = { "/admin/home" })
	public ModelAndView login(
	@RequestParam(value = "username", required = false) String username,
	@RequestParam(value = "password", required = false) String password) {
		
		System.out.println("in landing controller"); 
		ModelAndView mv = new ModelAndView("bodhi_landing");
		
		return mv;
	}
	
}