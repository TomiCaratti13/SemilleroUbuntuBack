package com.semillero.ubuntu.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileUpload {
    String uploadFile(MultipartFile multipartFile) throws IOException;

    String updateFile(String publicId, MultipartFile multipartFile) throws IOException;

    void deleteFile(String publicId) throws IOException;
}