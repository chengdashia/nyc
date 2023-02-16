package git.bds.nyc.communal.util;


import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Base64有用
 *
 * @author 成大事
 * @since 2023/01/01
 */
public class Base64Util {

    private Base64Util(){

    }

    /**
     * 将图片文件转成字节数组，并对其进行Base64编码处理
     * @param imgPath 图片文件路径
     * @return  编码后的字符串
     */
    public static String getImageEncode(String imgPath){
        InputStream in = null;
        byte[] bytes ;
        //返回Base64 编码的字节数组字符串
        String encode = null;
        //对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        try{
            //读取图片字节数组
            in = Files.newInputStream(Paths.get(imgPath));
            bytes = new byte[in.available()];
            in.read(bytes);
            encode = encoder.encode(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert in != null;
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return encode;
    }


    /**
     * 对字节数组字符串进行Base64解码并生成图片
     * @param base64Str 前端传过来的Base64编码的json数据
     * @return boolean
     */
    public static InputStream generateImageStream(String base64Str) throws IOException {
        if ("".equals(base64Str)){
            // 图像数据为空
            return null;
        }
        base64Str = base64Str.substring(base64Str.indexOf(",") + 1);
        BASE64Decoder decoder = new BASE64Decoder();
        InputStream inputStream;
        try {
            // Base64解码
            byte[] bytes = decoder.decodeBuffer(base64Str);
            for (int i = 0; i < bytes.length; ++i) {
                // 调整异常数据
                if (bytes[i] < 0) {
                    bytes[i] += 256;
                }
            }
            inputStream = new ByteArrayInputStream(bytes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return inputStream;
    }
}
