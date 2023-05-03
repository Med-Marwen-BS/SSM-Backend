package dev.mailApp.MailApp.model;



import dev.mailApp.MailApp.Enum.MailPriority;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class MailBody implements Serializable {

    private static final long serialVersionUID = 1L;

    private String sender;
    private List<String> receiver;
    private String body;

    private Date timeToSend;
    private MailPriority priority;
    private boolean sendNow = false;

    private String template;
    private Map<String, Object> templateModel = new HashMap<>();
}
