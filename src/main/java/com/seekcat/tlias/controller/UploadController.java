package com.seekcat.tlias.controller;

import com.seekcat.tlias.pojo.Result;
import com.seekcat.tlias.utils.OSSUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("/upload")
public class UploadController {
    @Resource
    private OSSUtils ou;

    @PostMapping
    public Result uploadImage(MultipartFile image) throws Exception {
        String url = ou.uploadImages(image);
        return Result.success(url);
    }
}
