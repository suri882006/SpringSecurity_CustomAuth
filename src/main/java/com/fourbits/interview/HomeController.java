package com.fourbits.interview;

import java.awt.PageAttributes.MediaType;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fourbits.interview.model.MyPojo;
import com.fourbits.interview.service.InterviewResource;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private InterviewResource interviewResource;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	
	@RequestMapping(value = "/pathvar/{id}", method = RequestMethod.GET)
	public String homePathVar(Locale locale, Model model, HttpServletRequest req, 
			HttpServletResponse res, @PathVariable("id") int pathId) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate +" server ip -> "+req.getServerName());
		model.addAttribute("pathId",pathId);
		return "home";
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/requestparam", method = RequestMethod.GET)
	public String homeRequestParam(Locale locale, Model model,
			@RequestParam(value="param1", required=true) String param1) {
		logger.info("Welcome homeeeeeeeeeeeeee! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate);
		model.addAttribute("pathId",param1);
		
		return "home";
	}
	
	@RequestMapping(value = "/pojo", method = RequestMethod.POST ,  headers = { "Content-type=application/json" })
	public String homeRequestOptional( @RequestBody MyPojo mypojo, BindingResult result) {
		if(result.hasErrors()) {
			System.out.println("has errrorrrrr");
		}
		System.out.println("id --> "+mypojo.getId()+" "+mypojo.getFname()+" "+mypojo.getLname());
		
		return "home";
	}
	
	/**
	 * Request which contains request headers
	 */
	@RequestMapping(value = "/requestheaders", method = RequestMethod.GET)
	public String homerequestHeader(Locale locale, Model model,
			 HttpServletRequest req, 
			HttpServletResponse res) {
		String username = req.getHeader("username");
		String password = req.getHeader("password");
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate);
		model.addAttribute("pathId","username -> "+username+" password -> "+password);
		
		return "home";
	}
	
	@RequestMapping(value = "/admin**", method = RequestMethod.GET)
	public ModelAndView adminPage() {

		ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring Security");
		model.addObject("message", "This is protected page!");
		model.setViewName("admin");

		return model;

	}
	
	@RequestMapping(value="/eresource/{resourcename}")
	public String getInterviewResource(Model model, @PathVariable("resourcename") String resourceName) {
		
		List<String> iresourceMap = new ArrayList<String>();
		if(resourceName.equals("spring")) {
			iresourceMap = interviewResource.getSpringResource();			
		}
		model.addAttribute("resourcelist",iresourceMap);
		
		return "resourcelist";
		
	}
	
	@RequestMapping(value="/eresourcejson/{resourcename}", headers="Accept=*/*", method= RequestMethod.GET, consumes="application/xml", produces= "application/json")
	public @ResponseBody List<String> getInterviewResourceAsJson(@PathVariable("resourcename") String resourceName) {
		
		List<String> iresourceMap = new ArrayList<String>();
		if(resourceName.equals("spring")) {
			iresourceMap = interviewResource.getSpringResource();			
		}		
		
		return iresourceMap;
		
	}
	
	@RequestMapping(value="/eresourceentity/{resourcename}", headers="Accept=*/*", method= RequestMethod.GET, produces= "application/json")
	public ResponseEntity<List<String>> getInterviewResourceAsEntiry(@PathVariable("resourcename") String resourceName) {
		
		List<String> iresourceMap = new ArrayList<String>();
		if(resourceName.equals("spring")) {
			iresourceMap = interviewResource.getSpringResource();			
		}		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("status", "Bingo");
		return new ResponseEntity<List<String>>(iresourceMap,responseHeaders,HttpStatus.OK);
		
	}
}
