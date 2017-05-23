package com.india.cservices.net;

public class OutputResponse {

	public Exception getException() {
		return Exception;
	}

	public void setException(Exception exception) {
		Exception = exception;
	}

	Exception Exception;
	ContentType contentType;
	
	public ContentType getContentType(){
		return contentType;
	}
	
	public void setContentType(String strContentType){
		if(strContentType.contains("json")){
			contentType = ContentType.JSON;
		}else if(strContentType.contains("xml")){
			contentType = ContentType.XML;
		} else {
			contentType = ContentType.Other;
		}
	}
}
