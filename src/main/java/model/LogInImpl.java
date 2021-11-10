package model;

import com.fasterxml.jackson.core.type.TypeReference;
import user.Role;
import user.User;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;


public class LogInImpl {

    public LogInImpl() {
    }


    public void validateUser(String username,String password){
        if(username.equals("admin") && password.equals("admin")){
            File file = new File(ToolManager.getInstance().getJSONFilePath());
            if(!file.exists()) {
                ToolManager.getInstance().createJSON();
                User user1 = new User("user1", "user1", Arrays.asList(new Role("create"), new Role("delete")));
                User user2 = new User("user2", "user2", Arrays.asList(new Role("download"), new Role("list")));
                User user3 = new User("user3", "user3", Arrays.asList(new Role("move"), new Role("delete")));
                try {
                    ToolManager.getInstance().getPrivilegeLevel().clear();
                    ToolManager.getInstance().getPrivilegeLevel().addAll(Arrays.asList("create","delete","download","list","move"));
                    ToolManager.getInstance().getObjectMapper().writeValue(ToolManager.getInstance().getjSONFile(),Arrays.asList(user1,user2,user3));
                } catch (IOException e) {
                    System.out.println("json write error");
                }
            }
        }else{
            File file = new File(ToolManager.getInstance().getJSONFilePath());
            if(!file.exists()) {
                System.out.println("user.json file must be created by admin");
            }else{
                try {
                    List<User> users = ToolManager.getInstance().getObjectMapper().readValue(file, new TypeReference<List<User>>() {
                    });
                    boolean exists = false;
                    for(User user:users){
                        if(user.getPassword().equals(password) && user.getUsername().equals(username)){
                            exists = true;
                            ToolManager.getInstance().getPrivilegeLevel().clear();
                            for(Role role:user.getRoles()){
                                ToolManager.getInstance().getPrivilegeLevel().add(role.getRoleName());
                            }
                        }
                    }
                    if(exists==false)
                        System.out.println("wrong username or password");
                } catch (IOException e) {
                    System.out.println("user.json read error");
                }
            }

        }
    }


}
