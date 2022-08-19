package com.git.bds.nyc.gen.screw;


import cn.smallbun.screw.core.Configuration;
import cn.smallbun.screw.core.engine.EngineConfig;
import cn.smallbun.screw.core.engine.EngineFileType;
import cn.smallbun.screw.core.engine.EngineTemplateType;
import cn.smallbun.screw.core.execute.DocumentationExecute;
import cn.smallbun.screw.core.process.ProcessConfig;
import com.git.bds.nyc.constant.Constants;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.Collections;

/**
 * 用户生成数据库的表结构
 * @author 成大事
 * @since 2022/8/2 18:41
 */
public class ScrewUtil {
    /**驱动*/
    private static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    /**可数据连接的url*/
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306";
    /**数据名称*/
    private static final String DB_NAME = "nongyingc";
    /**数据库用户名*/
    private static final String DB_USERNAME = "root";
    /**数据库密码*/
    private static final String DB_PASSWORD = "mysql729";

    /**生成文件的输出路径*/
    private static final String FILE_OUTPUT_DIR = ".\\dbDoc\\";
    /**可以设置 HTML 或者 Markdown 格式*/
    private static final EngineFileType FILE_OUTPUT_TYPE = EngineFileType.HTML;
    /**文档的名称*/
    private static final String DOC_FILE_NAME = "农营C";
    /**版本号*/
    private static final String DOC_VERSION = "1.0.0";
    /**文档的描述*/
    private static final String DOC_DESCRIPTION = "文档描述";

    public static void main(String[] args) {
        // 创建 screw 的配置
        Configuration config = Configuration.builder()
                // 版本
                .version(DOC_VERSION)
                // 描述
                .description(DOC_DESCRIPTION)
                // 数据源
                .dataSource(buildDataSource())
                // 引擎配置
                .engineConfig(buildEngineConfig())
                // 处理配置
                .produceConfig(buildProcessConfig())
                .build();

        // 执行 screw，生成数据库文档
        new DocumentationExecute(config).execute();
    }

    /**
     * 创建数据源
     */
    private static DataSource buildDataSource() {
        // 创建 HikariConfig 配置类
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(DRIVER_NAME);
        hikariConfig.setJdbcUrl(DB_URL + Constants.SEPARATOR + DB_NAME);
        hikariConfig.setUsername(DB_USERNAME);
        hikariConfig.setPassword(DB_PASSWORD);
        // 设置可以获取 tables remarks 信息
        hikariConfig.addDataSourceProperty("useInformationSchema", "true");
        // 创建数据源
        return new HikariDataSource(hikariConfig);
    }

    /**
     * 创建 screw 的引擎配置
     */
    private static EngineConfig buildEngineConfig() {
        return EngineConfig.builder()
                //生成文件路径
                .fileOutputDir(FILE_OUTPUT_DIR)
                //打开目录
                .openOutputDir(false)
                //文件类型
                .fileType(FILE_OUTPUT_TYPE)
                //文件类型
                .produceType(EngineTemplateType.freemarker)
                //自定义文件名称
                .fileName(DOC_FILE_NAME)
                .build();
    }

    /**
     * 创建 screw 的处理配置，一般可忽略
     * 指定生成逻辑、当存在指定表、指定表前缀、指定表后缀时，将生成指定表，其余表不生成、并跳过忽略表配置
     */
    private static ProcessConfig buildProcessConfig() {
        return ProcessConfig.builder()
                // 根据名称指定表生成
                .designatedTableName(Collections.<String>emptyList())
                //根据表前缀生成
                .designatedTablePrefix(Collections.<String>emptyList())
                // 根据表后缀生成
                .designatedTableSuffix(Collections.<String>emptyList())
                // 忽略表名
                .ignoreTableName(Arrays.asList("test_user", "test_group"))
                // 忽略表前缀
                .ignoreTablePrefix(Collections.singletonList("test_"))
                // 忽略表后缀
                .ignoreTableSuffix(Collections.singletonList("_test"))
                .build();
    }
}
