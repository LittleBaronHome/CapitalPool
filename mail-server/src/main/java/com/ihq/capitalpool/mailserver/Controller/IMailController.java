package com.ihq.capitalpool.mailserver.Controller;

import com.ihq.capitalpool.mailserver.Interface.IMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mail")
public class IMailController {
    @Autowired
    IMailService iMailService;

    @GetMapping("/simple")
    void sendSimpleMail(@RequestParam(value = "to") String to, @RequestParam(value = "subject") String subject, @RequestParam(value = "content") String content) {
        iMailService.sendSimpleMail(to, subject, content);
    }
}
