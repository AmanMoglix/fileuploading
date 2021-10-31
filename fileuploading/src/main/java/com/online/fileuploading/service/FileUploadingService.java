package com.online.fileuploading.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.online.fileuploading.domain.PlaFeed;

public interface FileUploadingService {
	public Map<String, Object> save(MultipartFile[] obj) throws IOException;

	public PlaFeed saveOrUpload(PlaFeed plaFeed);

	public PlaFeed getById(Long id);

	public List<PlaFeed> list();
}
