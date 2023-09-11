package com.suyh3304;

import com.suyh3304.client.FileClientConfig;
import com.suyh3304.client.FtpFileClientConfig;
import com.suyh3304.client.LocalFileClientConfig;
import com.suyh3304.util.JsonUtil;
import com.suyh3304.vo.FileConfigVo;

/**
 * @author suyh
 * @since 2023-09-11
 */
public class ApplicationDemo3304 {
    public static void main(String[] args) {
        test1();
        test2();
    }

    private static void test1() {
        // 测试使用LocalFileClientConfig 进行序列化
        LocalFileClientConfig clientConfig = new LocalFileClientConfig();
        clientConfig.setDomain("domain").setBasePath("basePath");

        String jsonValue = serializable(clientConfig);
        assert jsonValue != null;
        System.out.println("LocalFileClientConfig json value: " + jsonValue);

        FileConfigVo vo = deserialize(jsonValue);
        assert vo != null;

        System.out.println("LocalFileClientConfig vo: " + vo);
    }

    private static void test2() {
        // 测试使用LocalFileClientConfig 进行序列化
        FtpFileClientConfig clientConfig = new FtpFileClientConfig();
        clientConfig.setBasePath("ftpBasePath").setDomain("ftpDomain")
                .setHost("127.0.0.1").setPort(8080).setUsername("suyh");

        String jsonValue = serializable(clientConfig);
        assert jsonValue != null;
        System.out.println("FtpFileClientConfig json value: " + jsonValue);

        FileConfigVo vo = deserialize(jsonValue);
        assert vo != null;

        System.out.println("FtpFileClientConfig vo: " + vo);
    }

    private static String serializable(FileClientConfig clientConfig) {
        FileConfigVo vo = new FileConfigVo();
        vo.setConfig(clientConfig).setId(1L).setDeleted(Boolean.FALSE)
                .setName("name").setRemark("remark");
        return JsonUtil.serializable(vo);
    }

    private static FileConfigVo deserialize(String jsonValue) {
        return JsonUtil.deserialize(jsonValue, FileConfigVo.class);
    }
}
