package com.zyk.projectadminapi.admin.mail;

public interface MailService {
    void sendSimpleMail(String to, String subject, String content);
}
