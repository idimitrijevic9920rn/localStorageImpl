package config;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Config {

    private int maxFolderLength;
    private List<String> folders;
    private List<Integer> numOfFiles;
    private List<String> fileTypes;

    public Config(int maxFolderLength, List<String> folders, List<Integer> numOfFiles, List<String> fileTypes) {
        this.maxFolderLength = maxFolderLength;
        this.folders = folders;
        this.numOfFiles = numOfFiles;
        this.fileTypes = fileTypes;
    }

    public Config() {
    }

    public int getMaxFolderLength() {
        return maxFolderLength;
    }

    public void setMaxFolderLength(int maxFolderLength) {
        this.maxFolderLength = maxFolderLength;
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

    @Override
    public String toString() {
        return "Config{" +
                "maxFolderLength=" + maxFolderLength +
                ", folders=" + folders +
                ", numOfFiles=" + numOfFiles +
                ", fileTypes=" + fileTypes +
                '}';
    }
}
