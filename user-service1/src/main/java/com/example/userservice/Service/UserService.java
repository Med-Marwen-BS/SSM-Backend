package com.example.userservice.Service;

import com.example.userservice.Entity.Team;
import com.example.userservice.Enum.CsvStatus;
import com.example.userservice.Entity.Notification;
import com.example.userservice.Entity.Param.CsvConversionResult;
import com.example.userservice.Entity.Param.CsvResult;
import com.example.userservice.Entity.Param.MailBodyParam;
import com.example.userservice.Entity.Param.MailParam;
import com.example.userservice.Entity.User;
import com.example.userservice.Repo.TeamRepository;
import com.example.userservice.Repo.UserRepo;
import com.example.userservice.Utility.CsvHelper;
import com.example.userservice.Utility.Extractor;
import com.example.userservice.Utility.UserHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;
    private final TeamRepository teamRepository;

    private final PasswordEncoder passwordEncoder;

    private final MailService mailService;
    private final JWTService jwtService;

    private final MongoTemplate mongoTemplate;


    public User getById(String id){
        return userRepo.findById(id).orElseThrow(()-> new UsernameNotFoundException("User was not found in the database"));
    }

    public List<User> getAll(){
        return userRepo.findAll();
    }

    public String delete(String id){
        userRepo.deleteById(id);
        return "User Deleted";
    }

    public String addTeamToUser(String teamId  ,String emailUser){
        try {
            Optional<Team> optionalTeam = teamRepository.findById(teamId);
            Optional<User> optionalUser = userRepo.findByEmail(emailUser);

            if(optionalTeam.isEmpty() || optionalUser.isEmpty())
                throw new RuntimeException("team or user not found");

            User user=optionalUser.get();
            if(user.getTeam()!=null) throw new RuntimeException("user have team");
            user.setTeam(optionalTeam.get());
            userRepo.save(user);
            return "success";
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }


    }

    public User update(String id,User user){

        User original = userRepo.findById(id).orElseThrow(()-> new UsernameNotFoundException("User was not found in the database"));
        original.setFirstName(user.getFirstName() != null ? user.getFirstName() : original.getFirstName());
        original.setLastName(user.getLastName() != null ? user.getLastName() : original.getLastName());
        original.setRole(user.getRole() != null ? user.getRole() : original.getRole());
        original.setPassword(user.getPassword() != null ? passwordEncoder.encode(user.getPassword()) : original.getPassword());
        original.setEmail(user.getEmail() != null ? user.getEmail() : original.getEmail());
        original.setUsername(user.getUsername() != null ? user.getUsername() : original.getUsername());
        original.setSexe(user.getSexe() != null ? user.getSexe() : original.getSexe());
        return userRepo.save(original);
    }

    public void updateNotification(Notification notification){
        Query query = new Query();
        Update update = new Update();
        update.set("notification",notification);
        mongoTemplate.updateMulti(query,update,User.class);
    }

    public void readNotification(String id){
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        Update update = new Update();
        update.set("notification.read",true);
        mongoTemplate.updateFirst(query,update, User.class);
    }


    public String blockUser(String id){
        User original = userRepo.findById(id).orElseThrow(()-> new UsernameNotFoundException("User was not found in the database"));
        original.setEnabled(false);
        userRepo.save(original);
        return "User has been blocked";
    }

    public String UnblockUser(String id){
        User original = userRepo.findById(id).orElseThrow(()-> new UsernameNotFoundException("User was not found in the database"));
        original.setEnabled(true);
        userRepo.save(original);
        return "User has been unblocked";
    }

    public Boolean checkIfExistsByEmail(String email){
        return userRepo.existsByEmail(email);
    }

    public Boolean checkIfExistsByUsername(String username){
        return userRepo.existsByUsername(username);
    }

    public User getUserByToken(String token){
        String username = jwtService.extractUsername(token);
        return this.getByUsername(username);
    }
    public String leaveTeam(String token){
        try {
            this.getUserByToken(token);


            User user=this.getUserByToken(token);
            if(user.getTeam().getCreatorId().equals(user.getId()))
                throw new RuntimeException("creator cannot leave the team");
            user.setTeam(null);
            userRepo.save(user);
            return "success";
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }


    }

    public User getByUsername(String username){
        return userRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User does not exist on the database"));
    }

    public Boolean validatePassword(String id,String password){
        User user = userRepo.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found in the database"));
        return passwordEncoder.matches(password,user.getPassword());
    }



    public Boolean sendMailToSupport(MailBodyParam mailBodyParam){
        User user = userRepo.findByUsername(mailBodyParam.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User was not found in the database"));

        MailParam mailParam = MailParam.builder()
                .type("SUPPORT")
                .fullName("S.O.S Support Team")
                .email("sproject.technical@gmail.com")
                .message(mailBodyParam.getMessage())
                .username(user.getUsername())
                .sexe(user.getSexe().name())
                .sender(user)
                .build();
        try{
            mailService.sendEmail(mailParam);
            return true;
        }
        catch (Exception exception){
            exception.printStackTrace();
            return false;
        }
    }

    public CsvResult saveUsersFromCsv(MultipartFile file) throws IOException {
        try{
            return bulkSaveUsersFromCsv(CsvHelper.csvToUsersList(file.getInputStream(),','));
        }
        catch (Exception exception){
            return bulkSaveUsersFromCsv(CsvHelper.csvToUsersList(file.getInputStream(),';'));
        }
    }

    public CsvResult bulkSaveUsersFromCsv(CsvConversionResult csvConversionResult){
        List<User> userList = csvConversionResult.getUserList();
        CsvResult csvResult = csvConversionResult.getCsvResult();
        Map<User,Integer> indexedUsers = csvConversionResult.getIndexedUsers();

        List<String> emailList = Extractor.getEmailList(userList);
        List<String> usernameList = Extractor.getUsernameList(userList);
        List<User> existUsers = userRepo.findByEmailIn(emailList);
        existUsers.addAll(userRepo.findByUsernameIn(usernameList));
        List<User> usersToSave = new ArrayList<>();
        for(User user : userList){
            String tempEmail = user.getEmail();
            String tempUsername = user.getUsername();
            if(!UserHelper.checkIfEmailExist(existUsers,tempEmail)
                    && !UserHelper.checkIfUsernameExist(existUsers,tempUsername)
                    && !UserHelper.checkIfUserIsSaved(usersToSave,user) ){
                mailService.sendCsvMail(user);
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                usersToSave.add(user);
                csvResult.updateResult(CsvStatus.CREATED,indexedUsers.get(user));
            }
            else if(UserHelper.checkIfEmailExist(existUsers,tempEmail)
                    && UserHelper.checkIfUsernameExist(existUsers,tempUsername)
                    && !UserHelper.checkIfUserIsSaved(usersToSave,user) ){
                User existedUser = UserHelper.getExistedUser(existUsers,user.getEmail(),user.getUsername());
                user.setPassword(existedUser.getPassword());
                user.setId(existedUser.getId());
                usersToSave.add(user);
                csvResult.updateResult(CsvStatus.UPDATED,indexedUsers.get(user));
            }
            else {
                csvResult.updateResult(CsvStatus.FAILURE,indexedUsers.get(user));
            }
        }
        userRepo.saveAll(usersToSave);
        return csvResult;
    }
}
