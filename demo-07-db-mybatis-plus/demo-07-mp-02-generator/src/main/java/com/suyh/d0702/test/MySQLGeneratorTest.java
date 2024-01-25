package com.suyh.d0702.test;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;

/**
 * @author suyh
 * @since 2024-01-25
 */
public class MySQLGeneratorTest extends BaseGeneratorTest {

    /**
     * 数据源配置
     */
    private static final DataSourceConfig DATA_SOURCE_CONFIG = new DataSourceConfig
            .Builder("jdbc:mysql://isuyh.com:3306/flink-cds?serverTimezone=Asia/Shanghai", "flink-cds", "flink-cds")
            .schema("baomidou")
            .build();

    /**
     * <a href="https://baomidou.com/pages/981406">mybatis-plus可选配置</a>
     */
    public void testSimple() {
        StrategyConfig strategyConfig = strategyConfig()
                .build();
        GlobalConfig globalConfig = globalConfig()
                .disableOpenDir()   // 禁止打开输出目录
                .outputDir("/Users/suyunhong/suyh-develop/github/java-demo-springboot/demo-07-db-mybatis-plus/demo-07-mp-02-generator/src/main/java")    // 输出目录
                .build();
        AutoGenerator generator = new AutoGenerator(DATA_SOURCE_CONFIG);
        generator.strategy(strategyConfig);
        generator.global(globalConfig);
        generator.execute();
    }



    public static void main(String[] args) {
        MySQLGeneratorTest mySQLGeneratorTest = new MySQLGeneratorTest();
        mySQLGeneratorTest.testSimple();
    }
}
