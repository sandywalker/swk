package com.wenku.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * Created by sindtom on 4/6/16.
 */
@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;


    @Value("${mail.username}")
    private String from;


    public void sendMail(String subject, String content, String to) {
        sendMail(mailSender,from,subject,content,to);
    }

    public void sendMail(JavaMailSender sender,String from, String subject, String content, String to) {
        MimeMessage message = sender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message,false,"utf-8");
            helper.setFrom(from);
            helper.setSubject(subject);
            helper.setText(content,true);
            helper.setTo(to);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        sender.send(message);
    }
}
