package com.ihq.capitalpool.ssoserver.Service;

import java.util.Map;

public interface SsoService {

    Map<String, String> generatorToken(String username, String password);
}
