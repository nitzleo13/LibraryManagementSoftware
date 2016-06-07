package com.bodhi.controller;
 
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
 
@Controller
public class LoginController {
 
	@RequestMapping("/login")
	public ModelAndView landing() {
		System.out.println("in login controller"); 
		ModelAndView mv = new ModelAndView("bodhi_login");
		return mv;
	}
}