package com.online.fileuploading.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ResponseModel <T>{
	private Boolean status;
	private int statusCode;
	private String statusDescription;
	private T data;
	@JsonCreator
	public ResponseModel(@JsonProperty("status")Boolean status,@JsonProperty("statusCode") int statusCode,@JsonProperty("statusDescription") String statusDescription,@JsonProperty("data") T data) {
	super();
	this.status = status;
	this.statusCode = statusCode;
	this.statusDescription = statusDescription;
	this.data = data;
	}


	public ResponseModel(boolean status, int statusCode, T data) {
	super();
	        this.status = status;
	        this.statusCode = statusCode;
	        this.data = data;
	    }

	public ResponseModel(T data){
	this(true,data);
	}
	public ResponseModel(boolean status, T data) {
	       this(status, 200, data);
	}
	
}
