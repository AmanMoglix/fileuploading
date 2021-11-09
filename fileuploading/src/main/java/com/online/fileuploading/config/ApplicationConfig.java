package com.online.fileuploading.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;
@Component
@Data
public class ApplicationConfig {
	@Value("${candidateEmail}")
	private String email;

}
