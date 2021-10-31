package com.online.fileuploading.domain;

import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Data;
@Data
@Entity(name="pla_feed")
public class PlaFeed {
	private static final Logger logger=LoggerFactory.getLogger(PlaFeed.class);
	@Id
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
	public static PlaFeed bind(Map<String,Object> map) {
		logger.info("Binding plafeed data in plafed obj from map: '{}'",map);
		PlaFeed plaFeed=new PlaFeed();
		plaFeed.setMsn(String.valueOf(map.get("msn")));
		plaFeed.setGtin(String.valueOf( map.get("gtin")));
		plaFeed.setIdentifierExists(String.valueOf(map.get("identifierExists")));
		plaFeed.setCustomLabel2(String.valueOf(map.get("customLabel2")));
		plaFeed.setCustomLabel3(String.valueOf(map.get("customLabel3")));
		plaFeed.setCustomLabel4(String.valueOf(map.get("customLabel4")));
		plaFeed.setPromotionId(String.valueOf(map.get("promotionId")));
		plaFeed.setIsGoogleActive(String.valueOf(map.get("isGoogleActive")));
		plaFeed.setIsFacebookActive(String.valueOf(map.get("isFacebookActive")));
		plaFeed.setIsCriteoActive(String.valueOf(map.get("isCriteoActive")));
		plaFeed.setAvailability(String.valueOf(map.get("availability")));
		
		return plaFeed;
	}
	
}
