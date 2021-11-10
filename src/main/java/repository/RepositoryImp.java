package repository;

import model.ToolManager;
import specification.ProjectSpecification;

import java.io.File;
import java.io.IOException;

public class RepositoryImp implements ProjectSpecification {

    private String directory = "/Users/macbookpro/Downloads/directory";


    @Override
    public void saveFile() {

    }

    @Override
    public void downloadFile(String path) {
        try {
            ToolManager.getInstance().getDownload().download(path);
        } catch (IOException e) {
            System.out.println("error");
        }
    }

    @Override
    public void deleteFile(String str, int val) {
        if(val==0){
            ToolManager.getInstance().getDelete().deleteFile(str,directory);
        }else if(val==1){
            ToolManager.getInstance().getDelete().deleteFiles(str,directory);
        }else if(val==2){
            ToolManager.getInstance().getDelete().deleteAll(str);
        }
    }

    @Override
    public void viewFile(String path) {
//        ToolManager.getInstance().getListFiles().listTxtFilesFromDirectory(path);
//          ToolManager.getInstance().getListFiles().listAllTxtFilesFromDirectoryAndSubdirectories(path);
//        ToolManager.getInstance().getListFiles().listAllFoldersFromDirectoryAndSubdirectories(path);
//        ToolManager.getInstance().getListFiles().listAllFoldersFromDirectory(path);
//        ToolManager.getInstance().getListFiles().listSortedFiles(path);
//        ToolManager.getInstance().getListFiles().listSortedFolders(path);
//          ToolManager.getInstance().getListFiles().listFilesByDateCreated(path);
//        ToolManager.getInstance().getListFiles().listFilesByDateModified(path);
    }

    @Override
    public void createStorage(String path, int val) {
        if(val == 0)
            ToolManager.getInstance().getCreate().makeFolder(directory,path);
        if(val == 1)
            ToolManager.getInstance().getCreate().makeFolders(directory, path);
    }


    @Override
    public void createFile(String path,int val) {
        if(val == 0) {
            try {
                ToolManager.getInstance().getCreate().makeFile(directory,path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(val == 1){
            try {
                ToolManager.getInstance().getCreate().makeFiles(directory,path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void moveFile(String file,String target){
        try {
            ToolManager.getInstance().getMove().moveFile(file,target);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void logIn(String username, String password) {
        ToolManager.getInstance().getLogIn().validateUser(username,password);
    }

    public String getDirectory() {
        return directory;
    }
}
