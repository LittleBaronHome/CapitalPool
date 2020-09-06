package com.ihq.capitalpool.userserver.Entity;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Date;

public class UserTemp {
    private Integer id;
    private String username;
    @NotBlank(message = "昵称不能为空")
    private String name;
    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;
    private String phone;
    @NotBlank(message = "密码不能为空")
    private String password;
    private String activeCode;
    private Date createTime;

    public UserTemp() {
    }

    public UserTemp(Integer id, String username, @NotBlank(message = "昵称不能为空") String name, @NotBlank(message = "邮箱不能为空") @Email(message = "邮箱格式不正确") String email, String phone, @NotBlank(message = "密码不能为空") String password, String activeCode, Date createTime) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.activeCode = activeCode;
        this.createTime = createTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getActiveCode() {
        return activeCode;
    }

    public void setActiveCode(String activeCode) {
        this.activeCode = activeCode;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
