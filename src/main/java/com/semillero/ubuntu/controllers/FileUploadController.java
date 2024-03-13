package com.semillero.ubuntu.controllers;

import com.semillero.ubuntu.services.FileUpload;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequestMapping("/images")
@RequiredArgsConstructor
public class FileUploadController {

    private final FileUpload fileUpload;

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("image")MultipartFile multipartFile) throws IOException {
        String imageURL = fileUpload.uploadFile(multipartFile);
        return imageURL;
    }
}
