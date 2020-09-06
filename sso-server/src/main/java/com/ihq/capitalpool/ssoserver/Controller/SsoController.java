package com.ihq.capitalpool.ssoserver.Controller;

import com.alibaba.fastjson.JSONObject;
import com.ihq.capitalpool.common.RestException;
import com.ihq.capitalpool.common.RestResult;
import com.ihq.capitalpool.common.ResultStatusEnum;
import com.ihq.capitalpool.ssoserver.Service.SsoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sso")
public class SsoController {

    @Autowired
    private SsoService ssoService;

    @PostMapping("/login")
    public RestResult login(@RequestBody JSONObject parameter) {
        String username = parameter.getString("username");
        String password = parameter.getString("password");
        if (username == null || password == null) {
            throw new RestException(ResultStatusEnum.ParameterError, "用户名密码不能为空");
        }
        return new RestResult(ssoService.generatorToken(username, password));
    }
}
