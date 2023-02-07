package com.git.bds.nyc.applet.api.service;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.io.FileTypeUtil;
import com.git.bds.nyc.constant.Constants;
import com.git.bds.nyc.exception.BusinessException;
import com.git.bds.nyc.framework.file.core.util.FileTypeUtils;
import com.git.bds.nyc.framework.file.minio.MinioConfig;
import com.git.bds.nyc.framework.file.minio.MinioUtil;
import com.git.bds.nyc.result.ResultCode;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author 成大事
 * @since 2022/9/16 19:50
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class PrimaryProductFileImpl implements PrimaryProductFileService{


    private final MinioUtil minioUtil;

    private final MinioConfig minioConfig;


    /**
     * 上载产品图片
     *
     * @param files 文件夹
     * @return {@link List}<{@link String}>
     */
    @SneakyThrows
    @Override
    public List<String> uploadProductImg(MultipartFile[] files) {
        for (MultipartFile uploadFile : files) {
            String fileType = FileTypeUtil.getType(uploadFile.getInputStream());
            if(!FileTypeUtils.SUFFIX.contains(fileType)){
                throw new BusinessException(ResultCode.FILE_TYPE_ERROR.getCode(), ResultCode.FILE_TYPE_ERROR.getMessage());
            }
        }
        return minioUtil.uploadImgList(minioConfig.getBucketName(),files, StpUtil.getLoginIdAsLong(), Constants.PRODUCT);
    }

    /**
     * 上传请求img
     *
     * @param files 上载文件
     * @return {@link List}<{@link String}>
     */
    @SneakyThrows
    @Override
    public List<String> uploadDemandImg(MultipartFile[] files) {
        for (MultipartFile uploadFile : files) {
            String fileType = FileTypeUtil.getType(uploadFile.getInputStream());
            if(!FileTypeUtils.SUFFIX.contains(fileType)){
                throw new BusinessException(ResultCode.FILE_TYPE_ERROR.getCode(), ResultCode.FILE_TYPE_ERROR.getMessage());
            }
        }
        return minioUtil.uploadImgList(minioConfig.getBucketName(),files, StpUtil.getLoginIdAsLong(),Constants.DEMAND);
    }

    /**
     * 上传身份证img
     *
     * @param frontImg 身份证正面img
     * @return {@link String}
     */
    @Override
    @SneakyThrows
    public String uploadIdCardFrontImg(MultipartFile frontImg) {
        long userId = StpUtil.getLoginIdAsLong();
        String idCardImg = minioUtil.uploadIdCardImg(minioConfig.getBucketName(), frontImg, userId);
        log.info("身份证： "+idCardImg);
        return null;
    }

    /**
     * 上传身份证img
     *
     * @param backImg 身份证背面img
     * @return {@link String}
     */
    @Override
    public String uploadIdCardBackImg(MultipartFile backImg) {
        return null;
    }

    /**
     * 上载企业许可证img
     *
     * @param uploadFiles 上载文件
     * @return {@link List}<{@link String}>
     */
    @Override
    public String uploadEnterpriseLicenseImg(MultipartFile[] uploadFiles) {
        return null;
    }

    /**
     * 上传头像
     *
     * @param file 文件
     * @return {@link String}
     */
    @Override
    @SneakyThrows
    public String uploadAvatar(MultipartFile file) {
        String fileType = FileTypeUtil.getType(file.getInputStream());
        if(!FileTypeUtils.SUFFIX.contains(fileType)){
            throw new BusinessException(ResultCode.FILE_TYPE_ERROR.getCode(), ResultCode.FILE_TYPE_ERROR.getMessage());
        }
        return minioUtil.uploadAvatar(minioConfig.getBucketName(),file, StpUtil.getLoginIdAsLong());
    }



}
