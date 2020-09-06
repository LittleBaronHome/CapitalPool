package com.ihq.capitalpool.mailserver.Controller;

import com.ihq.capitalpool.mailserver.Service.IMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mail")
public class IMailController {
    @Autowired
    IMailService iMailService;

    @GetMapping("/simple")
    void sendSimpleMail(@RequestParam String to,
                        @RequestParam String subject,
                        @RequestParam String content) {
        iMailService.sendSimpleMail(to, subject, content);
    }

    @GetMapping("/html")
    void sendHtmlMail(@RequestParam String to,
                      @RequestParam String subject,
                      @RequestParam String content) {
        iMailService.sendHtmlMail(to, subject, content);
    }

    @GetMapping("/attachment")
    void sendAttachmentsMail(@RequestParam String to,
                             @RequestParam String subject,
                             @RequestParam String content,
                             @RequestParam String filePath) {
        iMailService.sendAttachmentsMail(to, subject, content, filePath);
    }
}
