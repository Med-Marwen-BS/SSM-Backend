package dev.mailApp.MailApp.Controller;

import dev.mailApp.MailApp.model.Param.MailParam;
import dev.mailApp.MailApp.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mail-service")
@RequiredArgsConstructor
public class MailController {

    private final MailService mailService;

    @PostMapping("/send")
    public Boolean sendMail(@RequestBody MailParam mail){
        return mail.getType().equalsIgnoreCase("support") ?
                mailService.sendMailToSupport(mail)
                : mailService.sendEmail(mail);
    }

    @PostMapping("/support")
    public Boolean sendMailToSupport(@RequestBody MailParam mailParam){
        return mailService.sendMailToSupport(mailParam);
    }
}
