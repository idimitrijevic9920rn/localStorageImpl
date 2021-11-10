package model;


import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ListFiles {

    public ListFiles() {
    }

    public void listTxtFilesFromDirectory(String str){
        if(str.equals("directory")){
            str = "";
        }
        File file = new File(ToolManager.getInstance().getDirectory() + "/" + str);
        for(File f:file.listFiles()){
            int sz = f.getName().length();
            String parts = String.valueOf(f.getName().toString().charAt(sz-1)) + String.valueOf(f.getName().toString().charAt(sz-2)) + String.valueOf(f.getName().toString().charAt(sz-3));
            if(parts.equals("txt") && f.isFile()){
                System.out.println(f.getName());
            }
        }
    }

    public void listAllTxtFilesFromDirectoryAndSubdirectories(String str){
        if(str.equals("directory")){
            str = "";
        }
        File f = new File(ToolManager.getInstance().getDirectory() + "/" + str);
        try {
            Files.walk(f.toPath()).sorted(Comparator.reverseOrder()).forEach(path -> {
                    String[] parts = path.toString().split("/");
                    String part = parts[parts.length - 1];
                    int sz = part.length();
                    if(sz>2) {
                        String txt = String.valueOf(part.charAt(sz - 1)) + String.valueOf(part.charAt(sz-2)) + String.valueOf(part.charAt(sz-3));
                        if (txt.equals("txt"))
                            System.out.println(part);
                    }
            });
        }catch (IOException e){
            System.out.println("folder not found");
        }
    }

    public void listAllFoldersFromDirectoryAndSubdirectories(String str){
        if(str.equals("directory")){
            str = "";
        }
        File f = new File(ToolManager.getInstance().getDirectory() + "/" + str);
        try {
            Files.walk(f.toPath()).sorted(Comparator.reverseOrder()).forEach(path -> {
                File file = new File(path.toString());
                if(file.isDirectory()){
                    String[] parts = path.toString().split("/");
                    System.out.println(parts[parts.length - 1]);
                }
            });
        }catch (IOException e){
            System.out.println("folder not found");
        }
    }

    public void listAllFoldersFromDirectory(String str){
        if(str.equals("directory")){
            str = "";
        }
        File file = new File(ToolManager.getInstance().getDirectory() + "/" + str);
        for(File f:file.listFiles()){
            if(f.isDirectory()){
                String[] parts = f.toPath().toString().split("/");
                System.out.println(parts[parts.length - 1]);
            }
        }
    }

    public void listSortedFiles(String str){
        if(str.equals("directory")){
            str = "";
        }
        File f = new File(ToolManager.getInstance().getDirectory() + "/" + str);
        ArrayList<File> files = new ArrayList<>();
        try {
            Files.walk(f.toPath()).sorted(Comparator.reverseOrder()).forEach(path -> {
                File file = new File(path.toString());
                if(file.isFile() && !file.getName().equals(".DS_Store")){
                    files.add(file);
                }
            });
            Collections.sort(files);
            for(File ff:files){
                System.out.println(ff.getName());
            }
        }catch (IOException e){
            System.out.println("folder not found");
        }
    }

    public void listSortedFolders(String str){
        if(str.equals("directory")){
            str = "";
        }
        File f = new File(ToolManager.getInstance().getDirectory() + "/" + str);
        ArrayList<String> files = new ArrayList<>();
        try {
            Files.walk(f.toPath()).sorted(Comparator.reverseOrder()).forEach(path -> {
                File file = new File(path.toString());
                if(file.isDirectory() && !file.getName().equals(".DS_Store")){
                    files.add(file.getName());
                }
            });
            Collections.sort(files);
            for(String ff:files){
                System.out.println(ff);
            }
        }catch (IOException e){
            System.out.println("folder not found");
        }
    }

    public void listFilesByDateCreated(String str){
        if(str.equals("directory")){
            str = "";
        }
        File f = new File(ToolManager.getInstance().getDirectory() + "/" + str);
        File[] files = f.listFiles();
        Comparator<File> comparator = Comparator.comparing(file -> {
            try {
                return Files.readAttributes(Paths.get(file.toURI()), BasicFileAttributes.class).creationTime();
            } catch (IOException e) {
                return null;
            }
        });

        Arrays.sort(files, comparator);
        for(File ff:files){
            if(!ff.getName().equals(".DS_Store") && ff.isFile())
                System.out.println(ff.getName());
        }
    }

    public void listFilesByDateModified(String str){
        if(str.equals("directory")){
            str = "";
        }
        File f = new File(ToolManager.getInstance().getDirectory() + "/" + str);
        File[] files = f.listFiles();
        Arrays.sort(files, Comparator.comparingLong(File::lastModified));
        for(File ff:files){
            if(!ff.getName().equals(".DS_Store") && ff.isFile())
                System.out.println(ff.getName());
        }

    }


}
