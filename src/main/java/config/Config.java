package config;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Config {

    private List<String> folders;
    private List<Integer> numOfFiles;
    private List<String> fileTypes;

    private List<Integer> folderSizes;

    public Config(List<String> folders, List<Integer> numOfFiles, List<String> fileTypes, List<Integer> folderSizes) {
        this.folders = folders;
        this.numOfFiles = numOfFiles;
        this.fileTypes = fileTypes;
        this.folderSizes = folderSizes;
    }

    public Config() {
    }


    public List<String> getFolders() {
        return folders;
    }

    public void setFolders(List<String> folders) {
        this.folders = folders;
    }

    public List<Integer> getNumOfFiles() {
        return numOfFiles;
    }

    public void setNumOfFiles(List<Integer> numOfFiles) {
        this.numOfFiles = numOfFiles;
    }

    public List<String> getFileTypes() {
        return fileTypes;
    }

    public void setFileTypes(List<String> fileTypes) {
        this.fileTypes = fileTypes;
    }

    public List<Integer> getFolderSizes() {
        return folderSizes;
    }

    public void setFolderSizes(List<Integer> folderSizes) {
        this.folderSizes = folderSizes;
    }

    @Override
    public String toString() {
        return "Config{" +
                ", folders=" + folders +
                ", numOfFiles=" + numOfFiles +
                ", fileTypes=" + fileTypes +
                ", folderSizes=" + folderSizes +
                '}';
    }
}
