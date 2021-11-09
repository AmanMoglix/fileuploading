package com.online.fileuploading.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import com.online.fileuploading.config.ApplicationConfig;
import com.online.fileuploading.domain.PlaFeed;
import com.online.fileuploading.domain.dto.CommonResponseObjectDTO;
import com.online.fileuploading.repository.FileUploadingRepository;
import com.online.fileuploading.service.FileUploadingService;
@Service
public class FileUploadingServiceImpl implements FileUploadingService {
	@Autowired
	private FileUploadingRepository fileUploadingRepository;

	@Autowired
	private FileStorageServiceImpl fileStorageServiceImpl;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private SpringTemplateEngine templateEngine;
	
	@Autowired
	private ApplicationConfig config;

	@Override
	public Map<String, Object> save(MultipartFile[] obj) throws IOException, MessagingException {
        List<PlaFeed> plaFeeds=new ArrayList<PlaFeed>();
		Map<String, Object> response = new HashMap<String, Object>();
		if (obj != null && obj.length > 0) {

			 plaFeeds=this.getEntityMap(obj[0]);
			final String fileName = obj[0].getOriginalFilename();
			File file = this.fileStorageServiceImpl.getFileStorageLoctaion("/plaFeed", fileName);
			obj[0].transferTo(file);

			response.put("message", "Added Successfully ..!");

		} else {
			response.put("message", "file not exist..");
		}
		if(plaFeeds!=null && plaFeeds.size()>0) {
			 Map<String ,Object> map=new HashMap<String, Object>();
			 map.put("data",plaFeeds);
			 map.put("candidateEmail",this.config.getEmail());
			 this.sendMail(map);
		 }
		response.put("data",plaFeeds);

		return response;
	}

	private void sendMail(Map<String, Object> props) throws MessagingException{
		Context context = new Context();
		context.setVariables(props);
		String html = templateEngine.process("mail/mail", context);
		MimeMessage message = this.javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		try {
			helper.setTo((String) props.get("candidateEmail"));
			helper.setSubject("Rows that are not inserted ");
			helper.setText(html, true);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
				System.out.println(javaMailSender);
				// send email asynchronously
				new Thread(() -> javaMailSender.send(message)).start();
	}

	private List<PlaFeed> getEntityMap(MultipartFile obj) throws IOException {
        List<PlaFeed> feeds=new ArrayList<PlaFeed>();

		BufferedReader fileReader = new BufferedReader(new InputStreamReader(obj.getInputStream(), "UTF-8"));

		CSVParser csvParser = new CSVParser(fileReader,
				CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());
		for (CSVRecord csvRecord : csvParser.getRecords()) {
			Map<String, Object> map = new HashMap<>();
			map.put("msn", csvRecord.get("msn"));
			map.put("gtin", csvRecord.get("gtin"));
			map.put("identifierExists", csvRecord.get("identifierExists"));
			map.put("customLabel2", csvRecord.get("customLabel2"));
			map.put("customLabel3", csvRecord.get("customLabel3"));
			map.put("customLabel4", csvRecord.get("customLabel4"));
			map.put("promotionId", csvRecord.get("promotionId"));
			map.put("isGoogleActive", csvRecord.get("isGoogleActive"));
			map.put("isFacebookActive", csvRecord.get("isFacebookActive"));
			map.put("isCriteoActive", csvRecord.get("isCriteoActive"));
			map.put("availability", csvRecord.get("availability"));
			if (!csvRecord.get("availability").equalsIgnoreCase("In Stock")
					&& !csvRecord.get("availability").equalsIgnoreCase("Out Of Stock")) {
				System.out.println("getting " +csvRecord.get("availability").equalsIgnoreCase("In Stock"));
              feeds.add(PlaFeed.bind(map));
			} else {
				this.saveOrUpload(PlaFeed.bind(map));

			}

		}
		return feeds;
	}

	@Override
	public PlaFeed saveOrUpload(PlaFeed plaFeed) {
		return this.fileUploadingRepository.save(plaFeed);
	}

	

	@Override
	public List<CommonResponseObjectDTO> list(String msn,Pageable pagable) {
		List<CommonResponseObjectDTO> commonResponseObjectDTO = this.fileUploadingRepository.findCustom(msn, pagable);
		if (commonResponseObjectDTO != null) {
			return commonResponseObjectDTO;
		}
		return null;
	}

	@Override
	public List<PlaFeed> list(Map<String, Object> params) {
		for(Map.Entry<String, Object> entry:params.entrySet()) {
			System.out.println(entry.getKey() + " "+ entry.getValue());
		}
		return null;
	}

}
