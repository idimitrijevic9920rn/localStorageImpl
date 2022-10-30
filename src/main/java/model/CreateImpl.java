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



}
