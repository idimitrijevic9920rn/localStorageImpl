package model;

import java.io.File;
import java.io.IOException;


public class CreateImpl {

    public CreateImpl() {
    }

    public void makeFolder(String path){

        File file = new File(ToolManager.getInstance().getDirectory() + "/" + path);

        File dir = new File(ToolManager.getInstance().getDirectory() + "/" + path.split("/" + file.getName())[0]);
        if(dir.isDirectory()){
            for (File listFile : dir.listFiles()) {
                if(listFile.getName().equals(file.getName())){
                    System.out.println("Folder " + file.getName() + " alredy exists");
                    return;
                }
            }
        }

        file.mkdir();
        System.out.println("successfully created folder " + file.getName());
    }



    public void makeFile(String path){
        File file = new File(ToolManager.getInstance().getDirectory() + "/" + path);

        if(file.exists()){
            System.out.println("file " + file.getName() + " alredy exist");
            return;
        }

        try {
            if(file.createNewFile()){
                System.out.println("File " + file.getName() + " created");
            }
        } catch (IOException e) {
            System.out.println("failded to create file");
        }
    }



}
