package com.ihq.capitalpool.userserver.Controller;

import com.ihq.capitalpool.userserver.Entity.User;
import com.ihq.capitalpool.userserver.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class RegisterController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public void RegisterUser(@Validated @RequestBody User user) {
        userService.RegisterUser(user);
    }

    @GetMapping("/active/{id}/{active_code}")
    public boolean ActiveUser(@PathVariable Integer id, @PathVariable String active_code) {
        userService.ActiveUser(id, active_code);
        return true;
    }

    @PutMapping("/update")
    public void updateUserInfo() {}

    @PutMapping("/cancel")
    public void CancelUser() {}


}
