package com.great.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created on 2021/8/30 19:18
 *
 * @author Y.X
 */
@ConfigurationProperties("springfox.documentation")
public class SwaggerDocketProperties {

    private String basePackage;

    private String pathRegex = ".*";

    private String pathMapping;

    private String title = "接口文档";

    private String description = "接口文档";

    private String name = "独立日";

    private String url = "https://www.great.com";

    private String email = "tech@great.com";

    private Boolean globalToken = true;

    private String globalTokenHeader = "great-Token";

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public String getPathRegex() {
        return pathRegex;
    }

    public void setPathRegex(String pathRegex) {
        this.pathRegex = pathRegex;
    }

    public String getPathMapping() {
        return pathMapping;
    }

    public void setPathMapping(String pathMapping) {
        this.pathMapping = pathMapping;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getGlobalToken() {
        return globalToken;
    }

    public void setGlobalToken(Boolean globalToken) {
        this.globalToken = globalToken;
    }

    public String getGlobalTokenHeader() {
        return globalTokenHeader;
    }

    public void setGlobalTokenHeader(String globalTokenHeader) {
        this.globalTokenHeader = globalTokenHeader;
    }
}