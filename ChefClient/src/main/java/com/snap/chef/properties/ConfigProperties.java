package com.snap.chef.properties;

import java.io.FileNotFoundException;

import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigProperties{
	
	public InputStream inputStream;
	public Properties properties;
	public String url;
	public String keyPath;
	public String jssecacertPath;
	public String ldapurl;
	public String ldapjsseCacertpath;
	private Logger logger = LoggerFactory.getLogger(ConfigProperties.class);
	public ConfigProperties() {
		// TODO Auto-generated constructor stub
		try {
			properties = new Properties();
			String propertyFileName = "chefpanel.properties";  
			inputStream = getClass().getClassLoader().getResourceAsStream(propertyFileName);
			if (inputStream != null) {
				properties.load(inputStream);
			} else {
				throw new FileNotFoundException("Property file '" + propertyFileName + "' not found in the classpath");
			}

		} catch (Exception e) {
			logger.error("Property values error", e);
		} 

	}
	public String getUrl() {
		return properties.getProperty("chef.url");
	}
	
	public String getKeyPath() {
		return properties.getProperty("chef.key");
	}
	
	public String getJssecacertPath() {
		return properties.getProperty("chef.jssecacertpath");
	}
	public String getLdapurl() {
		return properties.getProperty("ldap.url");
	}
	public String getLdapjsseCacertpath() {
		return properties.getProperty("ldap.jssecacertpath");
	}
	
	
	


	
	



	
	

}
