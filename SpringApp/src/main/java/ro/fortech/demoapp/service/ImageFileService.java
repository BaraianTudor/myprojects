package ro.fortech.demoapp.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageFileService {

	public String saveFileImage(MultipartFile file, String username) {
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		Path outputFilePath = Paths.get("C:\\Users\\calin\\Documents\\workspace-sts-3.9.1.RELEASE\\SpringApp\\src\\main\\resources\\static\\images\\" + username + "\\" + timestamp.getTime(),
				file.getOriginalFilename());

		try {
			Files.createDirectories(outputFilePath);
			Files.deleteIfExists(outputFilePath);
			Files.copy(file.getInputStream(), outputFilePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return outputFilePath.toString();

	}

}
