package model;

import com.fasterxml.jackson.core.type.TypeReference;
import user.Role;
import user.User;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class CreateUserImpl {

    public CreateUserImpl() {
    }

    public void createUser(String username,String password,String roles,List<String> folders){
        if(ToolManager.getInstance().isLogged()==true && ToolManager.getInstance().getPrivilegeLevel().contains("createUser")){
            File file = new File(ToolManager.getInstance().getJSONFilePath());
            String[] roleList = roles.split(",");
            ArrayList<Role> rolesList = new ArrayList<>();
            for(String role:roleList){
                rolesList.add(new Role(role));
            }
            User u = new User(username, password, rolesList,folders);
            try {
                List<User> users = ToolManager.getInstance().getObjectMapper().readValue(file, new TypeReference<List<User>>() {
                });
                boolean flag = true;
                ArrayList<User> toAdd = new ArrayList<>();
                for(User user:users){
                    toAdd.add(user);
                    if(user.getUsername().equals(u.getUsername()))
                        flag = false;
                }
                if(flag==true) {
                    toAdd.add(u);
                    try {
                        File json = new File(ToolManager.getInstance().getJSONFilePath());
                        ToolManager.getInstance().getObjectMapper().writeValue(json,toAdd);
                        System.out.println(u.getUsername() + " is sucesfully added");
                    } catch (IOException e) {
                        System.out.println("json write error");
                    }
                }else
                    System.out.println("user with that credentials alredy exists");
            } catch (IOException e) {
                System.out.println("user.json read error");
            }

        }else{
            System.out.println("user creation failed: user is not admin or user.txt does not exists");
        }
    }


}
