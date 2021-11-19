package model;

import com.fasterxml.jackson.core.type.TypeReference;
import user.Role;
import user.User;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class LogInImpl {

    public LogInImpl() {
    }


    public void validateUser(String username,String password){

        if(ToolManager.getInstance().getDirectory()==null){
            System.out.println("directory is not chosen");
            return;
        }

        File file = new File(ToolManager.getInstance().getJSONFilePath());
        try {
            List<User> users = ToolManager.getInstance().getObjectMapper().readValue(file, new TypeReference<List<User>>() {
            });
            boolean exists = false;
            for (User user : users) {
                if (user.getPassword().equals(password) && user.getUsername().equals(username)) {
                    ToolManager.getInstance().setLogged(true);
                    ToolManager.getInstance().setCurrentUser(username);
                    ToolManager.getInstance().setFolders(user.getFolders());
                    ToolManager.getInstance().setAdmin(users.get(0).getUsername());
                    System.out.println(username + " is now logged on");
                    exists = true;
                    for (Role role : user.getRoles()) {
                        ToolManager.getInstance().getPrivilegeLevel().add(role.getRoleName());
                    }
                }
            }
            if (exists == false)
                System.out.println("wrong username or password");
        } catch (IOException e) {
            System.out.println("user.json read error");
        }



    }




}
