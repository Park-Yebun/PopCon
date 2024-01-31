package com.ssafy.popcon.util;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.ssafy.popcon.user.controller.UserController;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class S3UploadUtil {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public String upload(MultipartFile file, String dirName) throws Exception {
        File uploadFile = convert(file).get();
        return upload(uploadFile, dirName);
    }

    // s3로 파일 업로드하기
    private String upload(File file, String dirName) {
        String fileName = dirName + "/" + UUID.randomUUID()+"_"+file.getName();   // S3에 저장된 파일 이름//
        String uploadImageUrl = putS3(file, fileName); // s3로 업로드
        removeNewFile(file); // 로컬에 생성된 File 삭제 (MultipartFile -> File 전환 하며 로컬에 파일 생성됨)
        return uploadImageUrl; // 업로드된 파일의 S3 URL 주소 반환
    }

    // 업로드하기
    private String putS3(File uploadFile, String fileName){
        amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, uploadFile).withCannedAcl(CannedAccessControlList.PublicRead));
        return amazonS3Client.getUrl(bucket, fileName).toString();
    }

    // 이미지 지우기
    private void removeNewFile(File targetFile) {
        if (targetFile.delete()) {
            logger.info("File delete success");
            return;
        }
        logger.info("File delete fail");
    }

    private Optional<File> convert(MultipartFile file) throws Exception {
        File convertFile = new File(file.getOriginalFilename());
        if(convertFile.createNewFile()) {
            try (FileOutputStream fos = new FileOutputStream(convertFile)) {
                fos.write(file.getBytes());
            }
            return Optional.of(convertFile);
        }
        return Optional.empty();
    }
}

