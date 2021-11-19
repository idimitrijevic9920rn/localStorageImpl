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


    public void deleteFile(String str){
        String dir = ToolManager.getInstance().getDirectory();
        String directory = ToolManager.getInstance().getDirectory().substring(0, ToolManager.getInstance().getDirectory().length()-1);
        String json = ToolManager.getInstance().getDirectory() + "/" + "user.json";
        File file = new File(dir + "/" + str);
        if(str.equals(dir)) {
            str = "";
            file = new File(dir);
        }
        try {
            Files.walk(file.toPath()).sorted(Comparator.reverseOrder()).forEach(path -> {
                        try {
                            if(path.toString().equals(json))
                                System.out.println("user.json can not be deleted");
                            else if(path.toString().equals(directory))
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
        File dir = new File(ToolManager.getInstance().getDirectory());
        String pathh = ToolManager.getInstance().getDirectory() + "/" + str;
        if(str.equals(dir.getName().toString())){
            pathh = ToolManager.getInstance().getDirectory();
        }
        File file = new File(pathh);
        try {
            Files.walk(file.toPath()).sorted(Comparator.reverseOrder()).forEach(path -> {
                try {
                    File toDelete = new File(path.toString());
                    if(toDelete.getName().equals("user.json"))
                        System.out.println("user.json can not be deleted");
                    else if(!path.toString().equals(file.getPath()))
                        Files.delete(path);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }catch (IOException e){
            System.out.println("delete error");
        }

    }

    public void deleteFiles(String str){
        String[] list = str.split(",");
        ArrayList<String> arr = new ArrayList<>();
        for(String f:list){
            arr.add(ToolManager.getInstance().getDirectory() + "/" + f);
        }
        File file = new File(ToolManager.getInstance().getDirectory());
        File[] files = file.listFiles();
        int count = files.length;


        for (int i = 0; i < count; i++) {
            if(arr.contains(files[i].toString())) {
                if (files[i].isFile()) {
                    files[i].delete();
                } else {
                    String[] parts = files[i].toString().split("/");
                    deleteAll(parts[parts.length - 1]);
                    files[i].delete();

                }
            }
        }

    }

}
