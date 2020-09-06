package com.ihq.capitalpool.userserver.Controller;

import com.ihq.capitalpool.common.RestResult;
import com.ihq.capitalpool.userserver.Entity.User;
import com.ihq.capitalpool.userserver.Entity.UserTemp;
import com.ihq.capitalpool.userserver.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
@CrossOrigin
public class RegisterController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    @ResponseBody
    public RestResult RegisterUser(@Validated @RequestBody UserTemp userTemp) {
        userService.RegisterUser(userTemp);
        return new RestResult();
    }

    @GetMapping("/active/{id}/{active_code}")
    public String ActiveUser(@PathVariable Integer id, @PathVariable String active_code, Model model) {
         model.addAttribute("msg", userService.ActiveUser(id, active_code));
         return "jumpToLogin";
    }

    @GetMapping("/detail/upd")
    @ResponseBody
    public RestResult selectByUsernamePassword(@RequestParam String username, @RequestParam String password) {
        return new RestResult(userService.selectByUsernamePassword(username, password));
    }

    @GetMapping("/detail/uuid")
    @ResponseBody
    public RestResult selectByUsernamePassword(@RequestParam String uuid) {
        return new RestResult(userService.selectByUUID(uuid));
    }

    @PutMapping("/update")
    @ResponseBody
    public RestResult updateUserInfo() {
        return new RestResult();
    }

    @PutMapping("/cancel")
    @ResponseBody
    public RestResult CancelUser() {
        return new RestResult();
    }


}
