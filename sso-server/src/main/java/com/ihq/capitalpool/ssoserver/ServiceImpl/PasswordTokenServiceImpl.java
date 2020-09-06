package com.ihq.capitalpool.ssoserver.ServiceImpl;

import com.ihq.capitalpool.ssoserver.Enum.TokenType;
import com.ihq.capitalpool.ssoserver.Service.PasswordTokenService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PasswordTokenServiceImpl extends BasicTokenServiceImpl implements PasswordTokenService {

    @Override
    public Map<String, String> generator(String uuid, String data, String key) {
        return super.generator(uuid, data, key, String.valueOf(TokenType.Password.getCode()));
    }
}
