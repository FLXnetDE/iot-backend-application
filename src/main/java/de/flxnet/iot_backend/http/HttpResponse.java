package de.flxnet.iot_backend.http;

import de.flxnet.iot_backend.util.JsonUtil;

/**
 * Software by FLXnet
 * More info at FLXnet.de
 * Copyright (c) 2015-2019 by FLXnet
 * @author Felix
 */
public class HttpResponse {
    
    private HttpResponseCode code;
    private Object message;
    
    public HttpResponse(HttpResponseCode code, Object message) {
	this.code = code;
	this.message = message;
    }
    
    /**
     * @return the code
     */
    public HttpResponseCode getCode() {
	return code;
    }
    
    /**
     * @param code the code to set
     */
    public void setCode(HttpResponseCode code) {
	this.code = code;
    }
    
    /**
     * @return the message
     */
    public Object getMessage() {
	return message;
    }
    
    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
	this.message = message;
    }
    
    /**
     * @return String
     */
    public String asJsonString() {
    	return JsonUtil.getGson().toJson(this);
    }
    
}