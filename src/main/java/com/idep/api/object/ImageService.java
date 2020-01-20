package com.idep.api.object;


import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.experimental.var;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;


@Service
public class ImageService {

    private AmazonS3 s3client;

    @Value("${amazonProperties.endpointUrl}")
    private String endpointUrl;
    @Value("${amazonProperties.bucketName}")
    private String bucketName;
    @Value("${amazonProperties.accessKey}")
    private String accessKey;
    @Value("${amazonProperties.secretKey}")
    private String secretKey;


    public String uploadFile(MultipartFile multipartFile) {
        String fileUrl = "";
        try {
            File file = convertMultiPartToFile(multipartFile);
            String fileName = generateFileName(multipartFile);
            fileUrl = endpointUrl + "/" + bucketName + "/" + fileName;
            uploadFileTos3bucket(fileName, file);
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileUrl;
    }

    private void uploadFileTos3bucket(String fileName, File file) {
        AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
        AmazonS3Client s3client = new AmazonS3Client(credentials);

        var response = s3client.putObject(new PutObjectRequest(bucketName, fileName, file)
                  .withCannedAcl(CannedAccessControlList.PublicRead));
    }

    public boolean deleteFileFromS3Bucket(String fileUrl) {
        AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
        AmazonS3Client s3client = new AmazonS3Client(credentials);

        String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
        try {

            s3client.deleteObject(new DeleteObjectRequest(bucketName + "/", fileName));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private String generateFileName(MultipartFile multiPart) {
        return new Date().getTime() + "-" + multiPart.getOriginalFilename().replace(" ", "_");
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }
}
