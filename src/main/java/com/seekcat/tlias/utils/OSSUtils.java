package com.seekcat.tlias.utils;

import com.aliyun.oss.ClientBuilderConfiguration;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.auth.EnvironmentVariableCredentialsProvider;
import com.aliyun.oss.common.comm.SignVersion;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.util.Arrays;
import java.util.UUID;

@Component
public class OSSUtils {
    public String uploadImages(MultipartFile image) throws Exception{
        String[] split = image.getOriginalFilename().split("\\.");
        StringBuilder sb = new StringBuilder();
        Arrays.stream(UUID.randomUUID().toString().split("-")).forEach(sb::append);

        String imageName = sb.toString() + split[split.length - 1];

        //从环境变量获取id和密钥
        EnvironmentVariableCredentialsProvider credentialsProvider = CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();

        ClientBuilderConfiguration clientBuilderConfiguration = new ClientBuilderConfiguration();
        clientBuilderConfiguration.setSignatureVersion(SignVersion.V4);
        OSS ossClient = OSSClientBuilder.create()
                .endpoint(OSSEnum.END_POINT.getValue())
                .credentialsProvider(credentialsProvider)
                .clientConfiguration(clientBuilderConfiguration)
                .region(OSSEnum.REGION.getValue())
                .build();

        PutObjectRequest putObjectRequest = new PutObjectRequest(OSSEnum.BUCKET_NAME.getValue(), OSSEnum.OBJECT_NAME.getValue() + imageName, image.getInputStream());
        PutObjectResult result = ossClient.putObject(putObjectRequest);

        ossClient.shutdown();

        return OSSEnum.IMAGE_URL.getValue() + imageName;
    }
}
