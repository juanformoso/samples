package com.seniorgeek.samples.jetty_springmvc_json.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Sample controller
 * 
 * @author jformoso
 */
@Controller
public class MyController {

	@RequestMapping(value = "/data", method = RequestMethod.GET)
	public Object getFlavor() {
		List<String> dummyData = new ArrayList<String>();
		dummyData.add("item1");
		dummyData.add("item2");
		dummyData.add("item3");
		
		return dummyData;
	}
}
