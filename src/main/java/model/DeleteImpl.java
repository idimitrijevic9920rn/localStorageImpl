package model;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;


public class DeleteImpl {

    public DeleteImpl() {
    }


    public void deleteFile(String str,String dir){
        String directory = ToolManager.getInstance().getDirectory().substring(0, ToolManager.getInstance().getDirectory().length()-1);
        if(str.equals("directory")) {
            str = "";
        }
        File file = new File(dir + str);
        try {
            Files.walk(file.toPath()).sorted(Comparator.reverseOrder()).forEach(path -> {
                        try {
                            if(path.toString().equals(directory))
                                System.out.println("directory can not be deleted");
                            else
                                Files.delete(path);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        }catch (IOException e){
            System.out.println("delete error");
        }

    }

    public void deleteAll(String str){
        String dir = ToolManager.getInstance().getDirectory().substring(0, ToolManager.getInstance().getDirectory().length()-1);
        int flag = 0;
        if(str.equals("directory")) {
            str = "";
        }
        File file = new File(ToolManager.getInstance().getDirectory() + "/" + str);
        try {
            Files.walk(file.toPath()).sorted(Comparator.reverseOrder()).forEach(path -> {
                try {
                    if(!path.toString().equals(file.getPath()))
                        Files.delete(path);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }catch (IOException e){
            System.out.println("delete error");
        }

    }

    public void deleteFiles(String str,String dir){
        String[] list = str.split(",");
        ArrayList<String> arr = new ArrayList<>();
        for(String f:list){
            arr.add(f);
        }
        File file = new File(dir);
        File[] files = file.listFiles();

        for(File f:files){
            if(arr.contains(f.getName())){
                f.delete();
            }
        }


    }

}
