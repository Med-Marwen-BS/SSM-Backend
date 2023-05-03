package dev.mailApp.MailApp.model;



import dev.mailApp.MailApp.Enum.MailPriority;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
public class MailBodyImpl extends MailBody {

    private static final long serialVersionUID = 1L;

    private List<String> cc;
    private List<String> bcc;
    private String subject;

    private boolean isHTML = false;

    public MailBodyImpl(
            String sender,
            List<String> receiver,
            String body,
            Date timeToSend,
            MailPriority priority,
            boolean sendNow,
            String template,
            Map<String, Object> templateModel,
            List<String> cc,
            List<String> bcc,
            String subject) {
        super(sender, receiver, body, timeToSend, priority, sendNow, template, templateModel);
        this.cc = cc;
        this.bcc = bcc;
        this.subject = subject;
    }

    public MailBodyImpl(
            String sender,
            List<String> receiver,
            MailPriority priority,
            boolean sendNow,
            String subject,
            String template,
            Map<String,Object> templateModel) {
        super(sender, receiver, null, null, priority, sendNow, template, templateModel);
        this.subject = subject;
    }
}

