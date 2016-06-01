package com.snap.chef.controller;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;

import org.json.JSONObject;
import com.snap.chef.chefapi.ChefApiClient;
import com.snap.chef.chefapi.method.ApiMethod;
import com.snap.chef.properties.ConfigProperties;
import com.snap.chef.properties.ItemProperties;



public class ChefController {
	
	HashMap<String, String> keyValue;
	HashMap<String, String> statusMap;
	String response;
	JSONObject responseJson;
	ConfigProperties properties = new ConfigProperties();
	
	
	
	
	public HashMap<String, String> viewKeyValueWithId(String databag,String item){
		
		ItemProperties items=new ItemProperties();
	    List<String> secrets=new ArrayList<String>();
	    List<String> secretDatabags=new ArrayList<String>();
	    secretDatabags=items.getDatabags();
	    secrets=items.getItems();
	    keyValue = new HashMap<String,String>();
		String jssecacertPath = properties.getJssecacertPath();
		String keypath = properties.getKeyPath();
		String chefUrl = properties.getUrl();
		Properties systemProps = System.getProperties();
		systemProps.put( "javax.net.ssl.trustStore", jssecacertPath);
		System.setProperties(systemProps);
		ChefApiClient cac = new ChefApiClient("dbaccess", keypath, chefUrl);
		String getRequest= "/organizations/snapdeal_devops/data/"+databag +"/"+item;
		ApiMethod am =cac.get(getRequest);
		int code = am.execute().getReturnCode();
		response = am.getResponseBodyAsString();
		responseJson = new JSONObject(response);
		Iterator<?> iterator = responseJson.keys();
		while(iterator.hasNext()){
			String key = (String)iterator.next();
			String value = responseJson.getString(key);
			int flag=0;
			for(String secretdb:secretDatabags){
			if(databag.equals(secretdb)){
			for(String word: secrets){
				String pattern = ".*"+word+".*";
				boolean matches = Pattern.matches(pattern, key);
				if(matches){
					
							flag=1;
							break;}
						}
				}
				
			}
			
			if(flag == 0){
				keyValue.put(key,value);
			}
			else if(flag==1){
				keyValue.put(key,"**");
			}
			
		}
		return keyValue;
	
}
	
	public int viewStatus(String databag,String item){
		String jssecacertPath = properties.getJssecacertPath();
		String keypath = properties.getKeyPath();
		String chefUrl = properties.getUrl();
		Properties systemProps = System.getProperties();
		systemProps.put( "javax.net.ssl.trustStore", jssecacertPath);
		System.setProperties(systemProps);
		ChefApiClient cac = new ChefApiClient("dbaccess", keypath, chefUrl);
		String getRequest= "/organizations/snapdeal_devops/data/"+databag +"/"+item;
		ApiMethod am =cac.get(getRequest);
		int code = am.execute().getReturnCode();
		
		return code;
		
	}
	
	public HashMap<String, String> viewKeyValue(String databag,String item){
		    ItemProperties items=new ItemProperties();
		    List<String> secrets=new ArrayList<String>();
		    List<String> secretDatabags=new ArrayList<String>();
		    secretDatabags=items.getDatabags();
		    secrets=items.getItems();
		
			String id="id";
			keyValue = new HashMap<String,String>();
			String jssecacertPath = properties.getJssecacertPath();
			String keypath = properties.getKeyPath();
			String chefUrl = properties.getUrl();
			Properties systemProps = System.getProperties();
			systemProps.put( "javax.net.ssl.trustStore", jssecacertPath);
			System.setProperties(systemProps);
			ChefApiClient cac = new ChefApiClient("dbaccess", keypath, chefUrl);
			String getRequest= "/organizations/snapdeal_devops/data/"+databag +"/"+item;
			ApiMethod am =cac.get(getRequest);
			int code = am.execute().getReturnCode();
			response = am.getResponseBodyAsString();
			responseJson = new JSONObject(response);
			responseJson.remove(id);
			Iterator<?> iterator = responseJson.keys();
			while(iterator.hasNext()){
				String key = (String)iterator.next();
				String value = responseJson.getString(key);
				int flag=0;
				for(String secretdb:secretDatabags){
				if(databag.equals(secretdb)){
				for(String word: secrets){
					String pattern = ".*"+word+".*";
					boolean matches = Pattern.matches(pattern, key);
					if(matches){
						
								flag=1;
								break;}
							}
					}	
				}
				if(flag == 0){
					keyValue.put(key,value);
				}
				else if(flag==1){
					keyValue.put(key,"**");

				}
				
			}
			return keyValue;
		
	}
	
	
	
	
	public HashMap<String, String> updateKeyValue(String databag, String item,String itemKey,String itemValue){
		
		ItemProperties items=new ItemProperties();
	    List<String> secrets=new ArrayList<String>();
	    List<String> secretDatabags=new ArrayList<String>();
	    secretDatabags=items.getDatabags();
	    secrets=items.getItems();
		
		HashMap<String, String> responseContainer = new HashMap<String,String>();
		String prev_value=null;
		keyValue = new HashMap<String,String>();
		String jssecacertPath = properties.getJssecacertPath();
		String keypath = properties.getKeyPath();
		String chefUrl = properties.getUrl();
		Properties systemProps = System.getProperties();
		systemProps.put( "javax.net.ssl.trustStore", jssecacertPath);
		System.setProperties(systemProps);
		
		ChefApiClient cac =  new ChefApiClient("dbaccess", keypath, chefUrl);
		
		String url= "/organizations/snapdeal_devops/data/"+databag +"/"+item;
		ApiMethod am =cac.get(url);
		int code = am.execute().getReturnCode();
		response = am.getResponseBodyAsString();
		responseJson = new JSONObject(response);
		prev_value = responseJson.getString(itemKey);
		
		
		
		int flag=0;
		for(String secretdb:secretDatabags){
		if(databag.equals(secretdb)){
		for(String word: secrets){
			String pattern = ".*"+word+".*";
			boolean matches = Pattern.matches(pattern, itemKey);
			if(matches){
				
						flag=1;
						break;}
					}
			}	
		}
		if(flag == 0){
			responseJson.put(itemKey,itemValue);		
			code = cac.put(url).body(responseJson.toString()).execute().getReturnCode();
			responseContainer.put("code",Integer.toString(code) );
			responseContainer.put("prev_value",prev_value );
			return responseContainer;
		}
		else{
			responseJson.put(itemKey,prev_value);		
			code = cac.put(url).body(responseJson.toString()).execute().getReturnCode();
			responseContainer.put("code",Integer.toString(code) );
			responseContainer.put("prev_value",prev_value );
			return responseContainer;
		}
	}


	public int createKeyValue(String databag, String item, String itemKey, String itemValue) {
		ItemProperties items=new ItemProperties();
	    List<String> secrets=new ArrayList<String>();
	    List<String> secretDatabags=new ArrayList<String>();
	    secretDatabags=items.getDatabags();
	    secrets=items.getItems();
	    
		keyValue = new HashMap<String,String>();
		String jssecacertPath = properties.getJssecacertPath();
		String keypath = properties.getKeyPath();
		String chefUrl = properties.getUrl();
		Properties systemProps = System.getProperties();
		systemProps.put( "javax.net.ssl.trustStore", jssecacertPath);
		System.setProperties(systemProps);
		
		ChefApiClient cac =  new ChefApiClient("dbaccess", keypath, chefUrl);
		
		String url= "/organizations/snapdeal_devops/data/"+databag +"/"+item;
		ApiMethod am =cac.get(url);
		int code = am.execute().getReturnCode();
		response = am.getResponseBodyAsString();
		responseJson = new JSONObject(response);
		
		int flag=0;
		for(String secretdb:secretDatabags){
		if(databag.equals(secretdb)){
		for(String word: secrets){
			String pattern = ".*"+word+".*";
			boolean matches = Pattern.matches(pattern, itemKey);
			if(matches){
				
						flag=1;
						break;}
					}
			}	
		}
		if(flag == 0){
			responseJson.put(itemKey,itemValue);
			code = cac.put(url).body(responseJson.toString()).execute().getReturnCode();
			return code;
		}
		else{
			responseJson.put("RestrictedKeyDoNotAdd",itemValue);
			code = cac.put(url).body(responseJson.toString()).execute().getReturnCode();
			return code;
		}
		
	}




	public HashMap<String, String> delete(String databag,String item,String itemKey) {
		
		ItemProperties items=new ItemProperties();
	    List<String> secrets=new ArrayList<String>();
	    List<String> secretDatabags=new ArrayList<String>();
	    secretDatabags=items.getDatabags();
	    secrets=items.getItems();
		
		HashMap<String, String> responseContainer = new HashMap<String,String>();
		String prev_value;
		
		String jssecacertPath = properties.getJssecacertPath();
		String keypath = properties.getKeyPath();
		String chefUrl = properties.getUrl();
		Properties systemProps = System.getProperties();
		systemProps.put( "javax.net.ssl.trustStore", jssecacertPath);
		System.setProperties(systemProps);
		ChefApiClient cac =  new ChefApiClient("dbaccess", keypath, chefUrl);
		
		String url= "/organizations/snapdeal_devops/data/"+databag +"/"+item;
		ApiMethod am =cac.get(url);
		int code = am.execute().getReturnCode();
		response = am.getResponseBodyAsString();
		responseJson = new JSONObject(response);
		prev_value = responseJson.getString(itemKey);
		
		int flag=0;
		for(String secretdb:secretDatabags){
		if(databag.equals(secretdb)){
		for(String word: secrets){
			String pattern = ".*"+word+".*";
			boolean matches = Pattern.matches(pattern, itemKey);
			if(matches){
				
						flag=1;
						break;}
					}
			}	
		}
		if(flag == 0){
			responseJson.remove(itemKey);		
			code = cac.put(url).body(responseJson.toString()).execute().getReturnCode();
			responseContainer.put("code", Integer.toString(code));
			responseContainer.put("prev_value", prev_value);
			
			return responseContainer;
		}
		else{		
			code = cac.put(url).body(responseJson.toString()).execute().getReturnCode();
			responseContainer.put("code", Integer.toString(code));
			responseContainer.put("prev_value", prev_value);
			
			return responseContainer;
		}
		
		
	}
		
	public int insertNewDataItem(String databag,String item){
		String CHEF_NODE_STR = "{ \"id\":\""+item+"\"}";
		String jssecacertPath = properties.getJssecacertPath();
		String keypath = properties.getKeyPath();
		String chefUrl = properties.getUrl();
		Properties systemProps = System.getProperties();
		systemProps.put( "javax.net.ssl.trustStore", jssecacertPath);
		System.setProperties(systemProps);
		ChefApiClient cac =  new ChefApiClient("dbaccess", keypath, chefUrl);
		String url= "/organizations/snapdeal_devops/data/"+databag;
		int responseCode = cac.post(url).body(CHEF_NODE_STR).execute().getReturnCode();
		return responseCode;
		
	}
	

}