package com.git.bds.nyc.applet.api.service;

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
     * @return {@link List}<{@link String}>
     */
    List<String> uploadProductImg(MultipartFile[] uploadFiles);

    /**
     * 上传请求img
     *
     * @param uploadFiles 上载文件
     * @return {@link List}<{@link String}>
     */
    List<String>  uploadDemandImg(MultipartFile[] uploadFiles);

    /**
     * 上传身份证img
     *
     * @param uploadFiles 上载文件
     * @return {@link List}<{@link String}>
     */
    List<String> uploadIdCardImg(MultipartFile[] uploadFiles);

    /**
     * 上载企业许可证img
     *
     * @param uploadFiles 上载文件
     * @return {@link List}<{@link String}>
     */
    List<String> uploadEnterpriseLicenseImg(MultipartFile[] uploadFiles);
}
