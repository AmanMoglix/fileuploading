package com.online.fileuploading.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.online.fileuploading.domain.PlaFeed;
import com.online.fileuploading.domain.dto.CommonResponseObjectDTO;
import com.online.fileuploading.model.ResponseModel;
import com.online.fileuploading.service.FileUploadingService;

@RestController
@RequestMapping(value = "/api")
public class FileUploadController {
	private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

	@Autowired
	private FileUploadingService fileUploadService;

	@RequestMapping(value = "", method = { RequestMethod.POST })
	public Map<String, Object> saveOrUplaod(@RequestPart(required = false) MultipartFile[] obj) throws IOException, MessagingException {
		logger.info("Going to store data : '{}'");
		return this.fileUploadService.save(obj);
	}
	
	@RequestMapping(value="",method= RequestMethod.GET)
	public ResponseModel<CommonResponseObjectDTO> findByMsnId(@RequestParam(name="search",required=false) String msnId ,@RequestParam(name="offset") int offset,
			@RequestParam(name="limit") int limit  ) {
	logger.info("Getting plafeed by msnId :'{}'",msnId);

		Pageable pageable=PageRequest.of(offset, limit);
		
	if(this.fileUploadService.list(msnId,pageable)!=null) {
	return new ResponseModel(true, this.fileUploadService.list(msnId,pageable)) ;
	
	}
	else 
	 return new ResponseModel(false, 204, new CommonResponseObjectDTO());
	}
	
	@RequestMapping(value="/list",method= {RequestMethod.GET})
	public ResponseModel<PlaFeed> list(@RequestParam (name="params",required=false) Map<String,Object> params){
		 List<PlaFeed> plaFeed=this.fileUploadService.list(params);
		if( plaFeed!=null && plaFeed.size()>0 ) {
			return new ResponseModel(true,200,plaFeed);
		}else
			return new ResponseModel<PlaFeed>(true,204,new PlaFeed());
			
	}
	
	
}
