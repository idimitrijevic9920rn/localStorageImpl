package model;


import java.io.File;


public class InitializeDirecory {

    public InitializeDirecory() {
    }


    public void initialize(String path){

        File dir = new File("/Users/ilija/Downloads/downloads" + "/" + path);

        if(!dir.exists()){
            dir.mkdir();
        }else{
            System.out.println("main directory alredy exists");
        }

        ToolManager.getInstance().setDirectory(dir.getPath());

    }

}
