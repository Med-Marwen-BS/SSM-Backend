package com.example.userservice.Utility;

import com.example.userservice.Entity.Param.CsvConversionResult;
import com.example.userservice.Entity.Param.CsvResult;
import com.example.userservice.Entity.User;
import com.example.userservice.Enum.CsvStatus;
import com.example.userservice.Enum.Role;
import com.example.userservice.Enum.Sexe;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;


public class CsvHelper {

    public static CSVParser createCSVParser(InputStream is, char delemiteur) throws IOException {
        BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));

        return new CSVParser(fileReader,
                CSVFormat.DEFAULT.builder().setDelimiter(delemiteur).setHeader().setSkipHeaderRecord(true).setIgnoreHeaderCase(true)
                        .setTrim(false).setIgnoreEmptyLines(false).build());

    }

    public static CsvConversionResult csvToUsersList(InputStream inputStream, char delemiteur) {
        try (CSVParser csvParser = createCSVParser(inputStream, delemiteur)) {

            Map<User,Integer> indexedUsers = new HashMap<>();
            List<User> userList = new ArrayList<>();
            CsvResult csvResult = new CsvResult();
            User user;
            int index = 0;

            for (CSVRecord csvRecord : csvParser.getRecords()) {
                index++;
                try {
                    user = User.builder()
                            .firstName(csvRecord.get("prenom"))
                            .lastName(csvRecord.get("nom"))
                            .username(csvRecord.get("utilisateur"))
                            .password(UUID.randomUUID().toString())
                            .email(csvRecord.get("email"))
                            .role(Role.valueOf(csvRecord.get("role").toUpperCase()))
                            .sexe(Sexe.valueOf(csvRecord.get("sexe").toUpperCase()))
                            .enabled(true)
                            .build();
                    if(!UserHelper.checkFields(user)){
                        csvResult.updateResult(CsvStatus.FAILURE,index);
                        continue;
                    }

                } catch (Exception exception) {
                    throw new RuntimeException("Failed to parse CSV file: " + exception.getMessage());
                }
                indexedUsers.put(user,index);
                userList.add(user);
            }
            return CsvConversionResult.builder()
                    .userList(userList)
                    .csvResult(csvResult)
                    .indexedUsers(indexedUsers)
                    .build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
