package com.morrow.modules.config;

import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.*;

/**
 * Swagger配置
 *
 * @Author Tomorrow
 * @Date 2020/9/5 11:56 下午
 */
@Configuration
@EnableOpenApi
public class SwaggerConfig {

    private ApiInfo apiInfo() {

        // 作者信息
        Contact contact = new Contact("Morrow", "https://www.morrow.com/", "morrow@163.com");

        return new ApiInfo(
                "Morrow的接口文档",
                "项目描述",
                "1.0",
                "https://www.morrow.com",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());
    }


    @Bean
    public Docket docket(Environment environment) {

        // 设置显示的Swagger环境
        Profiles dev = Profiles.of("dev");
        // 获取项目环境
        boolean flag = environment.acceptsProfiles(dev);

        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                // 配置Api文档分组
                .groupName("Morrow")
                // enable()是否启用Swagger，默认是true
                .enable(flag)
                .select()
                // RequestHandlerSelectors,配置要扫描接口的方式
                // basePackage指定要扫描的包
                // any()扫描所有，项目中的所有接口都会被扫描到
                // none()不扫描
                // withClassAnnotation()扫描类上的注解
                // withMethodAnnotation()扫描方法上的注解
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                //包下的类，生成接口文档
                //.apis(RequestHandlerSelectors.basePackage("com.morrow.modules.sys.controller"))
                .paths(PathSelectors.any())
                .build()

                // 支持的通讯协议集合
                .protocols(newHashSet("https", "http"))

                // 授权信息设置，必要的header token等认证信息
                .securitySchemes(securitySchemes())

                // 授权信息全局应用
                .securityContexts(securityContexts());


    }


    /**
     * 设置授权信息
     */
    private List<SecurityScheme> securitySchemes() {
        ApiKey apiKey = new ApiKey("token", "token", In.HEADER.toValue());
        return Collections.singletonList(apiKey);
    }

    /**
     * 授权信息全局应用
     */
    private List<SecurityContext> securityContexts() {
        return Collections.singletonList(
                SecurityContext.builder()
                        .securityReferences(Collections.singletonList(new SecurityReference("token", new AuthorizationScope[]{new AuthorizationScope("global", "")})))
                        .build()
        );
    }

    @SafeVarargs
    private final <T> Set<T> newHashSet(T... ts) {
        if (ts.length > 0) {
            return new LinkedHashSet<>(Arrays.asList(ts));
        }
        return null;
    }


}
