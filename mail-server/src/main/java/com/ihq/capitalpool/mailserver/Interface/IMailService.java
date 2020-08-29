package com.ihq.capitalpool.mailserver.Interface;

import org.springframework.stereotype.Component;

public interface IMailService {

    public void sendSimpleMail(String to, String subject, String content);

    public void sendHtmlMail(String to, String subject, String content);

    public void sendAttachmentsMail(String to, String subject, String content, String filePath);
}
