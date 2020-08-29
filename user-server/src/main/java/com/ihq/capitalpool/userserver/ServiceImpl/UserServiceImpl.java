package com.ihq.capitalpool.userserver.ServiceImpl;

import com.ihq.capitalpool.userserver.Entity.User;
import com.ihq.capitalpool.userserver.Mapper.UserMapper;
import com.ihq.capitalpool.userserver.Service.MailService;
import com.ihq.capitalpool.userserver.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    MailService mailService;

    @Override
    public void RegisterUser(User user) {
        userMapper.addUser(user);
        mailService.sendSimpleMail(user.getEmail(), "Ihq用户激活链接", "http://192.168.3.35:9004/user/active/" + user.getId() + "/125588745165416");
    }

    @Override
    public void ActiveUser(Integer id, String active_code) {
        userMapper.updateUserStatus(id);
    }

    @Override
    public void updateUserInfo(User user) {

    }

    @Override
    public void CancelUser(Integer id) {

    }
}
