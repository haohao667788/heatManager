package org.heatmanagment.hibernate.util;

import java.io.File;
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
	private SimpleDateFormat sdf;

	public FileUploader() {
		Properties props = new Properties();
		try {
			// props.load(new FileReader(new File("/picLocation.properties")));
			props.load(getClass()
					.getResourceAsStream("/picLocation.properties"));
			this.baseDir = props.getProperty("location");
		} catch (Exception e) {
			e.printStackTrace();
		}
		sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
	}

	public String upload(CommonsMultipartFile file, String type, String name) {
		String filePath;
		try {
			String suffix = file.getOriginalFilename().substring(
					file.getOriginalFilename().lastIndexOf("."));
			Date date = new Date(System.currentTimeMillis());
			String dt = this.sdf.format(date);
			filePath = this.baseDir + File.separator + type + "_" + name + "_"
					+ dt + suffix;
			File save = new File(filePath);
			file.getFileItem().write(save);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		return filePath;
	}

	public void deleteFile(String path) {
		File file = new File(path);
		file.delete();
	}
}
