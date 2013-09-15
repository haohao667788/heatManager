package org.heatmanagment.hibernate.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * 
 * @author Desmond
 * 
 */
@Service("fileUploader")
public class FileUploader {

	private String baseDir;
	private final String path;

	public FileUploader() {
		this.path = "/heatManager/pic/";
		Properties props = new Properties();
		try {
			// props.load(new FileReader(new File("/picLocation.properties")));
			props.load(getClass()
					.getResourceAsStream("/picLocation.properties"));
			this.baseDir = props.getProperty("location");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param file
	 * @param type
	 * @return if return is "", there is a problem.
	 */
	public String upload(CommonsMultipartFile file, String type) {
		String filePath;
		String fileName;
		try {
			String suffix = file.getOriginalFilename().substring(
					file.getOriginalFilename().lastIndexOf("."));

			fileName = type + "_" + System.currentTimeMillis() + suffix;
			filePath = this.baseDir + File.separator + fileName;
			File save = new File(filePath);
			file.getFileItem().write(save);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		return this.path + fileName;
	}

	public void deleteFile(String path) {
		File file = new File(path);
		file.delete();
	}

	public File getFile(String name) throws FileNotFoundException {
		String path = this.baseDir + File.separator + name;
		return new File(path);
	}
}
