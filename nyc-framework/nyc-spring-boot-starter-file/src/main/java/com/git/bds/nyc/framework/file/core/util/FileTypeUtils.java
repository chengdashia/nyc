package com.git.bds.nyc.framework.file.core.util;

import com.alibaba.ttl.TransmittableThreadLocal;
import lombok.SneakyThrows;
import org.apache.tika.Tika;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件类型 Utils
 *
 * @author 芋道源码
 */
public class FileTypeUtils {

    public static final  List<String> SUFFIX = new ArrayList<>();

    static {
        SUFFIX.add("jpg");
        SUFFIX.add("png");
        SUFFIX.add("ras");
        SUFFIX.add("rgb");
        SUFFIX.add("giff");
        SUFFIX.add("jfif");
        SUFFIX.add("gif");
        SUFFIX.add("tif");
        SUFFIX.add("pdf");
        SUFFIX.add("ps");
        SUFFIX.add("cod");
        SUFFIX.add("ico");
        SUFFIX.add("svg");
    }
    private FileTypeUtils(){}

    private static final ThreadLocal<Tika> TIKA = TransmittableThreadLocal.withInitial(Tika::new);

    /**
     * 获得文件的 mineType，对于doc，jar等文件会有误差
     *
     * @param data 文件内容
     * @return mineType 无法识别时会返回“application/octet-stream”
     */
    @SneakyThrows
    public static String getMineType(byte[] data) {
        return TIKA.get().detect(data);
    }

    /**
     * 已知文件名，获取文件类型，在某些情况下比通过字节数组准确，例如使用jar文件时，通过名字更为准确
     *
     * @param name 文件名
     * @return mineType 无法识别时会返回“application/octet-stream”
     */
    public static String getMineType(String name) {
        return TIKA.get().detect(name);
    }

    /**
     * 在拥有文件和数据的情况下，最好使用此方法，最为准确
     *
     * @param data 文件内容
     * @param name 文件名
     * @return mineType 无法识别时会返回“application/octet-stream”
     */
    public static String getMineType(byte[] data, String name) {
        return TIKA.get().detect(data, name);
    }

}
