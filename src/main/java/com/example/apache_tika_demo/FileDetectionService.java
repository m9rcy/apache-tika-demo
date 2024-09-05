package com.example.apache_tika_demo;

import org.apache.tika.Tika;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.metadata.TikaCoreProperties;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class FileDetectionService {

    private final Tika tika;
    private final ResourceLoader resourceLoader;

    public FileDetectionService(ResourceLoader resourceLoader) {
        this.tika = new Tika();
        this.resourceLoader = resourceLoader;
    }

    public void detectFilesInResourceFolder() {
        // Add your file names here
        //List<String> fileNames = Arrays.asList("sample.txt", "heleina360.png", "10B.bin", "application.properties");
        List<String> fileNames = Arrays.asList("hello.external.mime.type", "hello.hi","10B.bin", "application.properties");


        for (String fileName : fileNames) {
            try {
                Resource resource = resourceLoader.getResource("classpath:" + fileName);
                if (resource.exists()) {
                    Metadata metadata = new Metadata();
                    metadata.add(TikaCoreProperties.RESOURCE_NAME_KEY, fileName);
                    String mimeType = tika.detect(resource.getInputStream(), metadata);
                    System.out.println("File: " + fileName + " has MIME Type: " + mimeType);
                } else {
                    System.out.println("File: " + fileName + " not found in resources.");
                }
            } catch (IOException e) {
                System.out.println("Error detecting MIME type for file: " + fileName);
                e.printStackTrace();
            }
        }
    }
}
