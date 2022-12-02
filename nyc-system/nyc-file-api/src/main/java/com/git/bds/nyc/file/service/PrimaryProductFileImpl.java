package com.git.bds.nyc.file.service;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.io.FileTypeUtil;
import com.git.bds.nyc.exception.BusinessException;
import com.git.bds.nyc.framework.file.core.util.FileTypeUtils;
import com.git.bds.nyc.framework.file.minio.MinioConfig;
import com.git.bds.nyc.framework.file.minio.MinioUtil;
import com.git.bds.nyc.result.ResultCode;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 成大事
 * @since 2022/9/16 19:50
 */
@Slf4j
@Service
public class PrimaryProductFileImpl implements PrimaryProductFileService{

    @Resource
    private MinioUtil minioUtil;

    @Resource
    private MinioConfig minioConfig;



    /**
     * 上载图片
     *
     * @param files 文件夹
     * @param type
     * @return {@link List}<{@link String}>
     */
    @SneakyThrows
    @Override
    public List<String> uploadPictures(MultipartFile[] files, int type) {
        for (MultipartFile uploadFile : files) {
            String fileType = FileTypeUtil.getType(uploadFile.getInputStream());
            if(!FileTypeUtils.SUFFIX.contains(fileType)){
                throw new BusinessException(ResultCode.FILE_TYPE_ERROR.getCode(), ResultCode.FILE_TYPE_ERROR.getMessage());
            }
        }
        return minioUtil.uploadImgList(minioConfig.getBucketName(),files, StpUtil.getLoginIdAsLong(),type);
    }


}
