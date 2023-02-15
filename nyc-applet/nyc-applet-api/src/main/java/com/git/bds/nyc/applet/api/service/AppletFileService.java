package com.git.bds.nyc.applet.api.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author 成大事
 * @since 2022/9/16 19:50
 */

public interface AppletFileService {

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
     * @param frontImg 身份证正面img
     * @return {@link String}
     */
    String uploadIdCardFrontImg(MultipartFile frontImg);

    /**
     * 上传身份证回img
     *
     * @param backImg 身份证背面img
     * @return {@link String}
     */
    String uploadIdCardBackImg(MultipartFile backImg);

    /**
     * 上载企业许可证img
     *
     * @param uploadFiles 上载文件
     * @return {@link List}<{@link String}>
     */
    String uploadEnterpriseLicenseImg(MultipartFile[] uploadFiles);

    /**
     * 上传头像
     *
     * @param file 文件
     * @return {@link String}
     */
    String uploadAvatar(MultipartFile file);


}
