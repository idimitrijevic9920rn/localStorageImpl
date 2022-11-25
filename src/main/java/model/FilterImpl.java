package model;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class FilterImpl {

    public FilterImpl() {
    }

    public Object filterFiles(String param){

        List<File> files = new ArrayList<>();

        switch ((param)){
            case "absolutePath":
                return this.getAbsPath();
            case "relativePath":
                return this.relativePath();
            case "dateCreated":
                return this.dateCreated();
            case "dateModified":
                return this.dateModified();
            case "fileSize":
                return this.fileSize();

        }

        return files;
    }

    private List<String> getAbsPath(){
        List<String> files = new ArrayList<>();
        ToolManager.getInstance().getCurrentFileList().forEach(file -> files.add(file.getAbsolutePath()));
        return files;
    }

    private List<String> relativePath(){
        List<String> files = new ArrayList<>();
        ToolManager.getInstance().getCurrentFileList().forEach(file -> files.add(file.getName()));
        return files;
    }

    private Map<String, String> dateCreated(){
        Map<String,String> files = new HashMap<>();

        ToolManager.getInstance().getCurrentFileList().forEach(file -> {
            try {
                files.put(file.getName(), Files.readAttributes(file.toPath(), BasicFileAttributes.class).creationTime().toString());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        return files;
    }

    private Map<String, String> dateModified(){
        Map<String,String> files = new HashMap<>();

        ToolManager.getInstance().getCurrentFileList().forEach(file -> {
            try {
                files.put(file.getName(), Files.readAttributes(file.toPath(), BasicFileAttributes.class).lastModifiedTime().toString());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        return files;
    }

    private Map<String, Long> fileSize(){

        Map<String,Long> files = new HashMap<>();

        ToolManager.getInstance().getCurrentFileList().forEach(file -> files.put(file.getName(), FileUtils.sizeOf(file)));

        return files;
    }


}
