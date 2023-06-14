package com.example.userservice.Service;


import com.example.userservice.Entity.Param.MailParam;
import com.example.userservice.Entity.User;
import com.example.userservice.Exception.UserException;
import com.example.userservice.Repo.UserRepo;
import com.example.userservice.Request.RegisterRequest;
import com.example.userservice.Request.SignInRequest;
import com.example.userservice.Response.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {


    private final MailService mailService;
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    private final JWTService jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest registerRequest){
        boolean checkEmail = userRepo.existsByEmail(registerRequest.getEmail());
        boolean checkUsername = userRepo.existsByUsername(registerRequest.getUsername());

        if(checkEmail){
            throw new RuntimeException("email  already exist");
        } else if (checkUsername) {
            throw new RuntimeException("username already exist");
        }
        var user = User.builder()
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .email(registerRequest.getEmail())
                .username(registerRequest.getUsername())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(registerRequest.getRole())
                .sexe(registerRequest.getSexe())
                .build();
        userRepo.save(user);
        var token = jwtService.generateToken(user);
        MailParam mailParam = MailParam.builder()
                .fullName(user.getLastName()+" "+user.getFirstName())
                .email(user.getEmail())
                .username(user.getUsername())
                .sexe(user.getSexe().name())
                .type("Inscription")
                .build();
        mailService.sendEmail(mailParam);
        return AuthResponse.builder()
                .token(token)
                .build();
    }

    public AuthResponse signIn(SignInRequest signInRequest){
        var user = userRepo.findByUsername(signInRequest.getUsername())
                .orElseThrow(()-> new UsernameNotFoundException("User was not found in the database"));
        if(!user.getEnabled()){
            throw UserException.USER_NOT_ENABLED;
        }
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        signInRequest.getUsername(),
                        signInRequest.getPassword()
                )
        );
        var token = jwtService.generateToken(user);
        return AuthResponse.builder()
                .token(token)
                .build();
    }

    public Boolean resetPass(String username){
        try{
            User user = userRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User is not found on the database"));
            String randomPass = UUID.randomUUID().toString();
            user.setPassword(passwordEncoder.encode(randomPass));
//            String sexe = user.getSexe().name().isEmpty()&& user.getSexe().name().isBlank()?"HOMME":user.getSexe().name();
            userRepo.save(user);
            MailParam mailParam = MailParam.builder()
                    .fullName(user.getLastName()+" "+user.getFirstName())
                    .username(user.getUsername())
                    .email(user.getEmail())
                    .sexe("homme")
                    .resetPass(randomPass)
                    .type("Forgot_Password")
                    .build();
            mailService.sendEmail(mailParam);
            return true;
        }
        catch (Exception exception){
            exception.printStackTrace();
            System.out.println(exception.getMessage());
            throw exception;
        }
    }
}
