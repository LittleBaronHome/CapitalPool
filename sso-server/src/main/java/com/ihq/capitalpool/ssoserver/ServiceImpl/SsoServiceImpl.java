package com.ihq.capitalpool.ssoserver.ServiceImpl;

import com.alibaba.fastjson.JSONObject;
import com.ihq.capitalpool.common.RestException;
import com.ihq.capitalpool.common.RestResult;
import com.ihq.capitalpool.common.ResultStatusEnum;
import com.ihq.capitalpool.ssoserver.Service.PasswordTokenService;
import com.ihq.capitalpool.ssoserver.Service.SsoService;
import com.ihq.capitalpool.ssoserver.Service.UserServiceRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SsoServiceImpl implements SsoService {

    @Autowired
    private UserServiceRemote userServiceRemote;

    @Autowired
    private PasswordTokenService passwordTokenService;

    @Value("${QH.PublicKey}")
    private String qhPublicKey;

    @Override
    public Map<String, String> generatorToken(String username, String password) {
        RestResult restResult = userServiceRemote.selectByUsernamePassword(username, password);
        if (restResult.getStatus() != 200) {
            throw new RestException(restResult);
        }
        if (restResult.getData() == null) {
            throw new RestException(ResultStatusEnum.PasswordError);
        }
        HashMap res = restResult.getHashMapData();
        HashMap<String, Object> map = new HashMap<>();
        map.put("uuid", res.get("uuid"));
        map.put("username", res.get("username"));
        map.put("name", res.get("name"));
        map.put("email", res.get("email"));
        map.put("phone", res.get("phone"));
        map.put("status", res.get("status"));
        return passwordTokenService.generator(String.valueOf(res.get("uuid")), JSONObject.toJSONString(map), qhPublicKey);
    }
}
