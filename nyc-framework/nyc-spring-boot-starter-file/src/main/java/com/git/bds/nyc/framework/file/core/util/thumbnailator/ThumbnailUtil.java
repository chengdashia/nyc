package com.git.bds.nyc.framework.file.core.util.thumbnailator;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.apache.http.entity.ContentType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author 成大事
 * @since 2022/7/7 14:16
 * 一个图片压缩、缩放和添加水印的工具类
 */
public class ThumbnailUtil {

    private ThumbnailUtil(){

    }

    /**
     * 指定比例缩放 scale(),参数小于1,缩小;大于1,放大
     *
     * @param file   源文件路径
     * @param scale      指定比例
     * @param toFile     生成文件路径
     */
    public static boolean changeScale(MultipartFile file, double scale, String toFile){
        try {
            Thumbnails.of(file.getInputStream())
                    //指定比例缩放 scale(),参数小于1,缩小;大于1,放大
                    .scale(scale)
                    //输出路径
                    .toFile(toFile);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;

    }

    /**
     * 指定比例缩放 scale(),参数小于1,缩小;大于1,放大
     *
     * @param file   源文件路径
     */
    public static MultipartFile changeScale(MultipartFile file){
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            Thumbnails.of(file.getInputStream()).scale(0.5f).outputQuality(0.50f).toOutputStream(out);
            byte[] bytes = out.toByteArray();
            InputStream inputStream = new ByteArrayInputStream(bytes);
            return new MockMultipartFile(ContentType.APPLICATION_OCTET_STREAM.toString(), inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * 指定大小缩放 若图片横比width小，高比height小，放大
     * 若图片横比width小，高比height大，高缩小到height，图片比例不变
     * 若图片横比width大，高比height小，横缩小到width，图片比例不变
     * 若图片横比width大，高比height大，图片按比例缩小，横为width或高为height
     *
     * @param file  源文件路径
     * @param width     宽
     * @param height    高
     * @param toFile    生成文件路径
     */
    public static boolean changeSize(MultipartFile file, int width, int height, String toFile){
        try {
            Thumbnails.of(file.getInputStream())
                    .size(width, height)
                    .toFile(toFile);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 指定大小缩放 若图片横比width小，高比height小，放大
     * 若图片横比width小，高比height大，高缩小到height，图片比例不变
     * 若图片横比width大，高比height小，横缩小到width，图片比例不变
     * 若图片横比width大，高比height大，图片按比例缩小，横为width或高为height
     *
     * @param file  源文件路径
     * @param scale  缩放比率
     * @param toFile    生成文件路径
     */
    public static boolean changeSize(MultipartFile file, double scale, String toFile){
        try {
            Thumbnails.of(file.getInputStream())
                    .scale(scale)
                    .outputFormat("jpg")
                    .toFile(toFile);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 添加水印 watermark(位置,水印,透明度)
     *
     * @param file  源文件路径
     * @param positions    水印位置
     * @param watermark   水印文件路径
     * @param opacity   水印透明度
     * @param toFile    生成文件路径
     */
    public static boolean watermark(MultipartFile file, Positions positions, MultipartFile watermark, float opacity, String toFile){
        try {
            Thumbnails.of(file.getInputStream())
                    //指定比例缩放 scale(),参数小于1,缩小;大于1,放大
                    .scale(1)
                    //添加水印 positions 设定区域  先读取图片的流形式  opacity   水印透明度
                    .watermark(positions, ImageIO.read(watermark.getInputStream()), opacity)
                    .toFile(toFile);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 图片旋转 rotate(度数),顺时针旋转
     *
     * @param file  源文件路径
     * @param rotate    旋转度数
     * @param toFile    生成文件路径
     */
    public static boolean rotate(MultipartFile file, double rotate, String toFile){
        try {
            Thumbnails.of(file.getInputStream())
                    //指定比例缩放 scale(),参数小于1,缩小;大于1,放大
                    .scale(1)
                    //旋转度数
                    .rotate(rotate)
                    // 生成文件路径
                    .toFile(toFile);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 图片裁剪 sourceRegion()有多种构造方法，示例使用的是sourceRegion(裁剪位置,宽,高)
     *
     * @param file  源文件路径
     * @param positions    裁剪位置
     * @param width     裁剪区域宽
     * @param height    裁剪区域高
     * @param toFile    生成文件路径
     */
    public static boolean region(MultipartFile file, Positions positions, int width, int height, String toFile){
        try {
            Thumbnails.of(file.getInputStream())
                    //指定比例缩放 scale(),参数小于1,缩小;大于1,放大
                    .scale(1)
                    //源区域，position 指定区域
                    .sourceRegion(positions, width, height)
                    //生成文件路径
                    .toFile(toFile);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
