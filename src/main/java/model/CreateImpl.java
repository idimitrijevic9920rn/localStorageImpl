package model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CreateImpl {

    public CreateImpl() {
    }

    public void makeFolder(String path){
        File file = new File(ToolManager.getInstance().getDirectory() + "/" + path);
        file.mkdir();
    }


    public void makeFolders(String path) {
        String[] arr = path.split(",");
        for(String s:arr){
            File file = new File(ToolManager.getInstance().getDirectory() + "/" + s);
            file.mkdir();
        }
    }

    public void makeFile(String path){
        File file = new File(ToolManager.getInstance().getDirectory() + "/" + path);
        try {
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("failded to create file");
        }
    }

    public void makeFiles(String path){
        String[] arr = path.split(",");
        for(String s:arr){
            File file = new File(ToolManager.getInstance().getDirectory() + "/" + s);
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("failed to create file");
            }

        }
    }

    private boolean exeCheck(String path){
        String chars = path.substring(path.length()-3);
        if(chars.equals("exe")){
            return true;
        }
        return false;
    }

    private boolean checkUserFolderPremission(String files){



        return false;
    }


}
