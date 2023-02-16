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
        String encrypt = encryptor.encrypt("nyc");
        String encrypt2 = encryptor.encrypt("mysql729");
        //String decrypt = encryptor.decrypt("ZxY08m8wOk4qE/cTEgfzhRbYQlxKg5mhG+kZ6P5lc0MQwy87Z3MouPFWyVGlGyPf");
        //String decrypt2 = encryptor.decrypt("vtK/ygFJN1togHuUS17IbKsc6QQtyK2L2huyXLMmsc9vRQE0zCU+Qo5zQy0FfkQs");
        System.out.println(encrypt);
        System.out.println(encrypt2);
        //System.out.println(decrypt);
        //System.out.println(decrypt2);

    }

}
