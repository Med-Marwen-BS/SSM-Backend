package chiba.dev.userservice.Controller;


import chiba.dev.userservice.Request.RegisterRequest;
import chiba.dev.userservice.Request.SignInRequest;
import chiba.dev.userservice.Response.AuthResponse;
import chiba.dev.userservice.Response.CommonResponse;
import chiba.dev.userservice.Response.ErrorResponse;
import chiba.dev.userservice.Response.MainResponse;
import chiba.dev.userservice.Service.AuthService;
import chiba.dev.userservice.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user-service/auth")
public class AuthController {

    private final AuthService authService;

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest registerRequest){
        return ResponseEntity.ok(authService.register(registerRequest));
    }

    @PostMapping
    public ResponseEntity<RegisterRequest> register1(@RequestBody RegisterRequest registerRequest){
        return ResponseEntity.ok(registerRequest);
    }

    @PostMapping("/signIn")
    public ResponseEntity<MainResponse> signIn(@RequestBody SignInRequest signInRequest){
        try{
            return ResponseEntity.ok(authService.signIn(signInRequest));
        }
        catch (RuntimeException exception){
            exception.printStackTrace();
            return ResponseEntity.badRequest().body(new ErrorResponse(exception.getMessage()));
        }
    }

    @PutMapping("/reset/{username}")
    public ResponseEntity<CommonResponse<Boolean>> resetPass(@PathVariable("username") String username){
        try{
            return ResponseEntity.ok(new CommonResponse<>(authService.resetPass(username), HttpStatus.OK.toString()));
        }
        catch (Exception exception){
            return ResponseEntity.badRequest().body(new CommonResponse<>(authService.resetPass(username),HttpStatus.BAD_GATEWAY.toString()));
        }
    }

    @GetMapping("/checkEmail/{email}")
    public MainResponse checkIfEmailExist(@PathVariable String email){
        return new CommonResponse<>(userService.checkIfExistsByEmail(email),HttpStatus.OK.toString());
    }

    @GetMapping("/checkUsername/{username}")
    public MainResponse checkIfUsernameExist(@PathVariable String username){
        return new CommonResponse<>(userService.checkIfExistsByUsername(username),HttpStatus.OK.toString());
    }

}
