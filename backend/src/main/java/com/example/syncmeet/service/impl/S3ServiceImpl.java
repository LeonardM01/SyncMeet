package com.example.syncmeet.service.impl;

import com.example.syncmeet.service.S3Service;
import io.awspring.cloud.s3.S3Exception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3Uri;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.GetUrlRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Objects;

@Service
@Transactional
public class S3ServiceImpl implements S3Service {

    private final S3Client s3Client;

    @Value("${aws.s3.bucket}")
    private String bucketName;

    @Autowired
    public S3ServiceImpl(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    @Override
    public String uploadFile(MultipartFile file) throws S3Exception {
        String fileName = createFileName(file);
        try {
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(fileName)
                    .build();
            s3Client.putObject(putObjectRequest, RequestBody.fromBytes(file.getBytes()));
        } catch (IOException e) {
            throw new S3Exception("Failed to store file", e);
        }

        GetUrlRequest getUrlRequest = GetUrlRequest.builder()
                .bucket(bucketName)
                .key(fileName)
                .build();

        return s3Client.utilities().getUrl(getUrlRequest).toString();
    }

    @Override
    public String createFileName(MultipartFile file) {
        String encodedFileName = URLEncoder.encode(
                Objects.requireNonNull(file.getOriginalFilename()),
                StandardCharsets.UTF_8
        );
        return new Date().getTime() + "-" + encodedFileName;
    }

    @Override
    public String getObjectFromUrl(String objectUrl) {
        URI objectUri = URI.create(objectUrl);
        S3Uri s3Uri = s3Client.utilities().parseUri(objectUri);
        return s3Uri.key().orElseThrow(() -> new S3Exception("Could not parse key from URL", null));
    }

    @Override
    public void removeImage(String imageUrl) {
        String fileName;

        try {
            fileName = getObjectFromUrl(imageUrl);
        } catch (IllegalArgumentException e) {
            // Ignore exceptions for old images stored in the form of base64 strings
            return;
        }

        removeFile(fileName);
    }

    @Override
    public void removeFile(String fileName) {
        try {
            DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                    .bucket(bucketName)
                    .key(fileName)
                    .build();
            s3Client.deleteObject(deleteObjectRequest);
        } catch (Exception e) {
            throw new S3Exception("Failed to delete file", e);
        }
    }
}
