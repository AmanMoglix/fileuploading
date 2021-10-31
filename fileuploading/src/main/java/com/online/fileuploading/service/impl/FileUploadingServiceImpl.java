package com.online.fileuploading.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.online.fileuploading.domain.PlaFeed;
import com.online.fileuploading.repository.FileUploadingRepository;
import com.online.fileuploading.service.FileUploadingService;

@Service
public class FileUploadingServiceImpl implements FileUploadingService {
	@Autowired
	private FileUploadingRepository fileUploadingRepository;
	@Autowired
	private FileStorageServiceImpl fileStorageServiceImpl;

	@Override
	public Map<String, Object> save(MultipartFile[] obj) throws IOException {
		Map<String, Object> response = new HashMap<String, Object>();
		
		if (obj != null && obj.length > 0) {

			PlaFeed plaFeed = PlaFeed.bind(getEntityMap(obj[0]));
			
		    final String fileName = obj[0].getOriginalFilename();
			File file = this.fileStorageServiceImpl.getFileStorageLoctaion("/plaFeed", fileName);
			obj[0].transferTo(file);

			this.fileUploadingRepository.save(plaFeed);
			response.put("message", "Added Successfully ..!");

		} else {
			response.put("message", "file not exist..");
		}

		return response;
	}

	private Map<String, Object> getEntityMap(MultipartFile obj) throws IOException {
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
			return map;
		}
		return null;
	}

	@Override
	public PlaFeed saveOrUpload(PlaFeed plaFeed) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PlaFeed getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PlaFeed> list() {
		// TODO Auto-generated method stub
		return null;
	}

}
