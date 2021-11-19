package repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import config.Config;
import model.ToolManager;
import specification.ProjectSpecification;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RepositoryImp implements ProjectSpecification {


    @Override
    public void saveFile() {
        System.out.println("this function does not work for local storage");

    }


    @Override
    public void downloadFile(String path) throws IOException {
        if(ToolManager.getInstance().isLogged()==true) {
            if(ToolManager.getInstance().getPrivilegeLevel().contains("download")) {
                boolean flag = this.checkUserPrivilege("download");
                if (flag == true)
                    ToolManager.getInstance().getDownload().download(path);
                else
                    this.noUserPremission();
            }else
                this.noUserPremission();
        }else
            this.nobodyIsLoggedOn();

    }

    @Override
    public void deleteFile(String str, int val) {
        boolean flag = this.checkUserPrivilege("delete");
        if(flag==true){
            if(val==0){
                ToolManager.getInstance().getDelete().deleteFile(str);
            }else if(val==1){
                ToolManager.getInstance().getDelete().deleteFiles(str);
            }else if(val==2){
                ToolManager.getInstance().getDelete().deleteAll(str);
            }
        }
        else
            this.noUserPremission();
    }

    @Override
    public void viewFile(String path,int val) {
        if(ToolManager.getInstance().isLogged()==true) {
            if(ToolManager.getInstance().getPrivilegeLevel().contains("view")) {
                File file = new File(ToolManager.getInstance().getDirectory() + "/" + path);
                boolean flag = this.checkUserFolderPremission(file,0);
                if(flag==true) {
                    switch (val) {
                        case 1:
                            ToolManager.getInstance().getListFiles().listTxtFilesFromDirectory(path);
                            break;
                        case 2:
                            ToolManager.getInstance().getListFiles().listAllTxtFilesFromDirectoryAndSubdirectories(path);
                            break;
                        case 3:
                            ToolManager.getInstance().getListFiles().listAllFoldersFromDirectoryAndSubdirectories(path);
                            break;
                        case 4:
                            ToolManager.getInstance().getListFiles().listAllFoldersFromDirectory(path);
                            break;
                        case 5:
                            ToolManager.getInstance().getListFiles().listSortedFiles(path);
                            break;
                        case 6:
                            ToolManager.getInstance().getListFiles().listSortedFolders(path);
                            break;
                        case 7:
                            ToolManager.getInstance().getListFiles().listFilesByDateCreated(path);
                            break;
                        case 8:
                            ToolManager.getInstance().getListFiles().listFilesByDateModified(path);
                    }
                }else
                    System.out.println("user does not have premissions to this file");
            }else
                this.noUserPremission();
        }else
            this.nobodyIsLoggedOn();
    }

    @Override
    public void createStorage(String path, int val) {
        if (ToolManager.getInstance().isLogged() == true){
            if(ToolManager.getInstance().getPrivilegeLevel().contains("create")){
                if (val == 0){
                    File file = new File(ToolManager.getInstance().getDirectory() + "/" + path);
                    boolean flag = this.checkUserFolderPremission(file,1);
                    if(flag==true) {
                        boolean size = this.maxDirSize(file);
                        if(size==true) {
                            boolean folderlen = this.maxDirLen(file);
                            if(folderlen==true)
                                ToolManager.getInstance().getCreate().makeFolder(path);
                            else System.out.println("folder exceeds allowed value of the files");
                        }
                    }else System.out.println("user does not have premission to access this folder");
                }
                if (val == 1){
                    String parts[] = path.split(",");
                    for(String part:parts){
                        File file = new File(ToolManager.getInstance().getDirectory() + "/" + part);
                        boolean flag = this.checkUserFolderPremission(file,1);
                        if(flag==true){
                            boolean folderlen = this.maxDirLen(file);
                            if(folderlen==true){
                                ToolManager.getInstance().getCreate().makeFolder(part);
                            }
                            else System.out.println("folder " + file.getParentFile().getName() + " exceeds allowed value of the files");
                        }
                        else System.out.println("user does not have premission to this folder");
                    }
                }
            }else
                this.noUserPremission();
        }else
            this.nobodyIsLoggedOn();
    }


    @Override
    public void createFile(String path,int val) {
        if (ToolManager.getInstance().isLogged() == true) {
            if(ToolManager.getInstance().getPrivilegeLevel().contains("create")){
                if(val==0){
                    File file = new File(ToolManager.getInstance().getDirectory() + "/" + path);
                    boolean flag = this.checkUserFolderPremission(file,0);
                    if(flag==true) {
                        boolean size = this.maxDirSize(file);
                        if(size==true) {
                            boolean folderlen = this.maxDirLen(file);
                            if(folderlen==true)
                                if(this.checkFileExtension(path)==true)
                                    ToolManager.getInstance().getCreate().makeFile(path);
                                else
                                    System.out.println("directory does not have premission for creating this file extension");
                            else System.out.println("folder exceeds allowed value of the files");
                        }else System.out.println("folder is full");
                    }else System.out.println("user does not have premission to access this folder");
                }
                if (val == 1){
                    String parts[] = path.split(",");
                    for(String part:parts){
                        File file = new File(ToolManager.getInstance().getDirectory() + "/" + part);
                        boolean flag = this.checkUserFolderPremission(file,0);
                        if(flag==true){
                            boolean folderlen = this.maxDirLen(file);
                            if(folderlen==true){
                                if(this.checkFileExtension(path)==true)
                                    ToolManager.getInstance().getCreate().makeFile(part);
                                else
                                    System.out.println("directory does not have premission for creating this file extension");
                            }
                            else System.out.println("folder " + file.getParentFile().getName() + " exceeds allowed value of the files");
                        }else System.out.println("user doesn not have premission to access this file");
                    }
                }
            }else this.noUserPremission();
        }else this.nobodyIsLoggedOn();

    }

    @Override
    public void moveFile(String file,String target){
        if (ToolManager.getInstance().isLogged() == true){
            if(ToolManager.getInstance().getPrivilegeLevel().contains("move")){
                File file1 = new File(ToolManager.getInstance().getDirectory() + "/" + file);
                File file2 = new File(ToolManager.getInstance().getDirectory() + "/" + target);
                boolean flag = this.checkUserFolderPremission(file1,1);
                boolean flag1 = this.checkUserFolderPremission(file2,0);
                if(flag==true && flag1==true) {
                    try {
                        ToolManager.getInstance().getMove().moveFile(file, target);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }else
                this.noUserPremission();
        }else
            this.nobodyIsLoggedOn();
    }

    @Override
    public void logIn(String username, String password) {
        if (ToolManager.getInstance().isLogged() == false) {
            ToolManager.getInstance().getLogIn().validateUser(username, password);
        }else
            System.out.println("user is alredy logged on");
    }

    @Override
    public void logOut() {
        if(ToolManager.getInstance().isLogged() == true)
            ToolManager.getInstance().getLogOut().logOut();
        else
            System.out.println("nobody is logged on");
    }

    @Override
    public void createUser(String username,String password,String roles,List<String> folders) {
        if(ToolManager.getInstance().isLogged() == true) {
            if(ToolManager.getInstance().getPrivilegeLevel().contains("createUser"))
                ToolManager.getInstance().getCreateUser().createUser(username, password, roles,folders);
            else
                this.noUserPremission();
        }
    }

    @Override
    public void initialiseDirectory(String path, String username,String password) {
        if(ToolManager.getInstance().isLogged() == false) {
            ToolManager.getInstance().getInitializeDirecory().initialize(path, username, password);
        }else
            System.out.println("user is logged on, logout to create other directory");
    }

    @Override
    public void createConfig(int maxFolderLength, List<String> folders, List<Integer> numOfFiles, List<String> fileTypes) {
        if (ToolManager.getInstance().isLogged()==true){
                ToolManager.getInstance().getCreateConfig().initialize(maxFolderLength, folders, numOfFiles, fileTypes);
        }else
            this.nobodyIsLoggedOn();
    }


    @Override
    public void changeDir(String path, String username, String password) {
        if(ToolManager.getInstance().isLogged()==false)
            ToolManager.getInstance().getChangeDir().validate(path,username,password);
        else
            System.out.println("user is logged on, log out to change directory");
    }


    private boolean checkUserPrivilege(String privilege){
        if(ToolManager.getInstance().isLogged() == true && ToolManager.getInstance().getPrivilegeLevel().contains(privilege))
            return true;
        else
            return false;
    }

    private boolean checkUserFolderPremission(File file,int flag){
        if(ToolManager.getInstance().getFolders() == null)
            return true;
        if(flag==0) {
            if (ToolManager.getInstance().getFolders().contains(file.getName()) || ToolManager.getInstance().getFolders().contains(file.getParentFile().getName()))
                return true;
        }else if(flag==1){
            if (ToolManager.getInstance().getFolders().contains(file.getParentFile().getName()))
                return true;
        }
        return false;
    }

    private void noUserPremission() {
        System.out.println("this user does not have permission to do that");
    }

    private void nobodyIsLoggedOn(){
        System.out.println("nobody is logged on");
    }

    private long folderSize(File directory) {
        long length = 0;
        for (File file : directory.listFiles()) {
            if (file.isFile())
                length += file.length();
            else
                length += folderSize(file);
        }
        return length;
    }

    private boolean maxDirSize(File file){
        File dir = new File(ToolManager.getInstance().getDirectory());
        boolean flag = true;
        int maxDirSize;
        ObjectMapper mapper = new ObjectMapper();
        for(File f:dir.listFiles()){
            if(f.getName().equals("congig.json")){
                flag = true;
            }
        }
        if(flag==true){
            try {
                File config = new File(dir+"/config.json");
                Config configs = mapper.readValue(config, Config.class);
                maxDirSize = configs.getMaxFolderLength();
                if((long)folderSize(dir) + file.length() < (long)maxDirSize)
                    return true;
                else return false;
            } catch (IOException e) {
                System.out.println("config.json read error");
            }
        }
        return true;
    }

    private boolean maxDirLen(File file){
        File dir = new File(ToolManager.getInstance().getDirectory());
        boolean flag = true;
        int maxDirSize;
        ObjectMapper mapper = new ObjectMapper();
        for(File f:dir.listFiles()){
            if(f.getName().equals("congig.json")){
                flag = true;
            }
        }

        if(flag==true){
            try {
                File config = new File(dir + "/config.json");
                Config configs = mapper.readValue(config, Config.class);
                List<String> folders = configs.getFolders();
                List<Integer> numOfFiles = configs.getNumOfFiles();
                File chekck = file.getParentFile();
                for(int i=0;i<folders.size();i++){
                    if(folders.get(i).equals(chekck.getName())){
                        if(chekck.listFiles().length <= numOfFiles.get(i)){
                            System.out.println(chekck.listFiles().length +  "  " + numOfFiles.get(i));
                            return true;
                        }
                        else return false;
                    }
                }

            } catch (IOException e) {
                System.out.println("config.json read error");
            }
        }
        return true;
    }

    private boolean checkFileExtension(String file){
        String[] parts = file.split("\\.");
        if(parts.length==1 || parts.length==0) return true;
        ObjectMapper mapper = ToolManager.getInstance().getObjectMapper();
        File config = new File(ToolManager.getInstance().getDirectory() + "/config.json");
        if(config.exists()){
            try {
                Config configs = mapper.readValue(config, Config.class);
                if(configs.getFileTypes().contains(parts[1]))
                    return false;
                else return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else return true;

        return true;
    }


}
