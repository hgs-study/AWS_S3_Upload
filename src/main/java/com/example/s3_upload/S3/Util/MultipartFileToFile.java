package com.example.s3_upload.S3.Util;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

@Component
public class MultipartFileToFile {

    public File convert(MultipartFile multipartFile){
        File file = new File(System.getProperty("user.dir") +"/"+multipartFile.getOriginalFilename());
        try {
            multipartFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
}
