package com.mudigal.support.controllers;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mudigal.support.managedObjects.CaseMO;
import com.mudigal.support.managedObjects.SearchParamMO;
import com.mudigal.support.managedObjects.UpdateCaseMO;
import com.mudigal.support.services.CaseService;

/**
 * Handles REST web service requests
 */
@Controller
@RequestMapping("/caserc")
public class CaseRestController {
	private static final Logger logger = LoggerFactory
			.getLogger(CaseRestController.class);

	private CaseService caseService;

	@Autowired
	public void setCaseService(CaseService caseService) {
		this.caseService = caseService;
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String getSearchPage(Model model) {
		logger.debug("Received request to show search page");

		// Create new SearchParam and add to model
		// This is the formBackingOBject
		model.addAttribute("searchAttribute", new SearchParamMO());

		// This will resolve to /WEB-INF/jsp/search-page.jsp
		return "search-page";
	}

	@RequestMapping(value = "/search/cases", method = RequestMethod.POST)
	@ResponseBody
	public Collection<CaseMO> searchCaseByParam(
			@ModelAttribute("searchAttribute") SearchParamMO param) {

		logger.info("In searchCaseByParam Page");
		return this.caseService.getCaseByParam(param.getCaseStatus(), param
				.getKeyword().isEmpty() ? null : param.getKeyword(), param
				.getCreateDate().isEmpty() ? null : param.getCreateDate());

	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String getUpdatePage(Model model) {
		logger.debug("Received request to show update page");

		// Create new SearchParam and add to model
		// This is the formBackingOBject
		model.addAttribute("updateAttribute", new UpdateCaseMO());

		// This will resolve to /WEB-INF/jsp/update-page.jsp
		return "update-page";
	}

	@RequestMapping(value = "/update/cases", method = RequestMethod.POST)
	@ResponseBody
	public Integer updateCaseByfield(
			@ModelAttribute("updateAttribute") UpdateCaseMO updateCaseMO) {

		logger.info("In updateCaseByfield Page");
		return this.caseService.updateCaseByfield(updateCaseMO);

	}

}
