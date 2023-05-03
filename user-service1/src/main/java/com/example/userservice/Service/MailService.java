package com.example.userservice.Service;


import com.example.userservice.Entity.Param.MailParam;
import com.example.userservice.Entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class MailService {

    private final RestTemplate restTemplate;
    private String mailServiceUrl = "http://localhost:3008/mail-service/send";


    public void sendEmail(MailParam mailParam) {
        try{
            this.restTemplate.postForObject(mailServiceUrl, mailParam, Boolean.class);
        }
        catch (Exception exception){
            exception.printStackTrace();
            System.out.println(exception.getMessage());
        }
    }

    public void sendCsvMail(User user){
        MailParam mailParam = MailParam.builder()
                .fullName(user.getLastName()+" "+user.getFirstName())
                .email(user.getEmail())
                .username(user.getUsername())
                .sexe(user.getSexe().name())
                .resetPass(user.getPassword())
                .type("Inscription")
                .build();
        this.sendEmail(mailParam);
    }
}
