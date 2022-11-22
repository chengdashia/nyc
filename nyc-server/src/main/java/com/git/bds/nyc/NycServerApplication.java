package com.git.bds.nyc;

import cn.easyes.starter.register.EsMapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Indexed;

@Indexed
@EsMapperScan("com.git.bds.nyc.**.mapper.ee")
@SpringBootApplication
public class NycServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(NycServerApplication.class, args);
    }

}
