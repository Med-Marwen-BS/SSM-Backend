package chiba.dev.userservice.Controller;

import chiba.dev.userservice.Model.Entity.Notification;
import chiba.dev.userservice.Model.Entity.Param.CsvResult;
import chiba.dev.userservice.Model.Entity.Param.MailBodyParam;
import chiba.dev.userservice.Model.Entity.Region;
import chiba.dev.userservice.Model.Entity.User;
import chiba.dev.userservice.Response.CommonResponse;
import chiba.dev.userservice.Response.ErrorResponse;
import chiba.dev.userservice.Response.MainResponse;
import chiba.dev.userservice.Service.UserService;
import com.sun.tools.javac.Main;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/user-service")
public class UserController {

    private final UserService userService;

    @GetMapping("/get/{id}")
    public MainResponse getById(@PathVariable String id){
        try{
            return new CommonResponse<>(userService.getById(id), HttpStatus.OK.toString());
        }
        catch (RuntimeException exception){
            return new ErrorResponse(exception.getMessage());
        }

    }

    @GetMapping("/getAll")
    public MainResponse getAll(){
        return new CommonResponse<>(userService.getAll(),HttpStatus.OK.toString());
    }

    @GetMapping("/getByUsername/{username}")
    public MainResponse getByUsername(@PathVariable String username){
        try{
            User user = userService.getByUsername(username);
            return new CommonResponse<>(user,HttpStatus.OK.toString());
        }
        catch (RuntimeException exception){
            exception.printStackTrace();
            return new ErrorResponse(exception.getMessage());
        }
    }
    @DeleteMapping("/delete/{id}")
    public MainResponse delete(@PathVariable String id){
        return new CommonResponse<>(userService.delete(id),HttpStatus.OK.toString());
    }

    @PutMapping("/update/{id}")
    public MainResponse update(@PathVariable String id,@RequestBody User user){
        return new CommonResponse<>(userService.update(id,user),HttpStatus.OK.toString());
    }

    @PutMapping("/block/{id}")
    public MainResponse blockUser(@PathVariable String id){
        return new CommonResponse<>(userService.blockUser(id),HttpStatus.OK.toString());
    }

    @PutMapping("/unblock/{id}")
    public MainResponse unblockUser(@PathVariable String id){
        return new CommonResponse<>(userService.UnblockUser(id),HttpStatus.OK.toString());
    }

    @PostMapping("/contactSupport")
    public MainResponse sendMailToSupport(@RequestBody MailBodyParam message){
        return userService.sendMailToSupport(message) ?
                new CommonResponse<>(true,HttpStatus.OK.toString())
                        : new CommonResponse<>(false,HttpStatus.BAD_REQUEST.toString());
    }

    @PostMapping("/csv")
    public MainResponse saveFromCsv(@RequestParam("file") MultipartFile file){
        try{
            CsvResult csvResult = userService.saveUsersFromCsv(file);
            return new CommonResponse<>(csvResult,HttpStatus.OK.toString());
        }
        catch (Exception exception){
            exception.printStackTrace();
            return new ErrorResponse(exception.getMessage());
        }
    }

    @PostMapping("/notification")
    public MainResponse updateNotification(@RequestBody Notification notification){
        userService.updateNotification(notification);
        return new CommonResponse<>(true,HttpStatus.OK.toString());
    }

    @PutMapping("/readNotification/{id}")
    public MainResponse readNotification(@PathVariable String id){
        userService.readNotification(id);
        return new CommonResponse<>(true,HttpStatus.OK.toString());
    }


    @GetMapping("/validatePassword/{id}/{password}")
    public MainResponse validatePassword(@PathVariable String id,@PathVariable String password){
        try{
            return new CommonResponse<>(userService.validatePassword(id,password),HttpStatus.OK.toString());
        }
        catch (RuntimeException exception){
            return new ErrorResponse(exception.getMessage());
        }
    }
}
