package com.example.s3_upload.S3.Controller;

import com.example.s3_upload.S3.Util.AWSService;
import com.example.s3_upload.S3.Util.MultipartFileToFile;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UploadController {

    private final AWSService AWSService;
    private static final boolean FILE_EMPTY = false;
    private final MultipartFileToFile multipartFileToFile;


    @PostMapping(path = "/upload/images")
    public String uploadImages(@RequestParam(name="name") String name,
                               @RequestParam(name="testfile") MultipartFile multipartFile) throws IOException {

        AWSService.uploadFile(multipartFileToFile.convert(multipartFile));
        return "성공!";
    }

}
