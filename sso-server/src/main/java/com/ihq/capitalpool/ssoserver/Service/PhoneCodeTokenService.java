package com.ihq.capitalpool.ssoserver.Service;

import java.util.Map;

public interface PhoneCodeTokenService {
    Map<String, String> generator(String uuid, String data, String key);
}
