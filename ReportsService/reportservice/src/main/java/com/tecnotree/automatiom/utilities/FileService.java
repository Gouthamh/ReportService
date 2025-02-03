package com.tecnotree.automatiom.utilities;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileService {

    private static final String DEFAULT_DIRECTORY = System.getProperty("user.dir") + "/Documents";
    
    //private String default_dir;
    
//    public void setTragetDirector(String default_dir) {
//    	this.default_dir=default_dir;
//    }
    
    public String getTargetDirectory() {
        return DEFAULT_DIRECTORY;
    	//return System.getProperty("user.dir") + default_dir;
    }

    public void createDirectories(Path directoryPath) throws IOException {
        Files.createDirectories(directoryPath);
    }

    public void deleteFile(Path filePath) throws IOException {
        if (Files.exists(filePath)) {
            Files.delete(filePath);
        } else {
            throw new IOException("File does not exist: " + filePath.toAbsolutePath());
        }
    }

    public void deleteAllFilesInDirectory(Path directoryPath) throws IOException {
        if (Files.isDirectory(directoryPath)) {
            try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(directoryPath)) {
                for (Path path : directoryStream) {
                    if (Files.isRegularFile(path)) {
                        Files.delete(path);
                    }
                }
            }
        } else {
            throw new IllegalArgumentException("The specified path is not a directory.");
        }
    }

    public long countFilesInDirectory(Path directoryPath) throws IOException {
        if (Files.isDirectory(directoryPath)) {
            try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(directoryPath)) {
                return Files.list(directoryPath).count();
            }
        } else {
            throw new IllegalArgumentException("The specified path is not a directory.");
        }
    }
    
    
}
