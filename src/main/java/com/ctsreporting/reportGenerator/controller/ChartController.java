package com.ctsreporting.reportGenerator.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/chart")
public class ChartController {
	
	@GetMapping("/participationMatrics")
	public void participationMatrics(String location, String bu, Date fromTime, Date toTime) {
		
	}

}
