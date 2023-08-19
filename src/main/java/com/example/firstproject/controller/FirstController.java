package com.example.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
//import ch.qos.logback.core.model.Model;

@Controller
public class FirstController {
	 @GetMapping("/HI")
	    public String niceToMeetYou(Model model) {
	        model.addAttribute("username", "한유진");
	        return "greetings";
	    }
	 @GetMapping("/bye")
	 public String seeYou (Model model) {
		 model.addAttribute("username", "한유진");
		 
		 return "goodbye";
		 
	 }


}
