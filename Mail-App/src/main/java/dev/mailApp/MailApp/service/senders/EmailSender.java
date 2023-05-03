package dev.mailApp.MailApp.service.senders;



import dev.mailApp.MailApp.model.MailBodyImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;


@Service
public class EmailSender{

    @Autowired
    private JavaMailSender emailSender;



    public void send(MailBodyImpl notification) throws Exception {


        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setFrom(notification.getSender());
        String[] to = new String[notification.getReceiver().size()];
        for(int i = 0 ; i < notification.getReceiver().size() ; i++)
            to[i] = notification.getReceiver().get(i);
        helper.setTo(to);
        helper.setSubject(notification.getSubject());
        helper.setText(notification.getBody(), notification.isHTML());
        emailSender.send(message);


    }

}
