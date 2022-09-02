package com.git.bds.nyc.framework.swagger.config;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

/**
 * @author 成大事
 * @since 2022/8/19 9:01
 */
@Data
@Component
@ConfigurationProperties(prefix = "knife4j.info")
public class SwaggerProperties {

    /**
     * 标题
     */
    private String title = null;

    /**
     * 描述
     */
    private String description = null;

    /**
     * 联系人信息
     */
    @NestedConfigurationProperty
    private Contact contact = null;

    /**
     * 许可证
     */
    @NestedConfigurationProperty
    private License license = null;

    /**
     * 版本
     */
    private String version = null;


}
