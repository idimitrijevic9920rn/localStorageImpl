package model;


import java.io.File;


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
