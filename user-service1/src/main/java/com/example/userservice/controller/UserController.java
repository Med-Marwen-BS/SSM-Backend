package com.example.userservice.controller;

import com.example.userservice.Entity.Notification;
import com.example.userservice.Entity.Param.CsvResult;
import com.example.userservice.Entity.Param.MailBodyParam;
import com.example.userservice.Entity.Param.TeamToUserBody;
import com.example.userservice.Entity.User;
import com.example.userservice.Response.CommonResponse;
import com.example.userservice.Response.ErrorResponse;
import com.example.userservice.Response.MainResponse;
import com.example.userservice.Service.JWTService;
import com.example.userservice.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/user-service")
public class UserController {

    private final UserService userService;
    private final JWTService jwtService;

    @GetMapping("/get/{id}")
    public MainResponse getById(@PathVariable String id){
        try{
            return new CommonResponse<>(userService.getById(id), HttpStatus.OK.toString());
        }
        catch (RuntimeException exception){
            return new ErrorResponse(exception.getMessage());
        }

    }
    @GetMapping("/isTokenExpired")
    public MainResponse isTokenExpired(@RequestHeader("Authorization") String bearerToken){
        try{
            bearerToken=bearerToken.replace("Bearer ","");
            return new CommonResponse<>(jwtService.isTokenExpired(bearerToken), HttpStatus.OK.toString());
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
    @GetMapping("/getByToken")
    public MainResponse getByToken(@RequestHeader("Authorization") String bearerToken){
        try{
            bearerToken=bearerToken.replace("Bearer ","");
            User user = userService.getUserByToken(bearerToken);
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

    @PostMapping("/addTeamToUser")
    public MainResponse addTeamToUser(@RequestBody TeamToUserBody teamToUserBody){
        return new CommonResponse<>(userService.addTeamToUser(teamToUserBody.getTeamId(),teamToUserBody.getEmail()),HttpStatus.OK.toString());
    }

    @GetMapping("/leaveTeam")
    public MainResponse leaveTeam(@RequestHeader("Authorization") String bearerToken){
        try {
            bearerToken=bearerToken.replace("Bearer ","");
            return new CommonResponse<>(userService.leaveTeam(bearerToken),HttpStatus.OK.toString());
        }catch (Exception e){
            return new CommonResponse<>("failed",HttpStatus.BAD_REQUEST.toString());
        }
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
