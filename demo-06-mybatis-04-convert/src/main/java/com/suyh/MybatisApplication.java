package com.suyh;

import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.suyh.config.CodegenConvert;
import com.suyh.vo.CodegenTableDO;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MybatisApplication {
    public static void main(String[] args) {
        // 使用方式，没有详细测试和验证。
        TableInfo tableInfo = new TableInfo(null, null);
        CodegenTableDO convert = CodegenConvert.INSTANCE.convert(tableInfo);
    }
}
