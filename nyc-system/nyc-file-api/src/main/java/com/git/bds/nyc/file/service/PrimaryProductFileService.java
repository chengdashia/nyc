package com.git.bds.nyc.file.service;

import io.minio.errors.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * @author 成大事
 * @since 2022/9/16 19:50
 */

public interface PrimaryProductFileService {

    /**
     * 上传文件
     * @param uploadFiles 上传文件
     */
    List<String> uploadFiles(MultipartFile[] uploadFiles) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException;
}
