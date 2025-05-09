package com.comitfy.kidefy.configuration;

import lombok.Data;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class MinioConfiguration {

    /*
    @Value("${minio.access.key}")
    String accessKey;
    @Value("${minio.access.secret}")
    String accessSecret;
    @Value("${minio.url}")
    String minioUrl;

    @Value("${minio.bucket.name}")
    String bucketName;


    @Value("${minio.local.upload.path:C:}")
    String localUploadPath;

    @Bean
    public MinioClient generateMinioClient() {
        try {

            MinioClient client =MinioClient.builder()
                    .endpoint(minioUrl)
                    .credentials(accessKey, accessSecret)
                    .build();


            boolean isExistBucket = client.bucketExists(BucketExistsArgs.builder().
                    bucket(bucketName).build());

            if(!isExistBucket){
                client.makeBucket(MakeBucketArgs.builder().
                        bucket(bucketName).build());
            }

            return client;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }

*/
}
