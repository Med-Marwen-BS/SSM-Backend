package dev.mailApp.MailApp.service;



import dev.mailApp.MailApp.Enum.MailPriority;
import dev.mailApp.MailApp.model.MailBodyImpl;
import dev.mailApp.MailApp.model.Param.MailParam;
import dev.mailApp.MailApp.service.engine.MailEngine;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@PropertySource("classpath:/MailText/MailText.properties")
public class MailService {

    private final MailEngine mailNotificationEngine;

    private final Environment environment;

    public boolean sendEmail(MailParam param) {
        List<String> recivers = Collections.singletonList(param.getEmail());

        Map<String, Object> templateModel = new HashMap<>();
        templateModel.put("recipientName", param.getFullName());
        templateModel.put("username", param.getUsername());
        templateModel.put("senderName", "SSM Support");
        templateModel.put("sexe", param.getSexe().toLowerCase());
        templateModel.put("message",environment.getProperty(param.getType()));
        if(param.getResetPass() != null){
            templateModel.put("password",param.getResetPass());
        }
        MailBodyImpl mailNotification = new MailBodyImpl("SSM_Support", recivers, MailPriority.HIGH, true, param.getType().replace("_"," "), "template-thymeleaf", templateModel);

        try {
            mailNotificationEngine.processNotification(mailNotification);
            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
            System.out.println(exception.getMessage());
            return false;
        }

    }

    public Boolean sendMailToSupport(MailParam param){
        List<String> recivers = Collections.singletonList(param.getEmail());

        Map<String, Object> templateModel = new HashMap<>();
        templateModel.put("recipientName", param.getFullName());
        templateModel.put("username", param.getUsername());
        templateModel.put("senderName", param.getSender().getFullName());
        templateModel.put("sexe", param.getSexe().toLowerCase());
        templateModel.put("message",param.getMessage());
        MailBodyImpl mailNotification = new MailBodyImpl(param.getUsername(), recivers, MailPriority.HIGH, true, param.getType().replace("_"," "), "template-support", templateModel);

        try {
            mailNotificationEngine.processNotification(mailNotification);
            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
            System.out.println(exception.getMessage());
            return false;
        }
    }
}
