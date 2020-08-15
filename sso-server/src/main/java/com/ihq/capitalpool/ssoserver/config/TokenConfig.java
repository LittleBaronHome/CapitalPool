package com.ihq.capitalpool.ssoserver.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "token")
@RefreshScope
public class TokenConfig {
    private String Signature;

    public class ExpiredTime {
        private Integer common;
        private Integer refresh;

        public Integer getCommon() {
            return common;
        }

        public void setCommon(Integer common) {
            this.common = common;
        }

        public Integer getRefresh() {
            return refresh;
        }

        public void setRefresh(Integer refresh) {
            this.refresh = refresh;
        }
    }

    public String getSignature() {
        return Signature;
    }

    public void setSignature(String signature) {
        Signature = signature;
    }
}
