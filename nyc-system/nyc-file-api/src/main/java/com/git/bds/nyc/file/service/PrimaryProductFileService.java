package com.git.bds.nyc.file.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author 成大事
 * @since 2022/9/16 19:50
 */

public interface PrimaryProductFileService {

    /**
     * 上载图片
     *
     * @param uploadFiles 上传文件
     * @param type        类型
     * @return {@link List}<{@link String}>
     */
    List<String> uploadPictures(MultipartFile[] uploadFiles, int type);
}
