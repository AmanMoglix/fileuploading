package com.online.fileuploading.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.online.fileuploading.domain.PlaFeed;
import com.online.fileuploading.domain.dto.CommonResponseObjectDTO;

public interface FileUploadingService {
	public Map<String, Object> save(MultipartFile[] obj) throws IOException, MessagingException;

	public PlaFeed saveOrUpload(PlaFeed plaFeed);
	
	public List<CommonResponseObjectDTO> list(String msn,Pageable pageable);

	public List<PlaFeed> list(Map<String, Object> params);

	
}
