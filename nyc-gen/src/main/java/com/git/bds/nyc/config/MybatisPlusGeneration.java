package com.git.bds.nyc.config;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.fill.Column;
import com.github.yulichang.base.MPJBaseMapper;
import com.github.yulichang.base.MPJBaseService;
import com.github.yulichang.base.MPJBaseServiceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author 成大事
 * @since 2022/5/25 12:28
 */
public class MybatisPlusGeneration {
    public static void main(String[] args) {
        String databaseName = "nyc";
        String url = "jdbc:mysql://localhost:3306/"+databaseName+"?useSSL=false&serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf-8";
        String username = "root";
        String password = "mysql729";

        //表名集合
        List<String> tables = new ArrayList<>();
        tables.add("user");

        FastAutoGenerator.create(url, username, password)
                //全局配置
                .globalConfig(builder -> {
                    builder.disableOpenDir()  //禁止打开输出目录
                            .outputDir(System.getProperty("user.dir") + "\\nyc-gen\\src\\main\\java")   //指定输出目录
                            .author("成大事")   //作者名
//                            .enableKotlin()      //开启 kotlin 模式
                            .enableSwagger()     //开启 swagger 模式
                            .dateType(DateType.TIME_PACK)     //时间策略  定义生成的实体类中日期的类型 TIME_PACK=LocalDateTime;ONLY_DATE=Date;
                            .commentDate("yyyy-MM-dd HH:mm:ss");   //注释日期
//                            .disableOpenDir();   //禁止打开输出目录，默认打开
                })
                //包配置
                .packageConfig(builder -> {
                    builder.parent("com.git.bds.nyc")     //父包名
//                            .moduleName("demo")
                            .entity("domain")                 //Entity 包名
                            .service("service")             //	Service 包名
                            .serviceImpl("service.impl")    //Service Impl 包名
                            .mapper("dao")               //Mapper 包名
                            .xml("dao.xml")              //	Mapper XML 包名
                            .controller("controller")       //Controller 包名
                            .other("utils")                //自定义文件包名	输出自定义文件时所用到的包名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, System.getProperty("user.dir") +
                                    "\\nyc-gen\\src\\main\\java\\com\\git\\bds\\nyc\\dao\\xml"));//指定xml位置
                    //配置 **Mapper.xml 路径信息：项目的 resources 目录的 Mapper 目录下
                })
                //策略配置
                .strategyConfig(builder -> {
                    builder.addInclude(tables)
                            .addTablePrefix("tbl_","user_")//表名前缀，配置后生成的代码不会有此前缀
                            .addFieldPrefix("user_")


                            //service 的配置
                            .serviceBuilder()
                            .superServiceClass(MPJBaseService.class)           //	设置 service 接口父类
                            .superServiceImplClass(MPJBaseServiceImpl.class)  //设置 service 实现类父类
                            .formatServiceFileName("%sService")//服务层接口名后缀
                            .formatServiceImplFileName("%sServiceImpl")//服务层实现类名后缀

                            //pojo 的配置
                            .entityBuilder()
                            .superClass(Model.class)
                            .enableActiveRecord()   //开启 ActiveRecord 模型
                            .enableLombok()//实体类使用lombok,需要自己引入依赖
                            .enableChainModel() // 开启链式模型
                            .enableColumnConstant()  //开启字段常量
                            .enableTableFieldAnnotation()  //开启生成实体时生成字段注解
                            .enableRemoveIsPrefix()          //	开启 Boolean 类型字段移除 is 前缀
                            //.disableSerialVersionUID()  //不实现 Serializable 接口，不生产 SerialVersionUID
                            //.logicDeleteColumnName("status")//逻辑删除字段，使用delete方法删除数据时会将status设置为1。调用update方法时并不会将该字段放入修改字段中，而是在条件字段中
                            .enableTableFieldAnnotation()// 开启生成实体时生成字段注解@TableField
                            .idType(IdType.ASSIGN_ID)  //设置主键的生成策略 全局主键类型
                            // 数据库表字段映射到实体的命名策略
                            .columnNaming(NamingStrategy.underline_to_camel)                //数据库表字段映射到实体的命名策略：下划线转驼峰命
                            .naming(NamingStrategy.underline_to_camel)                      //数据库表映射到实体的命名策略：下划线转驼峰命
                            //.formatFileName("%sEntity")   //格式化文件名称
                            .addTableFills(
                                    new Column("create_time", FieldFill.INSERT),
                                    new Column("update_time", FieldFill.INSERT_UPDATE)
                            )   //添加表字段填充，"create_time"字段自动填充为插入时间，"modify_time"字段自动填充为插入修改时间


                            //controller 的配置
                            .controllerBuilder()
                            .formatFileName("%sController")//控制类名称后缀
                            .enableRestStyle()

                            //mapper 的配置
                            .mapperBuilder()
                            .enableBaseResultMap()               //启用 BaseResultMap 生成
                            .enableBaseColumnList()              //	启用 BaseColumnList
                            .enableMapperAnnotation()           //开启 @Mapper 注解
                            .superClass(MPJBaseMapper.class)    //设置父类
                            .formatMapperFileName("%sDao")    //格式化 mapper 文件名称
                            .formatXmlFileName("%sXml");       //格式化 Xml 文件名称
                })
//                .templateEngine(new FreemarkerTemplateEngine())
//                .templateEngine(new VelocityTemplateEngine())   // 默认的
                .execute();


    }

}
