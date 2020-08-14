package com.ihq.capitalpool.ssoserver.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "user")
public class Env {
    class Label {
        private String id;
        private String text;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
    private String name;
    private Label label;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }
}
