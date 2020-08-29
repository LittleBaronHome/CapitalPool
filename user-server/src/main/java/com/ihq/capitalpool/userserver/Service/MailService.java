package com.ihq.capitalpool.userserver.Service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "CapitalPoolMailServer")
public interface MailService {

    @RequestMapping(value = "/mail/simple", method = RequestMethod.GET)
    void sendSimpleMail(@RequestParam(value = "to") String to, @RequestParam(value = "subject") String subject, @RequestParam(value = "content") String content);
}
