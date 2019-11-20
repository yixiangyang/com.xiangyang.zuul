package com.xiangyang.zuul.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	
	@RequestMapping(value = "/v1/aaa", method = RequestMethod.GET)
	public String getConsumesAchievement() {
		return "aaaa";
	}
}
