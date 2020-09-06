package com.ihq.capitalpool.userserver.ServiceImpl;

import com.ihq.capitalpool.common.RestException;
import com.ihq.capitalpool.userserver.Entity.User;
import com.ihq.capitalpool.userserver.Entity.UserTemp;
import com.ihq.capitalpool.userserver.Mapper.UserMapper;
import com.ihq.capitalpool.userserver.Mapper.UserTempMapper;
import com.ihq.capitalpool.userserver.Service.MailServiceRemote;
import com.ihq.capitalpool.userserver.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserTempMapper userTempMapper;

    @Autowired
    MailServiceRemote mailServiceRemote;

    @Value("${user_active_millisecond}")
    String user_active_millisecond;

    @Override
    public void RegisterUser(UserTemp userTemp) {
        // 业务校验
        // 邮箱不能重复
        User origin_users = userMapper.selectByEmail(userTemp.getEmail());
        if (origin_users != null) {
            throw new RestException("邮箱已存在", "Email already exists");
        }

        // 生成激活码
        String active_code = String.valueOf(new Date().getTime());
        userTemp.setActiveCode(active_code);
        // 添加待激活用户
        userTempMapper.insert(userTemp);
        if (userTemp.getId() == null) {
            throw new RestException("添加用户失败", "Failed to add user");
        }

        // 发送邮件
        String url = "http://192.168.3.35:9004/user/active/" + userTemp.getId() + "/" + active_code;
        String mail_content = "<html>" +
                "<header></header>" +
                "<body>" +
                "<h3>尊敬用户您好：</h3>" +
                "<div><b>感谢您使用Ihq系统，请点击以下链接来激活您的账户：</b></div>" +
                "<div>%s</div>" +
                "</body>" +
                "</html>";
        mailServiceRemote.sendHtmlMail(userTemp.getEmail(), "Ihq用户激活链接", String.format(mail_content, url));
    }

    @Override
    public String ActiveUser(Integer id, String active_code) {
        // 验证id与激活码合法性
        UserTemp userTemp = userTempMapper.selectById(id);
        if (userTemp == null || !(active_code).equals(userTemp.getActiveCode()) ||
                // 有效期
                new Date().getTime() - userTemp.getCreateTime().getTime() >= Long.valueOf(user_active_millisecond)) {
            return "请求已失效，请点击下边链接返回";
        }

        // 激活用户，从临时表进正式表
        try {
            userMapper.insertFromTemp(id);
        } catch (DuplicateKeyException e) {
            return "用户已激活，不能重复激活，请点击下边链接返回";
        }

        return "激活成功，请点击下边链接登录";
    }

    @Override
    public User selectByUsernamePassword(String username, String password) {
        return userMapper.selectByUsernamePassword(username, password);
    }

    @Override
    public User selectByUUID(String uuid) {
        return userMapper.selectByUUID(uuid);
    }

    @Override
    public void updateUserInfo(User user) {

    }

    @Override
    public void CancelUser(Integer id) {

    }

}
