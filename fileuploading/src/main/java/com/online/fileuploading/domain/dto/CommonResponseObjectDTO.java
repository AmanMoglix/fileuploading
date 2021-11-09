package com.online.fileuploading.domain.dto;

import lombok.Data;

@Data
public class CommonResponseObjectDTO {
	private String msn;
	private String gtin;
	private String identifierExists;
	private String customLabel2;
	private String customLabel3;
	private String customLabel4;
	private String promotionId;
	private String isGoogleActive;
	private String isFacebookActive;
	private String isCriteoActive;
	private String availability;
	public CommonResponseObjectDTO(String msn, String gtin, String identifierExists, String customLabel2,
			String customLabel3, String customLabel4, String promotionId, String isGoogleActive,
			String isFacebookActive, String isCriteoActive, String availability) {
		this.msn = msn;
		this.gtin = gtin;
		this.identifierExists = identifierExists;
		this.customLabel2 = customLabel2;
		this.customLabel3 = customLabel3;
		this.customLabel4 = customLabel4;
		this.promotionId = promotionId;
		this.isGoogleActive = isGoogleActive;
		this.isFacebookActive = isFacebookActive;
		this.isCriteoActive = isCriteoActive;
		this.availability = availability;
	}
	public CommonResponseObjectDTO() {
		// TODO Auto-generated constructor stub
	}

	
}
