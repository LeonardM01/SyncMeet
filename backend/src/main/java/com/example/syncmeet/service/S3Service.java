package com.example.syncmeet.service;

import org.springframework.web.multipart.MultipartFile;

public interface S3Service {
    /**
    * Upload a file to S3
    * @param file the file to upload
    * @return the URL of the uploaded file
    */
    String uploadFile(MultipartFile file);

    /**
     * Remove an image from S3
     * @param imageUrl the URL of the image to remove
     */
    void removeImage(String imageUrl);

    /**
     * Remove a file from S3
     * @param fileUrl the URL of the file to remove
     */
    void removeFile(String fileUrl);

    /**
     * Generate a file name for a MultipartFile
     * @param file the MultipartFile to generate a file name for
     * @return the generated file name
     */
    String createFileName(MultipartFile file);

    /**
     * Get an object from a URL
     * @param objectUrl the URL of the object
     * @return the object
     */
    String getObjectFromUrl(String objectUrl);
}
