package com.example.apache_tika_demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class FileDetectionRunner implements CommandLineRunner {

    private final FileDetectionService fileDetectionService;

    public FileDetectionRunner(FileDetectionService fileDetectionService) {
        this.fileDetectionService = fileDetectionService;
    }

    @Override
    public void run(String... args) throws Exception {
        fileDetectionService.detectFilesInResourceFolder();
    }
}
