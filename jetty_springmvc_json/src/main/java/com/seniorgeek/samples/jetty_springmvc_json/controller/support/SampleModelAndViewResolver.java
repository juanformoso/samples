package com.seniorgeek.samples.jetty_springmvc_json.controller.support;

import java.lang.reflect.Method;

import org.springframework.ui.ExtendedModelMap;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.annotation.ModelAndViewResolver; 

/**
 * Simple view resolver that adds the returned method to the "data" key in the model
 * 
 * @author jformoso
 */
public class SampleModelAndViewResolver implements ModelAndViewResolver {

	@SuppressWarnings({ "rawtypes" })
	public ModelAndView resolveModelAndView(Method handlerMethod,
			Class handlerType, Object returnValue,
			ExtendedModelMap implicitModel, NativeWebRequest webRequest) {
		
		ModelAndView v = new ModelAndView("jsonView");
		v.addObject("data", returnValue);
		return v;
	}
} 
 