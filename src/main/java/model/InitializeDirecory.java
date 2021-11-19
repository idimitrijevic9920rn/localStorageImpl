package model;

import user.Role;
import user.User;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class InitializeDirecory {

    public InitializeDirecory() {
    }


    public void initialize(String path, String username, String password){

        File dir = new File(path);
        if(!dir.exists()){
            dir.mkdir();
            ToolManager.getInstance().setDirectory(path);
            File json = new File(path + "/" + "user.json");
            ToolManager.getInstance().setJSONFilePath(json.getAbsolutePath());
            ToolManager.getInstance().createJSON();
            User admin = new User(username,password,Arrays.asList(new Role("create"),new Role("createUser"), new Role("delete")),null);
            User user1 = new User("user1", "user1", Arrays.asList(new Role("create"), new Role("view")),Arrays.asList("folder1","folder2"));
            User user2 = new User("user2", "user2", Arrays.asList(new Role("download"), new Role("view")),Arrays.asList("folder2"));
            User user3 = new User("user3", "user3", Arrays.asList(new Role("move"), new Role("delete")),Arrays.asList("folder3"));
            ArrayList<User> users = new ArrayList<>();
            users.addAll(Arrays.asList(admin,user1,user2,user3));
            try {
                ToolManager.getInstance().getObjectMapper().writeValue(ToolManager.getInstance().getjSONFile(),users);
            } catch (IOException e) {
                System.out.println("json write error");
            }
        }else{
            System.out.println("this storage alredy exists");
        }

    }

}
