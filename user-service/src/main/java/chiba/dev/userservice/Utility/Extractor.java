package chiba.dev.userservice.Utility;

import chiba.dev.userservice.Model.Entity.User;

import java.util.List;
import java.util.stream.Collectors;

public class Extractor {


    public static List<String> getEmailList(List<User> userList){
        return userList.stream()
                .map(User::getEmail)
                .collect(Collectors.toList());
    }

    public static List<String> getUsernameList(List<User> userList){
        return userList.stream()
                .map(User::getUsername)
                .collect(Collectors.toList());
    }
}
