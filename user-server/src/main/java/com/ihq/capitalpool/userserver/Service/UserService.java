package com.ihq.capitalpool.userserver.Service;

import com.ihq.capitalpool.userserver.Entity.User;

public interface UserService {

    void RegisterUser(User user);

    void ActiveUser(Integer id, String active_code);

    void updateUserInfo(User user);

    void CancelUser(Integer id);
}
