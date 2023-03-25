//package com.suyh1202.config;
//
//import io.swagger.annotations.ApiOperation;
//import org.springframework.boot.actuate.autoconfigure.endpoint.web.CorsEndpointProperties;
//import org.springframework.boot.actuate.autoconfigure.endpoint.web.WebEndpointProperties;
//import org.springframework.boot.actuate.autoconfigure.web.server.ManagementPortType;
//import org.springframework.boot.actuate.endpoint.ExposableEndpoint;
//import org.springframework.boot.actuate.endpoint.web.EndpointLinksResolver;
//import org.springframework.boot.actuate.endpoint.web.EndpointMapping;
//import org.springframework.boot.actuate.endpoint.web.EndpointMediaTypes;
//import org.springframework.boot.actuate.endpoint.web.ExposableWebEndpoint;
//import org.springframework.boot.actuate.endpoint.web.WebEndpointsSupplier;
//import org.springframework.boot.actuate.endpoint.web.annotation.ControllerEndpointsSupplier;
//import org.springframework.boot.actuate.endpoint.web.annotation.ServletEndpointsSupplier;
//import org.springframework.boot.actuate.endpoint.web.servlet.WebMvcEndpointHandlerMapping;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//import org.springframework.util.StringUtils;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import springfox.documentation.annotations.ApiIgnore;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.oas.annotations.EnableOpenApi;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.service.ApiKey;
//import springfox.documentation.service.AuthorizationScope;
//import springfox.documentation.service.Contact;
//import springfox.documentation.service.SecurityReference;
//import springfox.documentation.service.SecurityScheme;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spi.service.contexts.SecurityContext;
//import springfox.documentation.spring.web.plugins.Docket;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collection;
//import java.util.List;
//
//@ConditionalOnProperty(value = "springfox.documentation.enabled", havingValue = "true", matchIfMissing = true)
//@EnableOpenApi
//@Configuration
//public class Swagger3Configuration implements WebMvcConfigurer {
//
//    @Bean
//    public Docket createRestApi() {
//        return new Docket(DocumentationType.OAS_30)
//                .apiInfo(apiInfo())
//                .select()
//                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
//                .apis(RequestHandlerSelectors.basePackage("com.suyh.controller"))
//                .paths(PathSelectors.any())
//                .build()
//                .ignoredParameterTypes(ApiIgnore.class);
//    }
//
//    // 生成接口信息，包括标题、联系人等
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//                .title("Swagger3接口文档")
//                .description("如有疑问，请联系开发工程师。")
//                .contact(new Contact("suyh", "https://www.suyh.com", "suyh@sina.com.com"))
//                .version("1.0")
//                .build();
//    }
//
//    /**
//     * 通过分组可以在生成的文档是下拉选择，查看哪一个分组
//     */
//    @Bean
//    public Docket createRestApiOther() {
//        return new Docket(DocumentationType.OAS_30)
//                .apiInfo(apiInfo())
//                // 按分组显示的名称
//                .groupName("用户信息分组")
//                .select()
//                // 指定要扫描的基础包
//                .apis(RequestHandlerSelectors.basePackage("com.suyh.controller"))
//                .paths(PathSelectors.any())
//                .build()
//                // 允许请求头中添加Authorization 鉴权信息
//                // 参考：https://www.jianshu.com/p/450416498508
//                .securityContexts(Arrays.asList(securityContexts()))
//                .securitySchemes(Arrays.asList(securitySchemes()))
//                // 忽略某些参数
//                .ignoredParameterTypes(ApiIgnore.class);
//    }
//
//    private SecurityScheme securitySchemes() {
//        return new ApiKey("Authorization", "Authorization", "header");
//    }
//
//    private SecurityContext securityContexts() {
//        return SecurityContext.builder()
//                .securityReferences(defaultAuth())
////                .forPaths(PathSelectors.any())    // 过时了
//                .operationSelector(operationContext -> true)
//                .build();
//    }
//
//    private List<SecurityReference> defaultAuth() {
//        AuthorizationScope authorizationScope = new AuthorizationScope("xxx", "描述信息");
//        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
//        authorizationScopes[0] = authorizationScope;
//        return Arrays.asList(new SecurityReference("Authorization", authorizationScopes));
//    }
//
//    /**
//     * swagger3 与springboot actuator 2.6 以上版本冲突的解决，
//     * 同时添加配置：spring.mvc.pathmatch.matching-strategy=ant_path_matcher
//     * 参考：https://github.com/springfox/springfox/issues/3462
//     * <p>
//     * 另外一个问题的冲突是spring-boot-actuator 与swagger3 版本之间的不兼容
//     */
//    @Bean
//    public WebMvcEndpointHandlerMapping webEndpointServletHandlerMapping(
//            WebEndpointsSupplier webEndpointsSupplier,
//            ServletEndpointsSupplier servletEndpointsSupplier,
//            ControllerEndpointsSupplier controllerEndpointsSupplier,
//            EndpointMediaTypes endpointMediaTypes,
//            CorsEndpointProperties corsProperties,
//            WebEndpointProperties webEndpointProperties,
//            Environment environment) {
//        List<ExposableEndpoint<?>> allEndpoints = new ArrayList<>();
//        Collection<ExposableWebEndpoint> webEndpoints = webEndpointsSupplier.getEndpoints();
//        allEndpoints.addAll(webEndpoints);
//        allEndpoints.addAll(servletEndpointsSupplier.getEndpoints());
//        allEndpoints.addAll(controllerEndpointsSupplier.getEndpoints());
//        String basePath = webEndpointProperties.getBasePath();
//        EndpointMapping endpointMapping = new EndpointMapping(basePath);
//        boolean shouldRegisterLinksMapping = this.shouldRegisterLinksMapping(webEndpointProperties, environment, basePath);
//        return new WebMvcEndpointHandlerMapping(
//                endpointMapping, webEndpoints, endpointMediaTypes, corsProperties.toCorsConfiguration(),
//                new EndpointLinksResolver(allEndpoints, basePath), shouldRegisterLinksMapping, null);
//    }
//
//
//    private boolean shouldRegisterLinksMapping(
//            WebEndpointProperties webEndpointProperties,
//            Environment environment, String basePath) {
//        return webEndpointProperties.getDiscovery().isEnabled() && (StringUtils.hasText(basePath) || ManagementPortType.get(environment).equals(ManagementPortType.DIFFERENT));
//    }
//}
