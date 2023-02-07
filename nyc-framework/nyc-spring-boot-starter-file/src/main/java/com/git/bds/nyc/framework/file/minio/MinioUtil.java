package com.git.bds.nyc.framework.file.minio;//package com.git.bds.nyc.util.minio;


import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.IdUtil;
import com.git.bds.nyc.exception.BusinessException;
import com.git.bds.nyc.framework.file.core.util.thumbnailator.ThumbnailUtil;
import io.minio.*;
import io.minio.errors.*;
import io.minio.http.Method;
import io.minio.messages.Bucket;
import io.minio.messages.DeleteObject;
import io.minio.messages.Item;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

import static com.git.bds.nyc.constant.Constants.POINT;
import static com.git.bds.nyc.constant.Constants.SEPARATOR;


/**
 * @author 成大事
 * @since 2022/7/9 22:02
 */
@Slf4j
@Component
public class MinioUtil {

    private static MinioClient minioClient;

    private static String URL;

    private static Long IMG_SIZE;

    /** 端点 */
    private String endpoint;
    /** bucket名称 */
    private String bucketName;
    /** 访问密钥 */
    private String accessKey;
    /** 秘密密钥 */
    private String secretKey;
    /** img大小 */
    private Integer imgSize;
    /** 文件大小 */
    private Integer fileSize;


    public MinioUtil() {
    }

    public MinioUtil(String endpoint, String bucketName, String accessKey, String secretKey, Integer imgSize, Integer fileSize) {
        this.endpoint = endpoint;
        this.bucketName = bucketName;
        this.accessKey = accessKey;
        this.secretKey = secretKey;
        this.imgSize = imgSize;
        this.fileSize = fileSize;
        createMinioClient();
        URL = endpoint + SEPARATOR + bucketName + SEPARATOR;
        IMG_SIZE = (long) (imgSize * 1024 * 1024);
    }

    /**
     * 创建基于Java端的MinioClient
     */
    public void createMinioClient() {
        try {
            if (null == minioClient) {
                log.info("开始创建 MinioClient..." );
                minioClient = MinioClient
                        .builder()
                        .endpoint(endpoint)
                        .credentials(accessKey, secretKey)
                        .build();
                createBucket(bucketName);
                log.info("创建完毕 MinioClient...");
            }
        } catch (Exception e) {
            log.error("MinIO服务器异常：" + e);
        }
    }


    /**
     * 用户的注册的时候，给一个根目录
     *
     * @param uId        用户id
     * @param bucketName 桶名
     * @return boolean
     */
    public boolean createRootUrl(String uId, String bucketName) {
        log.info("bucketName:  " + bucketName);
        String folder = uId + SEPARATOR;
        try {
            createFolder(bucketName, folder);
            return true;
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }

    /******************************  Operate Bucket Start  ******************************/

    /**
     * 启动SpringBoot容器的时候初始化Bucket
     * 如果没有Bucket则创建
     *
     */
    private void createBucket(String bucketName) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        if (!bucketExists(bucketName)) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        }
    }

    /**
     * 判断Bucket是否存在，true：存在，false：不存在
     *
     * @return boolean
     */
    public boolean bucketExists(String bucketName) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        return minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
    }


    /**
     * 获得Bucket的策略
     *
     * @param bucketName 捅名
     * @return 桶的策略
     */
    public String getBucketPolicy(String bucketName) throws ServerException, InsufficientDataException, ErrorResponseException, BucketPolicyTooLargeException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        return minioClient
                .getBucketPolicy(
                        GetBucketPolicyArgs
                                .builder()
                                .bucket(bucketName)
                                .build());
    }


    /**
     * 获得所有Bucket列表
     *
     * @return 所有的桶名
     */
    public List<Bucket> getAllBuckets() throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        return minioClient.listBuckets();
    }

    /**
     * 根据bucketName获取其相关信息
     *
     * @param bucketName 桶名
     * @return 桶名其相关信息
     */
    public Optional<Bucket> getBucket(String bucketName) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        return getAllBuckets().stream().filter(b -> b.name().equals(bucketName)).findFirst();
    }

    /**
     * 根据bucketName删除Bucket，true：删除成功； false：删除失败，文件或已不存在
     *
     * @param bucketName 桶名
     */
    public void removeBucket(String bucketName) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        minioClient.removeBucket(RemoveBucketArgs.builder().bucket(bucketName).build());
    }

    /******************************  Operate Bucket End  ******************************/


    /******************************  Operate Files Start  ******************************/

    /**
     * 判断文件是否存在
     *
     * @param bucketName 存储桶
     * @param objectName 文件名
     * @return boolean
     */
    public boolean isObjectExist(String bucketName, String objectName) {
        boolean exist = false;
        try {
            minioClient.statObject(
                    StatObjectArgs.builder().bucket(bucketName).object(objectName).build());
            exist = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exist;
    }

    /**
     * 判断文件夹是否存在
     *
     * @param bucketName 存储桶
     * @param objectName 文件夹名称
     */
    public boolean isFolderExist(String bucketName, String objectName) {
        boolean exist = false;
        try {
            Iterable<Result<Item>> results = minioClient.listObjects(
                    ListObjectsArgs.builder().bucket(bucketName).prefix(objectName).recursive(false).build());
            for (Result<Item> result : results) {
                Item item = result.get();
                if (item.isDir() && objectName.equals(item.objectName())) {
                    exist = true;
                }
            }
        } catch (Exception e) {
            exist = false;
        }
        return exist;
    }

    /**
     * 根据文件前缀查询文件
     *
     * @param bucketName 存储桶
     * @param prefix     前缀
     * @param recursive  是否使用递归查询
     * @return MinioItem 列表
     */
    public List<Item> getAllObjectsByPrefix(String bucketName,
                                            String prefix,
                                            boolean recursive) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        List<Item> list = new ArrayList<>();
        Iterable<Result<Item>> objectsIterator = minioClient.listObjects(
                ListObjectsArgs.builder().bucket(bucketName).prefix(prefix).recursive(recursive).build());
        if (objectsIterator != null) {
            for (Result<Item> o : objectsIterator) {
                Item item = o.get();
                list.add(item);
            }
        }
        return list;
    }

    /**
     * 获取文件流
     *
     * @param bucketName 存储桶
     * @param objectName 文件名
     * @return 二进制流
     */
    public InputStream getObject(String bucketName, String objectName) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        return minioClient.getObject(GetObjectArgs.builder().bucket(bucketName).object(objectName).build());
    }

    /**
     * 断点下载
     *
     * @param bucketName 存储桶
     * @param objectName 文件名称
     * @param offset     起始字节的位置
     * @param length     要读取的长度
     * @return 二进制流
     */
    public InputStream getObject(String bucketName, String objectName, long offset, long length) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        return minioClient.getObject(
                GetObjectArgs.builder()
                        .bucket(bucketName)
                        .object(objectName)
                        .offset(offset)
                        .length(length)
                        .build());
    }

    /**
     * 获取路径下文件列表
     *
     * @param bucketName 存储桶
     * @param prefix     文件名称
     * @param recursive  是否递归查找，false：模拟文件夹结构查找
     * @return 二进制流
     */
    public Iterable<Result<Item>> listObjects(String bucketName, String prefix, boolean recursive) {
        return minioClient.listObjects(
                ListObjectsArgs.builder()
                        .bucket(bucketName)
                        .prefix(prefix)
                        .recursive(recursive)
                        .build());
    }

    /**
     * 是否需要
     *
     * @param file 文件
     * @return {@link MultipartFile}
     */
    public MultipartFile isCompress(MultipartFile file){
        log.info("压缩前的大小：  "+ file.getSize());
        log.info("压缩前的名称：  "+ file.getOriginalFilename());
        //压缩
        if(file.getSize() > IMG_SIZE){
            file = ThumbnailUtil.changeScale(file);
            log.info("压缩后的大小：  "+ file.getSize());
        }
        return file;
    }

    /**
     * 获取上传路径
     *
     * @param file 文件
     * @param id   上传者id
     * @return {@link String}
     */
    public String getUploadPath(MultipartFile file,Long id,String type){
        //拼接路径
        DateTime dateTime = new DateTime(new Date());
        //获取文件后缀
        String suffix = org.springframework.util.StringUtils.getFilenameExtension(file.getOriginalFilename());
        return id + SEPARATOR + type +
                SEPARATOR + dateTime.year() +
                SEPARATOR + (dateTime.month() + 1) +
                SEPARATOR + dateTime.dayOfMonth() +
                SEPARATOR + IdUtil.getSnowflakeNextId() + POINT + suffix;
    }

    /**
     * 获取头像路径
     *
     * @param file 文件
     * @param id   上传者id
     * @return {@link String}
     */
    public String getAvatarPath(MultipartFile file,Long id){
        //获取文件后缀
        String suffix = org.springframework.util.StringUtils.getFilenameExtension(file.getOriginalFilename());
        return id + SEPARATOR + "avatar" + SEPARATOR + IdUtil.getSnowflakeNextId() + POINT + suffix;
    }

    /**
     * 获取身份证的路径
     *
     * @param file 文件
     * @param id   上传者id
     * @return {@link String}
     */
    public String getIdCardPath(MultipartFile file,Long id){
        //获取文件后缀
        String suffix = org.springframework.util.StringUtils.getFilenameExtension(file.getOriginalFilename());
        return id + SEPARATOR + "idCard" + SEPARATOR + IdUtil.getSnowflakeNextId() + POINT + suffix;
    }

    /**
     * 获取签名路径
     *
     * @param id 上传者id
     * @return {@link String}
     */
    public String getSignPath(Long id){
        //获取文件后缀
        return id + SEPARATOR + "sign" +
                SEPARATOR + IdUtil.getSnowflakeNextId() + ".png";
    }


    /**
     * 使用MultipartFile进行文件上传
     *
     * @param bucketName 存储桶
     * @param file       文件名
     * @throws Exception 异常
     */
    public String uploadImg(String bucketName, MultipartFile file, Long id,String type) throws Exception {
        String contentType = file.getContentType();
        //是否需要压缩
        file = isCompress(file);
        //获取上传的文件的路径
        String path = getUploadPath(file,id,type);
        log.info("上传路径：  "+ path);
        //获取流
        InputStream inputStream = file.getInputStream();
        //上传
        minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(bucketName)
                        .object(path)
                        .contentType(contentType)
                        .stream(inputStream, inputStream.available(), -1)
                        .build());
        return URL + path;
    }

    /**
     * 使用MultipartFile进行文件上传
     *
     * @param bucketName 存储桶
     * @param file       文件名
     * @throws Exception 异常
     */
    public String uploadAdvertisementPicture(String bucketName, MultipartFile file) throws Exception {
        String contentType = file.getContentType();
        DateTime dateTime = new DateTime(new Date());
        //获取文件后缀
        String suffix = org.springframework.util.StringUtils.getFilenameExtension(file.getOriginalFilename());
        String path = "advertisement" + SEPARATOR + dateTime.year() +
                SEPARATOR + dateTime.month() +
                SEPARATOR + dateTime.dayOfMonth() +
                SEPARATOR + IdUtil.getSnowflakeNextId() + POINT + suffix;
        //获取流
        InputStream inputStream = file.getInputStream();
        //上传
        minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(bucketName)
                        .object(path)
                        .contentType(contentType)
                        .stream(inputStream, inputStream.available(), -1)
                        .build());
        return URL + path;
    }


    /**
     * 上传IMG
     *
     * @param bucketName 桶名
     * @param files      文件夹
     * @param id         身份证件
     * @return {@link List}<{@link String}>
     * @throws Exception 例外
     */
    public List<String> uploadImgList(String bucketName, MultipartFile[] files, Long id,String type) throws Exception {
        List<String> imgList = new ArrayList<>(files.length);
        for (MultipartFile file : files) {
            //是否需要压缩
            file = isCompress(file);
            //拼接路径
            String path = getUploadPath(file,id,type);
            String contentType = file.getContentType();
            log.info("contentType:  "+contentType);
            //获取流
            InputStream inputStream = file.getInputStream();
            //上传
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(path)
                            .contentType(contentType)
                            .stream(inputStream, inputStream.available(), -1)
                            .build());
            imgList.add(URL+path);
        }
        return imgList;
    }

    /**
     * 上传签名
     *
     * @param bucketName  存储桶
     * @param inputStream 文件流
     * @param id          身份证件
     * @return {@link String}
     */
    public String uploadSign(String bucketName,InputStream inputStream,Long id) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        String signPath = getSignPath(id);
        minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(bucketName)
                        .object(signPath)
                        .stream(inputStream, inputStream.available(), -1)
                        .build());
        return signPath;
    }

    /**
     * 上传头像
     *
     * @param bucketName 桶名
     * @param id         身份证件
     * @param file       文件
     * @return {@link String}
     * @throws Exception 例外
     */
    public String uploadAvatar(String bucketName, MultipartFile file, Long id) throws Exception {
        //拼接路径
        String path = getAvatarPath(file,id);
        String contentType = file.getContentType();
        log.info("contentType:  "+contentType);
        //获取流
        InputStream inputStream = file.getInputStream();
        //上传
        minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(bucketName)
                        .object(path)
                        .contentType(contentType)
                        .stream(inputStream, inputStream.available(), -1)
                        .build());

        return URL + path;
    }


    /**
     * 上传身份证img
     *
     * @param bucketName 桶名
     * @param id         身份证件
     * @param file       文件
     * @return {@link String}
     * @throws Exception 例外
     */
    public String uploadIdCardImg(String bucketName, MultipartFile file, Long id) throws Exception {
        //拼接路径
        String path = getIdCardPath(file,id);
        String contentType = file.getContentType();
        log.info("contentType:  "+contentType);
        //获取流
        InputStream inputStream = file.getInputStream();
        //上传
        minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(bucketName)
                        .object(path)
                        .contentType(contentType)
                        .stream(inputStream, inputStream.available(), -1)
                        .build());

        return URL + path;
    }

    /**
     * 上传本地文件
     *
     * @param bucketName 存储桶
     * @param objectName 对象名称
     * @param fileName   本地文件路径
     */
    public ObjectWriteResponse uploadFile(String bucketName, String objectName, String fileName) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        return minioClient.uploadObject(
                UploadObjectArgs.builder()
                        .bucket(bucketName)
                        .object(objectName)
                        .filename(fileName)
                        .build());
    }

    /**
     * 通过流上传文件
     *
     * @param bucketName  存储桶
     * @param objectName  文件对象
     * @param inputStream 文件流
     */
    public ObjectWriteResponse uploadFile(String bucketName, String objectName, InputStream inputStream) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        return minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(bucketName)
                        .object(objectName)
                        .stream(inputStream, inputStream.available(), -1)
                        .build());
    }





    /**
     * 创建文件夹或目录
     *
     * @param bucketName 存储桶
     * @param folderPath 目录路径
     */
    public ObjectWriteResponse createFolder(String bucketName, String folderPath) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        if (!SEPARATOR.equals(folderPath.substring(folderPath.length() - 2))) {
            folderPath += SEPARATOR;
        }
        return minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(bucketName)
                        .object(folderPath)
                        .stream(new ByteArrayInputStream(new byte[]{}), 0, -1)
                        .build());
    }

    /**
     * 创建文件夹或目录
     *
     * @param bucketName 存储桶
     * @param uId        用户的id
     */
    public ObjectWriteResponse createFolderByUserId(String uId, String bucketName) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        String folderPath = getFolderPath(uId);
        if (!isFolderExist(bucketName, folderPath)) {
            return minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(folderPath)
                            .stream(new ByteArrayInputStream(new byte[]{}), 0, -1)
                            .build());
        }
        return null;
    }

    /**
     * 通过id 获取minio 的文件夹的路径
     *
     * @param uId 用户的id
     * @return minio 的文件夹的路径
     */
    public String getFolderPath(String uId) {
        return uId + SEPARATOR;
    }

    /**
     * 获取文件信息, 如果抛出异常则说明文件不存在
     *
     * @param bucketName 存储桶
     * @param objectName 文件名称
     */
    public String getFileStatusInfo(String bucketName, String objectName) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        return minioClient.statObject(
                StatObjectArgs.builder()
                        .bucket(bucketName)
                        .object(objectName)
                        .build()).toString();
    }

    /**
     * 拷贝文件
     *
     * @param bucketName    存储桶
     * @param objectName    文件名
     * @param srcBucketName 目标存储桶
     * @param srcObjectName 目标文件名
     */
    public ObjectWriteResponse copyFile(String bucketName, String objectName,
                                        String srcBucketName, String srcObjectName) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        return minioClient.copyObject(
                CopyObjectArgs.builder()
                        .source(CopySource.builder().bucket(bucketName).object(objectName).build())
                        .bucket(srcBucketName)
                        .object(srcObjectName)
                        .build());
    }

    /**
     * 删除文件
     *
     * @param bucketName 存储桶
     * @param objectName 文件名称
     */
    public boolean removeFile(String bucketName, String objectName) {
        boolean isDelete = true;
        try {
            objectName = objectName.replace(StringUtils.chop(URL), "");
            if (isObjectExist(bucketName, objectName)) {
                minioClient.removeObject(
                        RemoveObjectArgs.builder()
                                .bucket(bucketName)
                                .object(objectName)
                                .build());
            }
        } catch (Exception e) {
            e.printStackTrace();
            isDelete = false;
        }
        return isDelete;

    }

    /**
     * 批量删除文件
     *
     * @param bucketName 存储桶
     * @param keys       需要删除的文件列表
     */
    public void removeFiles(String bucketName, List<String> keys) {
        List<DeleteObject> objects = new LinkedList<>();
        keys.forEach(s -> {
            objects.add(new DeleteObject(s));
            try {
                removeFile(bucketName, s);
            } catch (Exception e) {
                log.error("批量删除失败！error: " + e);
            }
        });
    }

    /**
     * 获取文件外链
     *
     * @param bucketName 存储桶
     * @param objectName 文件名
     * @param expires    过期时间 <=7 秒 （外链有效时间（单位：秒））
     * @return url
     */
    public String getPresignedObjectUrl(String bucketName, String objectName, Integer expires) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        GetPresignedObjectUrlArgs args = GetPresignedObjectUrlArgs.builder().expiry(expires).bucket(bucketName).object(objectName).build();
        return minioClient.getPresignedObjectUrl(args);
    }

    /**
     * 获得文件外链
     *
     * @param bucketName 存储桶
     * @param objectName 文件名称
     * @return url
     */
    public String getPresignedObjectUrl(String bucketName, String objectName) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        GetPresignedObjectUrlArgs args = GetPresignedObjectUrlArgs.builder()
                .bucket(bucketName)
                .object(objectName)
                .method(Method.GET).build();
        return minioClient.getPresignedObjectUrl(args);
    }

    /**
     * 获得文件外链
     *
     * @param uId        用户id
     * @param objectName 文件名称
     * @return url         文件的下载链接
     */
    public String getFileUrl(String uId, String objectName) {
        return URL + uId + SEPARATOR + objectName;
    }

    /**
     * 将URLDecoder编码转成UTF8
     *
     * @param str url
     * @return utf-8 的地址
     * @throws UnsupportedEncodingException 异常
     */
    public String getUtf8ByUrlDecoder(String str) throws UnsupportedEncodingException {
        String url = str.replaceAll("%(?![0-9a-fA-F]{2})", "%25");
        return URLDecoder.decode(url, "UTF-8");
    }


}
