package com.snap.chef.properties;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ItemProperties {
	public InputStream inputStream;
	public Properties properties;
	public String url;
	public String keyPath;
	public String jssecacertPath;
	public String ldapurl;
	public String ldapjsseCacertpath;
	private Logger logger = LoggerFactory.getLogger(ConfigProperties.class);
	public ItemProperties() {
		// TODO Auto-generated constructor stub
		try {
			properties = new Properties();
			String propertyFileName = "item.properties";  
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
	public List<String> getItems() {
		String item=properties.getProperty("item.list");
		List<String> items = Arrays.asList(item.split("\\s*,\\s*"));
		return items;
	}
	public List<String> getDatabags() {
		String item=properties.getProperty("databag.list");
		List<String> items = Arrays.asList(item.split("\\s*,\\s*"));
		return items;
	}
	
}
