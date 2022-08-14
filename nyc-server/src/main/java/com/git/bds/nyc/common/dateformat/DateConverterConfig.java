package com.git.bds.nyc.common.dateformat;


import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author 成大事
 * @since 2022/7/23 14:16
 */
@Configuration
public class DateConverterConfig {

    private static final String YYYY_MM_DD = "yyyy-MM-dd";
    private static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    /**
     * 自定义类型转换,HTTP请求日期字符串转换日期类型,
     * 相当于以前设置进 ConversionServiceFactoryBean
     *
     * @return Converter<java.lang.String,java.util.Date>
     * @author 成大事
     * @since 2022/7/23 14:16
     */
    @Bean
    public Converter<String, LocalDateTime> localDateTimeConverter() {
        return new Converter<String, LocalDateTime>() {
            @Override
            public LocalDateTime convert(String source) {
                try {
                    return parse(source);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                throw new RuntimeException("日期解析错误");
            }
        };
    }

    /**
     * 根据字符串进行解析,将Date转LocalDateTime
     *
     * @param source 日期字符串
     * @return java.time.LocalDateTime
     * @author 成大事
     * @since 2022/7/23 14:16
     */
    public LocalDateTime parse(String source) throws ParseException {
        if (StringUtils.isBlank(source)) {
            return null;
        }
        DateFormat format;
        source = source.trim();
        //判断是否yyyy-MM-dd格式
        if(source.matches("^\\d{4}-\\d{1,2}-\\d{1,2}$")){
            format = new SimpleDateFormat(YYYY_MM_DD);
            Date date = format.parse(source);
            return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
            //判断是否yyyy-MM-dd HH:mm:ss格式
        }else if(source.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}:\\d{1,2}$")){
            format = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
            Date date = format.parse(source);
            return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        }else {
            throw new IllegalArgumentException("Invalid false value " + source);
        }
    }
}

