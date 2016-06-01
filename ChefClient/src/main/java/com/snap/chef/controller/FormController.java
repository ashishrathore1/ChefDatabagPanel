package com.snap.chef.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.snap.dao.FormDao;
import com.snap.dao.FormDaoImpl;

@Controller
public class FormController {
	
	@Autowired 
	FormDaoImpl form;
	String username;
	
	@RequestMapping(value="/welcome**" , method=RequestMethod.POST)
	public ModelAndView dataBag(HttpServletRequest request){
		ModelAndView model = new ModelAndView();
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("username");
		List<String> allDataBag = form.getAllDataBag(username);
		model.addObject("allDataBag", allDataBag);
		model.setViewName("HomeDataBag");
		return model;
	} 
	
	@RequestMapping(value="/dataItems" , method=RequestMethod.POST)
	public ModelAndView dataItems(@RequestParam("databag")String databag,HttpServletRequest request){
		ModelAndView model = new ModelAndView();
		String msg = "Select Databag "; 
		if(databag.equals("--select-databag")){
			model.addObject("msg", msg);
			model.setViewName("inserted");
		}else{
			HttpSession session = request.getSession();
			String username = (String)session.getAttribute("username");
			List<String> allDataBagItems = form.getAllDataBagItems(username,databag);
			model.addObject("allDataBagItems", allDataBagItems);
			model.addObject("databag",databag);
			model.setViewName("HomeDataItems");
		}
		
		return model;
	} 
	
	

}
