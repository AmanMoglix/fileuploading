package com.online.fileuploading.controller;

import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.online.fileuploading.service.FileUploadingService;

@RestController
@RequestMapping(value = "/api")
public class FileUploadController {
	private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

	@Autowired
	private FileUploadingService fileUploadService;


	@RequestMapping(value = "", method = { RequestMethod.POST })
	public Map<String, Object> saveOrUplaod(@RequestParam("file") MultipartFile[] obj) throws IOException {
		logger.info("Going to store data : '{}'", obj[0]);
		return this.fileUploadService.save(obj);
	}
}
