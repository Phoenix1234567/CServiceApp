package com.india.cservices.net;

import java.io.InputStream;

public class OutputResponseStream extends OutputResponse{
	
	
	InputStream inputStream;
	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

}

