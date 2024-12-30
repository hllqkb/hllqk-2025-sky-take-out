package com.sky.controller.admin;

import com.sky.constant.MessageConstant;
import com.sky.result.Result;
import com.sky.utils.AliOssUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
@Api(tags = "后台管理-通用")
@RequestMapping("/admin/common")
public class CommonController {
    @Autowired
    private AliOssUtil aliOssUtil;
    @ApiOperation("上传文件")
    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file) {
        log.info("上传文件, file={}",file);
        if (file.isEmpty()) {
            return Result.error("上传文件为空");
        }
        String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        if (!extension.equals(".jpg") && !extension.equals(".png") && !extension.equals(".jpeg")) {
            return Result.error("上传文件格式不正确");
        }
        String uploadFileName= UUID.randomUUID().toString()+extension;
        try {
            String fileUrl = aliOssUtil.upload(file.getBytes(), uploadFileName);
            return Result.success(fileUrl);
        }
        catch (IOException e) {
            log.error("上传文件失败, e={}", e);
            return Result.error(MessageConstant.UPLOAD_FAILED);
        }

    }
}
