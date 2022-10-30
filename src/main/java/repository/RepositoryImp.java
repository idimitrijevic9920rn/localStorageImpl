package repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import config.Config;
import model.ToolManager;
import org.apache.commons.io.FileUtils;
import specification.ProjectSpecification;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class RepositoryImp implements ProjectSpecification {


    @Override
    public void downloadFile(String path) {
        ToolManager.getInstance().getDownload().download(path);
    }

    @Override
    public void deleteFile(String str, int val) {
        if(val==1){
            File file = new File(ToolManager.getInstance().getDirectory() + "/" + str);
            ToolManager.getInstance().getDelete().deleteFiles(file);
        }else if(val==2){
            ToolManager.getInstance().getDelete().deleteAll(str);
        }
    }

    @Override
    public Object viewFile(Object path, int val, Object param) {

        switch (val) {
            case 1:
                return ToolManager.getInstance().getListFiles().listTxtFilesFromDirectory(((String) path));
            case 2:
                //vrati sve fajlove u zadatom direktorijumu i svim poddirektorijumima
                return ToolManager.getInstance().getListFiles().listAllFilesFromDirectoryAndSubdirectories(((String) path));
            case 3:
                //vrati fajlove sa određenom ekstenzijom
                return ToolManager.getInstance().getListFiles().listAllFilesWithExtension(((String) param));
            case 4:
                //vrati fajlove koji u svom imenu sadrže, počinju, ili se završavaju nekim
                //zadatim podstringom
                System.out.println(ToolManager.getInstance().getListFiles().listAllFilesWithSubstring(((String) param)));
                return ToolManager.getInstance().getListFiles().listAllFilesWithSubstring(((String) param));
            case 5:
//                vratiti da li određeni direktorijum sadrži fajl sa određenim imenom, ili više
//                fajlova sa zadatom listom imena
                return ToolManager.getInstance().getListFiles().listAllFilesWithExtension(((String) param));
           case 6:
//              vratiti u kom folderu se nalazi fajl sa određenim zadatim imenom,
                return String.valueOf(ToolManager.getInstance().getListFiles().getFileFolder(((String) param)));
            case 7:
                //pretraga po datumu kreiranje ili modifikacije, rastuće ili opadajuće,
                return ToolManager.getInstance().getListFiles().getSortedFiles(((String) path), ((String) param));
            case 8:
//              vrati fajlove koji su kreirani/modifikovani u nekom periodu, u nekom
//              direktorijumu,
                return ToolManager.getInstance().getListFiles().getCreatedAndModifiedFiles((String)path, ((List<String>) param));

        }

        return null;

    }

    @Override
    public Object filterFiles(String param) {
        System.out.println(ToolManager.getInstance().getFilterImpl().filterFiles(param));
        return ToolManager.getInstance().getFilterImpl().filterFiles(param);
    }

    @Override
    public void createStorage(String path, int val) {

        if (val == 0){
            File file = new File(ToolManager.getInstance().getDirectory() + "/" + path);
                boolean folderlen = this.maxDirLen(file);
                if(folderlen)
                    ToolManager.getInstance().getCreate().makeFolder(path);
                else System.out.println("folder exceeds allowed value of the files");
        }

        if (val == 1){
            String[] parts = path.split(",");
            for(String part:parts){
                File file = new File(ToolManager.getInstance().getDirectory() + "/" + part);
                    boolean folderlen = this.maxDirLen(file);
                    if(folderlen){
                        ToolManager.getInstance().getCreate().makeFolder(part);
                    }
                    else System.out.println("folder " + file.getParentFile().getName() + " exceeds allowed value of the files");
            }
        }


    }


    @Override
    public void createFile(String path,int val) {

        if(val==0){
            File file = new File(ToolManager.getInstance().getDirectory() + "/" + path);

            if(!pathExists(path)){
                System.out.println("Folder " + path.split("/")[path.split("/").length-2] + " does not exist");
               return;
            }

            boolean folderlen = this.maxDirLen(file);
            if(folderlen){
                if(this.checkFileExtension(path)){
                    if(this.checkFolderSize(path)){
                        ToolManager.getInstance().getCreate().makeFile(path);
                    }else
                        System.out.println("directory is full");
                }
                else
                    System.out.println("directory does not have premission for creating this file extension");
            }
            else System.out.println("folder exceeds allowed value of the files");

        }


        if (val == 1){
            String[] parts = path.split(",");
            for(String part:parts){
                File file = new File(ToolManager.getInstance().getDirectory() + "/" + part);
                    boolean folderlen = this.maxDirLen(file);
                    if(folderlen){
                        if(this.checkFileExtension(path))
                            ToolManager.getInstance().getCreate().makeFile(part);
                        else
                            System.out.println("directory does not have premission for creating this file extension");
                    }
                    else System.out.println("folder " + file.getParentFile().getName() + " exceeds allowed value of the files");
            }
        }



    }

    @Override
    public void rename(String path,String newName){
        ToolManager.getInstance().getRename().rename(path,newName);
    }

    @Override
    public void moveFile(String file,String target){
        try {
            ToolManager.getInstance().getMove().moveFile(file, target);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialiseDirectory(String path) {
        ToolManager.getInstance().getInitializeDirecory().initialize(path);
    }

    @Override
    public void createConfig(List<String> folders, List<Integer> numOfFiles, List<String> fileTypes, List<Integer> folderSizes) {
        ToolManager.getInstance().getCreateConfig().initialize(folders, numOfFiles, fileTypes, folderSizes);
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


    private boolean maxDirLen(File file){
        File dir = new File(ToolManager.getInstance().getDirectory());
        boolean flag = false;

        ObjectMapper mapper = new ObjectMapper();
        for(File f:dir.listFiles()){
            if(f.getName().equals("congig.json")){
                flag = true;
            }
        }

        if(flag){
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

        ObjectMapper mapper = ToolManager.getInstance().getObjectMapper();
        File config = new File(ToolManager.getInstance().getDirectory() + "/config.json");
        if(config.exists()){
            try {
                Config configs = mapper.readValue(config, Config.class);
                return !configs.getFileTypes().contains(parts[1]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        return true;
    }

    private boolean checkFolderSize(String file){

        File f = new File(ToolManager.getInstance().getDirectory() + "/" + file);

        String to = f.getAbsolutePath();
        String dirName = to.split(ToolManager.getInstance().getDirectory())[1].split("/")[1];
        File directory = new File(ToolManager.getInstance().getDirectory() + "/" + dirName);

        ObjectMapper mapper = new ObjectMapper();

        int index = -1;
        File config = new File(ToolManager.getInstance().getDirectory() + "/config.json");
        try {
            Config configs = mapper.readValue(config, Config.class);
            for(int i=0;i<configs.getFolders().size();i++){
                if(configs.getFolders().get(i).equals(dirName)){
                    index = i;
                }
            }

            if(index!=-1 && configs.getFolderSizes().get(index) <= FileUtils.sizeOf(directory)){
                return false;
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return true;

    }


    private boolean pathExists(String path){

        String[] arr = path.split("/");
        String folderPath = path.split("/" + arr[arr.length-1])[0];

        File file = new File(ToolManager.getInstance().getDirectory() + "/" + folderPath);

        return file.exists();
    }


}
