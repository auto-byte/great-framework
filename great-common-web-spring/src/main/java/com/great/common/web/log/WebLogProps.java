package com.great.common.web.log;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created on 2021/9/8 14:45
 *
 * @author Y.X
 */
@ConfigurationProperties(prefix = "global.web")
public class WebLogProps {

    private Log log = new Log();

    public Log getLog() {
        return log;
    }

    public void setLog(Log log) {
        this.log = log;
    }

    public static class Log {

        private Boolean enable = true;
        /**
         * 日志信息最大长度，默认200
         */
        private Integer maxLength = 400;

        private Boolean showIp = false;

        public Boolean getEnable() {
            return enable;
        }

        public void setEnable(Boolean enable) {
            this.enable = enable;
        }

        public Integer getMaxLength() {
            return maxLength;
        }

        public void setMaxLength(Integer maxLength) {
            this.maxLength = maxLength;
        }

        public Boolean getShowIp() {
            return showIp;
        }

        public void setShowIp(Boolean showIp) {
            this.showIp = showIp;
        }
    }
}
