package com.ihq.capitalpool.userserver.Entity;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class User {
    private Integer id;
    private String uuid;
    private String username;
    @NotBlank(message = "昵称不能为空")
    private String name;
    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;
    private String phone;
    @NotBlank(message = "密码不能为空")
    private String password;
    private Integer status;

    public User(Integer id, String uuid, String username, String name, String email, String phone, String password, Integer status) {
        this.id = id;
        this.uuid = uuid;
        this.username = username;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.status = status;
    }

    public User() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
