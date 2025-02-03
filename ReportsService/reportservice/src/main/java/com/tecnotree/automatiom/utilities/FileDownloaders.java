package com.tecnotree.automatiom.utilities;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URISyntaxException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FileDownloaders {

	private static final Logger logger = LogManager.getLogger(FileDownloaders.class);
	private final FileService fileService;
	private final ExtractFileName urlService;

	public FileDownloaders(FileService fileService, ExtractFileName urlService) {
		this.fileService = fileService;
		this.urlService = urlService;
	}

	public FileDownloaders downloadFile(String fileUrl) {
		try {
			String fileName = ExtractFileName.FileName(fileUrl);
			Path filePath = Paths.get(fileService.getTargetDirectory(), fileName);

			fileService.createDirectories(filePath.getParent());
			downloadFromUrl(fileUrl, filePath.toString());

			logger.info("File downloaded successfully to " + filePath.toAbsolutePath());
			checkFile(filePath);
			return this;
		} catch (IOException | URISyntaxException e) {
			logger.error("Error handling file: " + e.getMessage(), e);
		}
		return this;
	}

	private void downloadFromUrl(String fileUrl, String localFilePath) throws IOException {
		URL url = new URL(fileUrl);
		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();

		InputStream inputStream = null;
		OutputStream outputStream = null;
		try {
			inputStream = httpConn.getInputStream();
			outputStream = new FileOutputStream(localFilePath);

			byte[] buffer = new byte[4096];
			int bytesRead;

			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
			}
		} finally {
			httpConn.disconnect();
			if (outputStream != null) {
				outputStream.close();
			}
			if (inputStream != null) {
				inputStream.close();
			}

		}
	}

	private void checkFile(Path filePath) throws IOException {
		if (Files.exists(filePath)) {
			logger.info("File exists: " + filePath.toAbsolutePath());
		} else {
			logger.warn("File does not exist: " + filePath.toAbsolutePath());
		}

		Path directoryPath = filePath.getParent();
		long fileCount = fileService.countFilesInDirectory(directoryPath);
		logger.info("Number of files in directory: " + fileCount);
	}

	public void deleteFile(String fileUrl) {
		try {
			String fileName = urlService.FileName(fileUrl);
			Path filePath = Paths.get(fileService.getTargetDirectory(), fileName);

			fileService.deleteFile(filePath);
			logger.info("File deleted successfully: " + filePath.toAbsolutePath());
		} catch (IOException | URISyntaxException e) {
			logger.error("Error deleting file: " + e.getMessage(), e);
		}
	}

	public void deleteAllFiles() {
		try {
			Path directoryPath = Paths.get(fileService.getTargetDirectory());
			fileService.deleteAllFilesInDirectory(directoryPath);
			logger.info("All files deleted successfully in directory: " + directoryPath.toAbsolutePath());
		} catch (IOException e) {
			logger.error("Error deleting all files: " + e.getMessage(), e);
		}
	}
	


	public int countFiles() {
		Path directoryPath = Paths.get(fileService.getTargetDirectory());
		try {
			return (int) fileService.countFilesInDirectory(directoryPath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;

	}
}
