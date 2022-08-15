package com.git.bds.nyc.config.mybatis.encrypt;
import com.bigdatastudio.nongyingc.exception.BaseException;
import com.bigdatastudio.nongyingc.exception.ExceptionEnum;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


/**
 *  aes 加密的工具类
 *  1.存储 加密的秘钥key
 *  2.实现 aes 加密
 *  3.实现aes解密的功能
 *  @author 成大事
 *  @since 2022/7/21 21:49
 */
public class Aes {

    /**     定义 aes 加密的key
         密钥  必须是16位, 自定义,
         如果不是16位, 则会出现InvalidKeyException: Illegal KEY size
      解决方案有两种：
    1.需要安装Java Cryptography Extension (JCE) Unlimited Strength Jurisdiction Policy Files(可以在Oracle下载).
    2.设置设置key的长度为16个字母和数字的字符窜（128 Bit/8=16字符）就不报错了。*/
    private static final String KEY = "BIGDATASTUDIO666";

    /**定义加密的编码*/
    private static final String CHARSET = "utf-8";

    /**偏移量*/
    private static final int OFFSET = 16;
    private static final String TRANSFORMATION = "Aes/CBC/PKCS5Padding";
    private static final String ALGORITHM = "Aes";

    /**
     * 加密
     *
     * @param content 内容
     * @return  加密的内容
     */
    public static String encrypt(String content) {
        return encrypt(content, KEY);
    }

    /**
     * 解密
     *
     * @param content 内容
     * @return 解密结果
     */
    public static String decrypt(String content) {
        return decrypt(content, KEY);
    }

    /**
     * 加密
     *
     * @param content 需要加密的内容
     * @param key     加密密码
     * @return 加密的内容
     */
    public static String encrypt(String content, String key) {
        try {
            SecretKeySpec skey = new SecretKeySpec(key.getBytes(), ALGORITHM);
            IvParameterSpec iv = new IvParameterSpec(key.getBytes(), 0, OFFSET);
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            byte[] byteContent = content.getBytes(CHARSET);
            // 初始化
            cipher.init(Cipher.ENCRYPT_MODE, skey, iv);
            byte[] result = cipher.doFinal(byteContent);
            // 加密
            return new Base64().encodeToString(result);
        } catch (Exception e) {
            throw new BaseException(ExceptionEnum.BODY_NOT_MATCH.getResultCode(),ExceptionEnum.BODY_NOT_MATCH.getResultMsg());
        }
    }

    /**
     * Aes（256）解密
     *
     * @param content 待解密内容
     * @param key     解密密钥
     * @return 解密之后
     */
    public static String decrypt(String content, String key) {
        try {

            SecretKeySpec skey = new SecretKeySpec(key.getBytes(), ALGORITHM);
            IvParameterSpec iv = new IvParameterSpec(key.getBytes(), 0, OFFSET);
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            // 初始化
            cipher.init(Cipher.DECRYPT_MODE, skey, iv);
            byte[] result = cipher.doFinal(new Base64().decode(content));
            // 解密
            return new String(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}

