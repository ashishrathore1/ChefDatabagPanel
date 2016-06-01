package com.snap.chef.test;

import java.util.Properties;

import org.json.JSONObject;

import com.snap.chef.chefapi.ChefApiClient;
import com.snap.chef.chefapi.method.ApiMethod;

public class Check {

    //private static String CHEF_NODE_STR = "{  \"name\": \"latte\" }";// "{ \"id\": \"item_upgrade\",\"url\":\"hello\"}"
	private static String CHEF_NODE_STR = "{ \"id\": \"item_upgrade4\" }";
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		String jssecacertPath="/home/ashish/chef_ldap/jssecacerts";
		Properties systemProps = System.getProperties();
		systemProps.put( "javax.net.ssl.trustStore", jssecacertPath);
		System.setProperties(systemProps);
		
		ChefApiClient cac = new ChefApiClient("dbaccess", "/home/ashish/chef/dbaccess.pem", "https://46.51.219.215");

		ApiMethod am =cac.get("/organizations/snapdeal_devops/data/upgradetesting/item_upgrade4");
		//ApiMethod am =cac.get("/organizations/snapdeal_devops/data/staging");
		int code = am.execute().getReturnCode();
		String xx = am.getResponseBodyAsString();
		System.out.println(code+"  \n"+xx);
		
		
		/*String itemKey="fullname";
		String itemValue="ashi";
		JSONObject responseJson = new JSONObject(xx);
		responseJson.put(itemKey,itemValue);
		System.out.println(responseJson.toString());
		
		int res = cac.put("/organizations/snapdeal_devops/data/upgradetesting/item_upgrade2").body(responseJson.toString()).execute().getReturnCode();
		System.out.println(res);*/
		
		/*String res = cac.delete("/organizations/snapdeal_devops/data/upgradetesting/item_upgrade4").execute().getResponseBodyAsString();
		System.out.println(res);*/
		
		/*String res = cac.post("/organizations/snapdeal_devops/data/upgradetesting/item_upgrade4").body(CHEF_NODE_STR).execute().getResponseBodyAsString();
		System.out.println(res);*/
		
		/*int res = cac.post("/organizations/snapdeal_devops/data/upgradetesting").body(CHEF_NODE_STR).execute().getReturnCode();
		System.out.println(res);*/
		
	}

}
