package com.snap.chef.controller;

import java.net.ConnectException;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class LoginController {
	String viewName;
	private Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@RequestMapping(value="checkUser",method=RequestMethod.GET)
	public ModelAndView verifyUserget(HttpServletRequest request){
		ModelAndView model = new ModelAndView();
		HttpSession session = request.getSession();
		if(session.getAttribute("username")!=null)
		{
		   viewName="Home";
		}else{
			viewName="InvalidCreds";
		}
		model.setViewName(viewName);
		return model;
		
	}
	
	
	
	@RequestMapping(value="checkUser",method=RequestMethod.POST)
	public ModelAndView verifyUser(@RequestParam("username")String username,@RequestParam("password") String password,HttpServletRequest request){
		ModelAndView model = new ModelAndView();
		LdapAuth ldapAuth = new LdapAuth();
		HttpSession session = null;
		int responseCode=0;
		try {
			responseCode = ldapAuth.verifyUser(username, password);
		} catch (ConnectException e) {
			logger.error("ConnectException",e);
		} catch (NamingException e) {
			logger.error("NamingException",e);
		} catch (InterruptedException e) {
			logger.error("InterruptedException",e);
		}
		
		if(responseCode == 1){
			session = request.getSession();
			viewName="Home";
			session.setAttribute("username", username);
		}else{
			logger.error("Your Credentials are wrong.verify it");
			viewName="InvalidCreds";
		}
		
		model.setViewName(viewName);
		return model;
	}
	
	@RequestMapping(value={"/"},method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request){
		ModelAndView model = new ModelAndView();
		HttpSession session = request.getSession();
		if(session.getAttribute("username")!=null)
		{
		   model.setViewName("Home");
		}
		else{
		model.setViewName("login");
		}
		return model;
	}
	
	@RequestMapping(value={"/GOTOHOME**"})
	public ModelAndView homePage(){
		ModelAndView model = new ModelAndView();
		model.setViewName("Home");
		return model;
	}
	
	@RequestMapping(value={"/GOTOLOGIN**"})
	public ModelAndView loginPage(){
		ModelAndView model = new ModelAndView();
		model.setViewName("login");
		return model;
	}
	
	@RequestMapping(value={"/GOTOLOGOUT**"})
	public ModelAndView logoutControl(HttpServletRequest request){
		ModelAndView model = new ModelAndView();
		HttpSession session = request.getSession();
		session.setAttribute("username", null);
		session.invalidate();
		model.setViewName("login");
		return model;
	}
	
	

}
