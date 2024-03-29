package git.bds.nyc.framework.jackson.config;

import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author 成大事
 * @since 2022/7/23 14:15
 */
@Configuration
public class LocalDateTimeSerializerConfig {

    /**
     * 注入配置文件格式化的格式
     */
    private static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    /**
     *  注册响应json格式的LocalDateTime日期格式序列化器,指定格式
     *
     * @return LocalDateTimeSerializer
     * @author 成大事
     * @since 2022/7/23 14:16
     */
    @Bean
    public LocalDateTimeSerializer localDateTimeSerializer() {
        return new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(YYYY_MM_DD_HH_MM_SS));
    }

    /**
     *  注册json请求方式的LocalDateTime反序列化器,指定格式
     *
     * @return LocalDateTimeDeserializer
     * @author 成大事
     * @since 2022/7/23 14:16
     */
    @Bean
    public LocalDateTimeDeserializer localDateTimeDeserializer() {
        return new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(YYYY_MM_DD_HH_MM_SS));
    }

    /**
     *  格式化日期类型,响应对应格式化日期字符串
     *
     * @return Jackson2ObjectMapperBuilderCustomizer
     * @author 成大事
     * @since 2022/7/23 14:16
     */
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return builder -> {
            //返回json格式,前端序列化为字符串
            builder.serializerByType(LocalDateTime.class, localDateTimeSerializer());
            //从json对象日期字符串反序列化为日期对象
            builder.deserializerByType(LocalDateTime.class, localDateTimeDeserializer());
            //Jackson全局转化long类型为String，解决jackson序列化时long类型缺失精度问题
            builder.serializerByType(Long.class, ToStringSerializer.instance);
            builder.serializerByType(Long.TYPE, ToStringSerializer.instance);
        };
    }




}

