package com.snap.chef.chefapi.method;

import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.PostMethod;

public class Post extends ApiMethod {

	public Post(HttpMethod method) {
		super("POST");
		this.method = method;
	}
	
	@SuppressWarnings("deprecation")
	public ApiMethod body(String body){
		this.reqBody = body;
		PostMethod post = (PostMethod) method;
		post.setRequestBody(body);
		return this;
	}

}
