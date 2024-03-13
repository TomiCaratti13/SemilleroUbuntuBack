package com.semillero.ubuntu.services.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.semillero.ubuntu.services.FileUpload;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileUploadImpl implements FileUpload {

    private final Cloudinary cloudinary;
    @Override
    public String uploadFile(MultipartFile multipartFile) throws IOException {


        return cloudinary.uploader()
                .upload(multipartFile.getBytes(),
                        Map.of("public_id", UUID.randomUUID().toString()))
                .get("url")
                .toString();
    }
    @Override
    public String updateFile(String publicId, MultipartFile multipartFile) throws IOException {



        return cloudinary.uploader()
                .upload(multipartFile.getBytes(),
                        ObjectUtils.asMap("public_id", publicId, "overwrite", true))
                .get("url")
                .toString();
    }
    @Override
    public void deleteFile(String publicId) throws IOException {
        cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
    }

}
