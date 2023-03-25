package com.suyh1202.config;

import io.swagger.annotations.Api;
import org.springframework.boot.actuate.autoconfigure.endpoint.web.CorsEndpointProperties;
import org.springframework.boot.actuate.autoconfigure.endpoint.web.WebEndpointProperties;
import org.springframework.boot.actuate.autoconfigure.web.server.ManagementPortType;
import org.springframework.boot.actuate.endpoint.ExposableEndpoint;
import org.springframework.boot.actuate.endpoint.web.EndpointLinksResolver;
import org.springframework.boot.actuate.endpoint.web.EndpointMapping;
import org.springframework.boot.actuate.endpoint.web.EndpointMediaTypes;
import org.springframework.boot.actuate.endpoint.web.ExposableWebEndpoint;
import org.springframework.boot.actuate.endpoint.web.WebEndpointsSupplier;
import org.springframework.boot.actuate.endpoint.web.annotation.ControllerEndpointsSupplier;
import org.springframework.boot.actuate.endpoint.web.annotation.ServletEndpointsSupplier;
import org.springframework.boot.actuate.endpoint.web.servlet.WebMvcEndpointHandlerMapping;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.util.StringUtils;
import springfox.boot.starter.autoconfigure.OpenApiAutoConfiguration;
import springfox.boot.starter.autoconfigure.SpringfoxConfigurationProperties;
import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 在有些时候我们的接口都需要认证的，而有登录 接口是不需要认证的，所以我们需要将认证和不需要认证的分开。
 * 将需要认证的接口，让添加 Authorization 请求头信息。
 * 可以在注解中指定，如： @ApiOperation(authorizations = {@Authorization(value = "Authorization")})
 * 但是需要做相应的配置
 *
 * 访问路径：http://localhost:8080/swagger-ui/index.html
 *
 * 参考配置属性对象：{@link SpringfoxConfigurationProperties} 以及{@link OpenApiAutoConfiguration}
 */
@ConditionalOnProperty(value = "springfox.documentation.enabled", havingValue = "true", matchIfMissing = true)
@EnableOpenApi
@Configuration
public class Swagger3ConfigurationPlus {
    public static final String AUTH_KEY = HttpHeaders.AUTHORIZATION;

    /**
     * 通过分组可以在生成的文档是下拉选择，查看哪一个分组
     */
    @Bean
    public Docket userDocket() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                // 按分组显示的名称
                .groupName("suyh-分组")
                .select()
                // 指定要扫描的基础包，并且类上面有注解 @Api 的才会被扫描到
                .apis(RequestHandlerSelectors.basePackage("com.suyh1202.controller"))
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(schemes())
                .ignoredParameterTypes(ApiIgnore.class);
    }

    // 生成接口信息，包括标题、联系人等
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Swagger3接口文档")
                .description("如有疑问，请联系开发工程师。")
                .contact(new Contact("suyh", "https://www.suyh.com", "suyh@sina.com.com"))
                .version("1.0")
                .build();
    }

    private List<SecurityScheme> schemes() {
        List<SecurityScheme> list = new ArrayList<>();
        // 这里使用ApiKey 的方式，同时还可以使用其他方式。但我还没弄清楚，只是暂时这样用。
        list.add(new ApiKey(AUTH_KEY, "Authorization", "header"));
        // 比如使用BasicAuth
//         list.add(new springfox.documentation.service.BasicAuth("basicAuthToken"));
        return list;
    }

    /**
     * swagger3 与springboot 2.6 以上版本冲突的解决，
     * 同时添加配置：spring.mvc.pathmatch.matching-strategy=ant_path_matcher
     * 参考：https://github.com/springfox/springfox/issues/3462
     * <p>
     * 另外一个问题的冲突是spring-boot-actuator 与swagger3 版本之间的不兼容
     */
    @Bean
    public WebMvcEndpointHandlerMapping webEndpointServletHandlerMapping(
            WebEndpointsSupplier webEndpointsSupplier,
            ServletEndpointsSupplier servletEndpointsSupplier,
            ControllerEndpointsSupplier controllerEndpointsSupplier,
            EndpointMediaTypes endpointMediaTypes,
            CorsEndpointProperties corsProperties,
            WebEndpointProperties webEndpointProperties,
            Environment environment) {
        List<ExposableEndpoint<?>> allEndpoints = new ArrayList<>();
        Collection<ExposableWebEndpoint> webEndpoints = webEndpointsSupplier.getEndpoints();
        allEndpoints.addAll(webEndpoints);
        allEndpoints.addAll(servletEndpointsSupplier.getEndpoints());
        allEndpoints.addAll(controllerEndpointsSupplier.getEndpoints());
        String basePath = webEndpointProperties.getBasePath();
        EndpointMapping endpointMapping = new EndpointMapping(basePath);
        boolean shouldRegisterLinksMapping = this.shouldRegisterLinksMapping(webEndpointProperties, environment, basePath);
        return new WebMvcEndpointHandlerMapping(
                endpointMapping, webEndpoints, endpointMediaTypes, corsProperties.toCorsConfiguration(),
                new EndpointLinksResolver(allEndpoints, basePath), shouldRegisterLinksMapping, null);
    }


    private boolean shouldRegisterLinksMapping(
            WebEndpointProperties webEndpointProperties,
            Environment environment, String basePath) {
        return webEndpointProperties.getDiscovery().isEnabled() && (StringUtils.hasText(basePath) || ManagementPortType.get(environment).equals(ManagementPortType.DIFFERENT));
    }
}
