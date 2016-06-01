package com.snap.chef.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.snap.dao.FormDaoImpl;

@Controller
public class RedirectControllerDataItem {

	@Autowired
	FormDaoImpl form ;
	ChefController chefController;
	
	@RequestMapping(value="/viewDataBagItem", method=RequestMethod.GET )
	public ModelAndView getViewKeyValue(){
		ModelAndView model = new ModelAndView();
		model.setViewName("Home");
		return model;
	}

	
	@RequestMapping(value="/viewDataBagItem", method=RequestMethod.POST )
	public ModelAndView viewKeyValue(@RequestParam("databag") String databag,@RequestParam("item") String item,HttpServletRequest request){
		ModelAndView model = new ModelAndView();
		chefController = new ChefController();
		String msg = "Select Proper DataItem  "; 
		if(item.equals("--select-Items")){
			model.addObject("msg", msg);
			model.setViewName("inserted");
		}
		else{		
			request.setAttribute("databag", databag);
			request.setAttribute("item", item);
			
			int status = chefController.viewStatus(databag, item);
			
			if(status == 200){
			model.setViewName("display");
			}
			else{
				msg = Integer.toString(status)+":Databag or DataItem does not exists";
				model.addObject("msg", msg);
				model.setViewName("inserted");		
			}
				
		}
			
		return model;
	}
	
	@RequestMapping(value="/newDataItem", method=RequestMethod.POST )
	public ModelAndView newDataItem(@RequestParam("databag") String databag,@RequestParam("newItem") String item,HttpServletRequest request){
		ModelAndView model = new ModelAndView();
		
		String msg = "Select Proper DataItem  "; 
		chefController = new ChefController();
		int responseCode = chefController.insertNewDataItem(databag, item);
		if(responseCode == 201){
			form.provideAccess((String)request.getSession().getAttribute("username"), databag, item);
			msg="Item Inserted Successfully";
		}else if(responseCode == 409){
			msg = "Item already exists in the chosen databag";
		}else {
			msg="Some error occured";
		}
		model.addObject("msg", msg);
		model.setViewName("inserted");
						
		return model;
	}
	
	
	
	
}
