package com.snap.chef.controller;

import java.net.ConnectException;
import java.util.*;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.snap.chef.properties.ConfigProperties;

public class LdapAuth {
	
	ConfigProperties configProperties;
	Logger logger = LoggerFactory.getLogger(LdapAuth.class);
	public int verifyUser(String username,String password) throws NamingException,ConnectException,InterruptedException{
	
	configProperties = new ConfigProperties();	
	String initCtx="com.sun.jndi.ldap.LdapCtxFactory"; 
	String myHost=configProperties.getLdapurl();
	String my_SearchBase = "DC=jasperindia,DC=local";	
	String email=username+"@snapdeal.com";
	String my_Filter = "mail="+email; 
	DirContext ctx=null;
	NamingEnumeration results=null;
	String jssecacertPath=configProperties.getLdapjsseCacertpath();
	Properties systemProps = System.getProperties();
	systemProps.put( "javax.net.ssl.trustStore", jssecacertPath);
	System.setProperties(systemProps);
	Hashtable<String, String> h=new Hashtable<String, String>();
	h.put(Context.INITIAL_CONTEXT_FACTORY, initCtx);
	h.put(Context.PROVIDER_URL,myHost);
	h.put(Context.SECURITY_PRINCIPAL,username+"@jasperindia.local");
	h.put(Context.SECURITY_CREDENTIALS, password);
	h.put(Context.SECURITY_PROTOCOL, "ssl");
	try{
	ctx =new InitialDirContext(h);
	SearchControls c=new SearchControls();
	c.setSearchScope(SearchControls.SUBTREE_SCOPE);
	 
	 results=ctx.search(my_SearchBase, my_Filter,c);
	
	if(results!=null && results.hasMoreElements()){
		return 1;}
	else {return 0;}}
		catch (NamingException e) {
			logger.error("NamingException",e);
		return 0;
	} finally {
	if (results != null) {
	try {
	results.close();
	} catch (Exception e) {
		logger.error("Exception",e);
	}
	}
	if (ctx != null) {
	try {
	ctx.close();
	} catch (Exception e) {
		logger.error("Exception",e);
	}
	}
	}
	}
	

}
