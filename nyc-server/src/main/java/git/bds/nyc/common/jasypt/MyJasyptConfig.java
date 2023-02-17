package git.bds.nyc.common.jasypt;


import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 成大事
 * @since 2022/7/16 16:57
 */
@Configuration
public class MyJasyptConfig {
    private final String KEY = "PEB123@321BEP";

    @Bean(name = "CodeEncryptBean")
    public StringEncryptor CodeEncryBean() {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();

        SimpleStringPBEConfig config = new SimpleStringPBEConfig();

        config.setPassword(KEY);
        config.setAlgorithm("PBEWithMD5AndDES");
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setIvGeneratorClassName("org.jasypt.iv.RandomIvGenerator");
        config.setStringOutputType("base64");
        encryptor.setConfig(config);
        return encryptor;
    }

    //测试
    public static void main(String[] args) {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword("PEB123@321BEP");
        config.setAlgorithm("PBEWithMD5AndDES");
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setIvGeneratorClassName("org.jasypt.iv.RandomIvGenerator");
        config.setStringOutputType("base64");
        encryptor.setConfig(config);
        //===================================
        String root = encryptor.encrypt("nyc");
        String pwd = encryptor.encrypt("mysql729");
        String appid = encryptor.encrypt("wx2b1b9f396a67b94c");
        String secret = encryptor.encrypt("b76856231c6db17c47e3e88779c95889");
        String redisPwd = encryptor.encrypt("12345678");
        System.out.println("root = " + root);
        System.out.println("pwd = " + pwd);
        System.out.println("appid = " + appid);
        System.out.println("secret = " + secret);
        System.out.println("redisPwd = " + redisPwd);

    }

}
