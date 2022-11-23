package com.git.bds.nyc.file.service;

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
     * 上传文件
     *
     * @param uploadFiles 上传文件
     * @return {@link List}<{@link String}>
     */
    @SneakyThrows
    @Override
    public List<String> uploadFiles(MultipartFile[] uploadFiles) {
        for (MultipartFile uploadFile : uploadFiles) {
            String type = FileTypeUtil.getType(uploadFile.getInputStream());
            if(!FileTypeUtils.SUFFIX.contains(type)){
                throw new BusinessException(ResultCode.FILE_TYPE_ERROR.getCode(), ResultCode.FILE_TYPE_ERROR.getMessage());
            }
        }
        return minioUtil.uploadImgList(minioConfig.getBucketName(),uploadFiles, 111L);
    }
}
