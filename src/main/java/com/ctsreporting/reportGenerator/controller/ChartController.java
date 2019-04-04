package com.ctsreporting.reportGenerator.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ctsreporting.reportGenerator.dto.ParticipationMatrics;
import com.ctsreporting.reportGenerator.service.ChartService;

@RestController
@RequestMapping("/chart")
public class ChartController {
	
	@Autowired
	private ChartService chartService;

	@SuppressWarnings("rawtypes")
	@GetMapping("/participationMatrics")
	public ParticipationMatrics participationMatrics(String location, String bu, Date fromTime, Date toTime) {
		return chartService.participationMatrics(null, null, null, null);
	}

}
