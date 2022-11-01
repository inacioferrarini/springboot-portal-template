package com.inacioferrarini.template.portal.sample.samplefeature.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(SampleController.BASE_MAPPING)
public class SampleController {

	public static final String BASE_MAPPING = "/samplefeature";
	public static final String INDEX_MAPPING = "/";

	private static final String INDEX_VIEW = "samplefeature/temp";

	@GetMapping(INDEX_MAPPING)
	public String index() {
		return INDEX_VIEW;
	}

}
