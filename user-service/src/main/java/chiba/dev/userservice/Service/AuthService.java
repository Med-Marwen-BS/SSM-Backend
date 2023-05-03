package chiba.dev.userservice.Service;


import chiba.dev.userservice.Enum.Role;
import chiba.dev.userservice.Exception.UserException;
import chiba.dev.userservice.Model.Entity.User;
import chiba.dev.userservice.Repo.UserRepo;
import chiba.dev.userservice.Model.Entity.Param.MailParam;
import chiba.dev.userservice.Request.RegisterRequest;
import chiba.dev.userservice.Request.SignInRequest;
import chiba.dev.userservice.Response.AuthResponse;
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
            userRepo.save(user);
            MailParam mailParam = MailParam.builder()
                    .fullName(user.getLastName()+" "+user.getFirstName())
                    .username(user.getUsername())
                    .email(user.getEmail())
                    .sexe(user.getSexe().name())
                    .resetPass(randomPass)
                    .type("Mot_De_Passe_Oublié")
                    .build();
            mailService.sendEmail(mailParam);
            return true;
        }
        catch (Exception exception){
            exception.printStackTrace();
            System.out.println(exception.getMessage());
            return false;
        }
    }
}
