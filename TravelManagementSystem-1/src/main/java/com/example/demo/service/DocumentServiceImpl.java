package com.example.demo.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class DocumentServiceImpl implements FileService {

	@Value("${upload.dir}")
	private String path;

	@Override
	public String uploadFile(MultipartFile file) {
		// TODO Auto-generated method stub
		File dir = new File(path);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		   String fileUrl=UUID.randomUUID()+file.getOriginalFilename();
		String savefile = path + File.separator + fileUrl;
		File saveimage = new File(savefile);

		try {
			file.transferTo(saveimage);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("File Not Found");
		}

		return fileUrl;
	}

	@Override
	public byte[] getFile(String filename) {
		// TODO Auto-generated method stub
		Path file = Paths.get(path).resolve(filename);

		try {
			return Files.readAllBytes(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	@Override
	public String deleteFile(String filename) {
		// TODO Auto-generated method stub
		Path file = Paths.get(path).resolve(filename);

		try {
			if(Files.deleteIfExists(file)) {
				return "File Deleted Successfully..👍";
			}else {
				return "File Not Found";
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return "Error While Deleting File";
		}
		 
	}


}
