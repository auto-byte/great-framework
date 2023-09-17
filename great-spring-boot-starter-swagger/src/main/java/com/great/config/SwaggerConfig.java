package com.great.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.schema.ScalarType;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ParameterType;
import springfox.documentation.service.RequestParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * Created on 2021/4/27 11:45 上午
 *
 * @author Y.X
 */
@Configuration
@ConditionalOnProperty(value = "springfox.documentation.enabled", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(SwaggerDocketProperties.class)
public class SwaggerConfig {

    @Resource
    private SwaggerDocketProperties swaggerDocketProperties;

    @Bean
    @ConditionalOnMissingBean
    public Docket createRestApi(ApiInfo apiInfo) {
        Predicate<RequestHandler> predicate = RequestHandlerSelectors
                .withMethodAnnotation(ApiOperation.class);
        if (StringUtils.hasText(swaggerDocketProperties.getBasePackage())) {
            predicate = predicate
                    .and(RequestHandlerSelectors
                            .basePackage(swaggerDocketProperties.getBasePackage()));
        }
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo)
                .select()
                .apis(predicate)
                .paths(PathSelectors.regex(swaggerDocketProperties.getPathRegex()))
                .build()
                .globalRequestParameters(globalRequestParameters());
    }

    @Bean
    @ConditionalOnMissingBean
    public ApiInfo apiInfo(Contact contact) {
        return new ApiInfoBuilder()
                .title(swaggerDocketProperties.getTitle())
                .description(swaggerDocketProperties.getDescription())
                .contact(contact)
                .version("1.0")
                .build();
    }

    @Bean
    @ConditionalOnMissingBean
    public Contact contact() {
        return new Contact(swaggerDocketProperties.getName(),
                swaggerDocketProperties.getUrl(),
                swaggerDocketProperties.getEmail());
    }

    private List<RequestParameter> globalRequestParameters() {
        List<RequestParameter> parameters = new ArrayList<>();
        if (Objects.equals(Boolean.TRUE, swaggerDocketProperties.getGlobalToken())) {
            parameters.add(new RequestParameterBuilder()
                    .name(swaggerDocketProperties.getGlobalTokenHeader())
                    .description("请求Token")
                    .required(false)
                    .in(ParameterType.HEADER)
                    .query(q -> q.defaultValue("").model(m -> m.scalarModel(ScalarType.STRING)))
                    .build());
        }
        return parameters;
    }
}
