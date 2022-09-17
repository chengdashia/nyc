package com.git.bds.nyc.file.service;

import com.git.bds.nyc.framework.file.minio.MinioConfig;
import com.git.bds.nyc.framework.file.minio.MinioUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.ArrayList;
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
        List<String> imgList = new ArrayList<>(uploadFiles.length);
        for (MultipartFile uploadFile : uploadFiles) {
            String imgUrl = minioUtil.uploadFile(minioConfig.getBucketName(), uploadFile, 111L);
            imgList.add(imgUrl);
        }

        return imgList;
    }
}
