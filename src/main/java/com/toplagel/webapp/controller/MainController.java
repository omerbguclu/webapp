package com.toplagel.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController extends ControllerCommon {

	@GetMapping("/index")
	public String index() {
		return "index";
	}

}
