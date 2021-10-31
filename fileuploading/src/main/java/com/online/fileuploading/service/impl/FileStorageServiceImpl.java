package com.online.fileuploading.service.impl;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.fileuploading.config.FileStorageProperties;
import com.online.fileuploading.utils.FileStorageException;

@Service
public class FileStorageServiceImpl {
	private final Path filestorageLocation;
	@Autowired
	ServletContext ctx;
	//to get the MIME type of a file,

	@Autowired
	public FileStorageServiceImpl(FileStorageProperties filestorageProperties) {
		this.filestorageLocation = Paths.get(filestorageProperties.getUploadDir()).toAbsolutePath().normalize();

		try {
			Files.createDirectories(filestorageLocation);
		} catch (Exception ex) {
			throw new FileStorageException("Could not create the directory where the uploaded files will be stored.",
					ex);
		}
	}

	public File getFileStorageLoctaion(String dir, String filename) {
		String uploadPath = this.filestorageLocation + dir;
		File file = new File(uploadPath);
		if (!file.exists()) {
			file.mkdirs();
			file.setExecutable(true, false);
			file.setReadable(true, false);
			file.setWritable(true, false);
		}
		file = new File(uploadPath + "/" + filename);
		return file;
	}

	public File getRealPath(String folderName, String fileName) {
		return new File(this.filestorageLocation + folderName + "/" + fileName);
	}

}
