package model;

import com.fasterxml.jackson.core.type.TypeReference;
import user.User;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ChangeDirImpl {

    public ChangeDirImpl() {
    }

    public void validate(String path, String username, String password){
        File file = new File(path);
        if(file.exists()){
            try {
                boolean flag = check(path);
                if(flag==true) {
                    File json = new File(path + "/user.json");
                    List<User> users = ToolManager.getInstance().getObjectMapper().readValue(json, new TypeReference<List<User>>() {
                    });
                    String adminUsername = users.get(0).getUsername().toString();
                    String adminPassword = users.get(0).getPassword().toString();
                    if (username.equals(adminUsername) && password.equals(adminPassword)) {
                        ToolManager.getInstance().setDirectory(path);
                        ToolManager.getInstance().setJSONFilePath(path + "/user.json");
                        ToolManager.getInstance().setAdmin(username);
                        System.out.println("succesfully changed directory, login now");
                    } else
                        System.out.println("user " + username + " is not admin of this directory");
                }else
                    System.out.println(path + " is not directory");
            } catch (IOException e) {
                System.out.println("json read error");
            }

        }else
            System.out.println("this directory does not exists");
    }

    private boolean check(String path){
        File file = new File(path);
        for(File f:file.listFiles()){
            if(f.isFile() && f.getName().equals("user.json")){
                return true;
            }
        }
        return false;
    }

}
