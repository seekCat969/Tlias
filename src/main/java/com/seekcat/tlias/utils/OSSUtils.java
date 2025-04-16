package com.seekcat.tlias.utils;

import com.aliyun.oss.ClientBuilderConfiguration;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.auth.EnvironmentVariableCredentialsProvider;
import com.aliyun.oss.common.comm.SignVersion;
import com.aliyun.oss.model.PutObjectRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.UUID;

@Component
public class OSSUtils {
    public String uploadImages(MultipartFile image) throws Exception {
//        将原始文件名通过.进行截取获得字符串数组
        String[] split = image.getOriginalFilename().split("\\.");
        StringBuilder sb = new StringBuilder();
//        将UUID中的'-'删去，拼接到sb中去
        Arrays.stream(UUID.randomUUID().toString().split("-")).forEach(sb::append);
//        拼接文件名UUID + . + 文件后缀格式
        String imageName = sb + "." + split[split.length - 1];

        //从环境变量获取id和密钥
        EnvironmentVariableCredentialsProvider credentialsProvider = CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();
//        创建一个ClientBuilderConfiguration对象
        ClientBuilderConfiguration clientBuilderConfiguration = new ClientBuilderConfiguration();
//        设置签名版本
        clientBuilderConfiguration.setSignatureVersion(SignVersion.V4);
//        获得一个OSS接口的实现类对象，OSSClientBuilder这个工具类的create()方法会返回一个OSSClientBuilderImpl
//        return new OSSClientBuilderImpl();
        OSS ossClient = OSSClientBuilder.create()
//                传递参数，END_POINT是访问OSS的URL
                .endpoint(OSSEnum.END_POINT.getValue())
//                传递id和密钥
                .credentialsProvider(credentialsProvider)
//                传递ClientBuilderConfiguration对象
                .clientConfiguration(clientBuilderConfiguration)
//                传递bucket的地区
                .region(OSSEnum.REGION.getValue())
//                返回一个OSS实现类对象
                .build();
//        创建putObjectRequest对象，这个对象需要在ossClient调用上传方法putObject的时候被传递
        PutObjectRequest putObjectRequest = new PutObjectRequest(OSSEnum.BUCKET_NAME.getValue(),
//                传递文件路径，OBJECT_NAME + / + 文件名
                OSSEnum.OBJECT_NAME.getValue() + "/" + imageName,
//                MultipartFile的InputStream
                image.getInputStream());
//        上传
        ossClient.putObject(putObjectRequest);

        ossClient.shutdown();
//        返回访问URL
        return OSSEnum.IMAGE_URL.getValue() + OSSEnum.OBJECT_NAME.getValue() + "/" + imageName;
    }
}
