package com.seekcat.tlias.utils;

public enum OSSEnum {
    END_POINT("https://oss-cn-chengdu.aliyuncs.com"),
    BUCKET_NAME("tlias-learn-project"),
    REGION("cn-chengdu"),
    OBJECT_NAME("images"),
    IMAGE_URL("https://tlias-learn-project.oss-cn-chengdu.aliyuncs.com/");

    private String value;

    // 构造方法，用于存储每个枚举常量对应的字符串
    OSSEnum(String value) {
        this.value = value;
    }

    // 获取枚举常量存储的字符串
    public String getValue() {
        return value;
    }

}
