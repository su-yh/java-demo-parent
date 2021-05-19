
package com.suyh38.use;

import com.suyh38.spi.SpiLoader;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author sWX5327794
 * @since 2021-05-19
 */
@Slf4j
public class UsageDemo {
    public static void main(String[] args) {
        // 它的功能就是加载 META-INF/services/com.suyh38.use.InitFunc 文件，每一行一个类。
        // 并可递归加载每一个jar 包中的配置，并合并到一起。
        List<InitFunc> initFuncs = SpiLoader.of(InitFunc.class).loadInstanceListSorted();
        log.info("initFuncs: {}", initFuncs);
    }
}
