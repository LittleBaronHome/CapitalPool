package com.ihq.capitalpool.userserver.Service;

import com.ihq.capitalpool.userserver.Entity.User;
import com.ihq.capitalpool.userserver.Entity.UserTemp;

public interface UserService {

    void RegisterUser(UserTemp userTemp);

    String ActiveUser(Integer id, String active_code);

    User selectByUsernamePassword(String username, String password);

    User selectByUUID(String uuid);

    void updateUserInfo(User user);

    void CancelUser(Integer id);
}
