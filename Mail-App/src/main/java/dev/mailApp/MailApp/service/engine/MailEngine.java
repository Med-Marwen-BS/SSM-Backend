package dev.mailApp.MailApp.service.engine;



import dev.mailApp.MailApp.exception.MailException;
import dev.mailApp.MailApp.model.MailBodyImpl;
import dev.mailApp.MailApp.service.senders.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

@Service
public class MailEngine {

    @Autowired
    private SpringTemplateEngine springTemplateEngine;

    @Autowired
    private EmailSender emailSender;

    public void validate(MailBodyImpl notification) throws MailException {
        if (notification.getSubject() == null) throw new MailException("Subject can't be null");
        if (CollectionUtils.isEmpty(notification.getReceiver()))
            throw new MailException("Receiver can't be empty");
    }


    public void send(MailBodyImpl notification) {

        try {
            emailSender.send(notification);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    public void decorate(MailBodyImpl notification) {
        if (notification.getTemplate() == null) return;

        Context thymeleafContext = new Context();
        thymeleafContext.setVariables(notification.getTemplateModel());

        notification.setBody(
                springTemplateEngine.process(notification.getTemplate(), thymeleafContext));
        notification.setHTML(true);
    }

    public void processNotification(MailBodyImpl notification) throws Exception {

        decorate(notification);
        validate(notification);
        send(notification);

    }
}
