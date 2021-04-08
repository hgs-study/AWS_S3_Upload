package com.example.s3_upload.S3.Util;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class AWSService {
    private static final String BUCKET_NAME = YOUR_BUCKET_NAME;
    private static final String ACCESS_KEY = YOUR_ACCESS_KEY;
    private static final String SECRET_KEY = YOUR_SECRET_KEY;
    private static final String REGION = YOUR_REGION;
    private AmazonS3 amazonS3;

    public AWSService() {
        AWSCredentials awsCredentials = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);
        amazonS3 = new AmazonS3Client(awsCredentials);
    }

    public void uploadFile(File file) {
        if (amazonS3 != null) {
            try {
                PutObjectRequest putObjectRequest = new PutObjectRequest(BUCKET_NAME + "/share/test", file.getName() , file);
                putObjectRequest.setCannedAcl(CannedAccessControlList.PublicRead); // file permission

                amazonS3.putObject(putObjectRequest); // upload file

                saveFileUrl(file); //saveUrl

            } catch (AmazonServiceException ase) {
                ase.printStackTrace();
            } finally {
                amazonS3 = null;
            }
        }
    }

    private void saveFileUrl(File file){
        String fileName = file.getName();
        AWSCredentials awsCredentials = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);

        AmazonS3 s3Client =  AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .withRegion(REGION)
                .build();
        s3Client.putObject(new PutObjectRequest(BUCKET_NAME+ "/share/test",fileName, new File(fileName)).withCannedAcl(CannedAccessControlList.PublicRead));
        String s3Url = s3Client.getUrl(BUCKET_NAME+ "/share/test", fileName).toString();
        //save 로직 (리턴받은 url DB저장)
    }
}

