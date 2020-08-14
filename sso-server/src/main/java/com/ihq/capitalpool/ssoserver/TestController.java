package com.ihq.capitalpool.ssoserver;

import com.ihq.capitalpool.ssoserver.config.Env;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    private Env env;

    @GetMapping("/getuser")
    public String getUser() {
        return env.getName() + env.getLabel();
    }
}
