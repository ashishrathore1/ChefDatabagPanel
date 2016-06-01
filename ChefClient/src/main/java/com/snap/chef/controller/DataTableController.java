package com.snap.chef.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.snap.dao.DataItems;
import com.snap.dao.FormDaoImpl;




@Controller
public class DataTableController {

	HashMap<String, Object> JSONROOT = new HashMap<String, Object>();
	Pattern pattern;
	Matcher matcher;
	ChefController chefController ;
	ICsvBeanWriter csvWriter;
	String key;
	String value;
	int code;
	
	@Autowired
	FormDaoImpl formDaoImpl;
	
	@RequestMapping(value="downloadCSV", method=RequestMethod.POST)
	public void downloadCSV(HttpServletRequest request,HttpServletResponse response){
		List<DataItems> dataItemtList = new ArrayList<DataItems>();
		String csvFileName = request.getParameter("item")+"_keyvalue.csv";
		response.setContentType("text/csv");
		String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                csvFileName);
        response.setHeader(headerKey, headerValue);
       
			chefController = new ChefController();
			Map<String,String> keyValue = chefController.viewKeyValueWithId(request.getParameter("databag"), request.getParameter("item"));
			for (Map.Entry<String, String> entry : keyValue.entrySet())
			{
				dataItemtList.add(new DataItems(entry.getKey(), entry.getValue()));
			}
			
			try {
				csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE );
				String[] header = { "Key", "Value" };
				csvWriter.writeHeader(header);
				
				for(DataItems dataItem : dataItemtList){
					csvWriter.write(dataItem, header);
				}
				
				csvWriter.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
        
		
	}
	
	
	
	@RequestMapping(value="/Controller**", method=RequestMethod.POST )
	public void fetchValues(HttpServletRequest request,HttpServletResponse response){
		
		String action = request.getParameter("action");
		String databag = request.getParameter("databag");
		String item = request.getParameter("item");
		String username = (String) request.getSession().getAttribute("username");
		Map<String, String> responseContainer;
		
		if ( action != null) 
		{
			List<DataItems> dataItemtList = new ArrayList<DataItems>();
			Gson gson  = new GsonBuilder().setPrettyPrinting().create();
			response.setContentType("application/json");

			if (action.equals("list")) 
			{
				chefController = new ChefController();
				
					try {
						
						Map<String,String> keyValue = chefController.viewKeyValue(databag, item);
						for (Map.Entry<String, String> entry : keyValue.entrySet())
						{
							dataItemtList.add(new DataItems(entry.getKey(), entry.getValue()));
						}
							JSONROOT.put("Result", "OK");
							JSONROOT.put("Records", dataItemtList);
							String jsonArray = gson.toJson(JSONROOT);
							response.getWriter().print(jsonArray);
						
						
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				
			}
			
			
			if(action.equals("create") || action.equals("update")){
				code=0;
				try{
					DataItems dataItems = new DataItems();					
					if(request.getParameter("key") != null && request.getParameter("value") != null){
						String tmpString = request.getParameter("key");
						tmpString = tmpString.trim();
						pattern = Pattern.compile("[^A-Za-z0-9_]");
					    matcher = pattern.matcher(tmpString);
					    boolean flag = matcher.find();
					    if(flag == true){
					    	JSONROOT.put("Result", "ERROR");
							JSONROOT.put("Message", "Key cannot have special character other than '_' ");
							String jsonArray = gson.toJson(JSONROOT);
					        response.getWriter().print(jsonArray);
					    	return;
					    }
					    
						key = tmpString;
						dataItems.setKey(key);
						value = request.getParameter("value");
						value = value.trim();
						dataItems.setValue(value);
					}
					else{
						JSONROOT.put("Result", "ERROR");
						JSONROOT.put("Message", "Give Proper Name");
						String jsonArray = gson.toJson(JSONROOT);
				        response.getWriter().print(jsonArray);
						return;
					}
					
					if(action.equals("update")){
						responseContainer = chefController.updateKeyValue(databag, item, key, value);
						if(responseContainer.get("code").equals("200")){
							formDaoImpl.activityLog(username, databag, item, key, responseContainer.get("prev_value"), value, "updated");
						}
					}else{
						code = chefController.createKeyValue(databag,item,key,value);
						if(code == 200){
							formDaoImpl.activityLog(username, databag, item, key, "null", value, "created");
						}
						
					}
					
					if(!key.equals("RestrictedKeyDoNotAdd")){
					JSONROOT.put("Result", "OK");
					JSONROOT.put("Record", dataItems);
					String jsonArray = gson.toJson(JSONROOT);
			        response.getWriter().print(jsonArray);
			        }
					
				}catch(Exception ex){
					ex.printStackTrace();
				}
				
				
			}
			
			if(action.equals("delete")){
				try{
					
					if(request.getParameter("key") != null){
						key = request.getParameter("key");
						responseContainer = chefController.delete(databag, item,key);
						
						if(responseContainer.get("code").equals("200")){
							formDaoImpl.activityLog(username, databag, item, key,responseContainer.get("prev_value"),"null", "deleted");
							JSONROOT.put("Result", "OK");
							String jsonArray = gson.toJson(JSONROOT);
			                response.getWriter().print(jsonArray);
						}
							
					}	
				}catch(Exception ex){
					ex.printStackTrace();
				}
				
			}
			
			
		}
	
}
}
