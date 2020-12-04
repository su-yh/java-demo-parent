package com.suyh.es3202.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author 苏雲弘
 * @Description:
 * @date 2020-04-03 17:01
 */
@Configuration
@EnableSwagger2
//@Profile({"local"}) // 限制，只有当spring.profiles.active = local 时此配置才有效
public class SwaggerConfig {

    /**
     * 通过分组可以在生成的文档是下拉选择，查看哪一个分组
     */
    @Bean
    public Docket createRestApiCustomer() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("demo-32-es-02")
                .select()
                // 指定要扫描的基础包
                .apis(RequestHandlerSelectors.basePackage("com.suyh.es3202.controller"))
                .paths(PathSelectors.any())
                .build()
                .ignoredParameterTypes(ApiIgnore.class);
    }

    private ApiInfo apiInfo() {
        // 前端访问地址：http://localhost:8080/swagger-ui.html
        return new ApiInfoBuilder()
                .title("TITLE 显示标题")
                .description("description 描述说明")
                .version("v1.0")
                .build();
    }

}
