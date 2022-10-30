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


    public void initialize(String path){

        File dir = new File(ToolManager.getInstance().getDirectory() + "/" + path);

        if(!dir.exists()){
            dir.mkdir();
            ToolManager.getInstance().setDirectory(path);
        }else{
            System.out.println("this storage alredy exists");
        }

    }

}
