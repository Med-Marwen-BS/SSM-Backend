package chiba.dev.userservice.Utility;

import chiba.dev.userservice.Model.Entity.User;

import java.util.List;

public class UserHelper {


    public static Boolean checkIfEmailExist(List<User> userList, String email){
        return userList.stream()
                .anyMatch(user -> user.getEmail().equals(email));
    }

    public static Boolean checkIfUsernameExist(List<User> userList,String username){
        return userList.stream()
                .anyMatch(user -> user.getUsername().equals(username));
    }

    public static User getExistedUser(List<User> userList,String email,String username){
        return userList.stream()
                .filter(user -> user.getEmail().equals(email) && user.getUsername().equals(username))
                .findFirst().get();
    }

    public static Boolean checkIfUserIsSaved(List<User> savedUsers,User user){
        return savedUsers.stream()
                .anyMatch(savedUser -> savedUser.getEmail().equals(user.getEmail()) || savedUser.getUsername().equals(user.getUsername()));
    }

    public static Boolean checkFields(User user){
        return  user.getUsername() != null
                && user.getFirstName() != null
                && user.getLastName() != null
                && user.getEmail() != null
                && user.getSexe() != null
                && user.getRole() != null;
    }

}
