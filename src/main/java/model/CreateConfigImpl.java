package model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import config.Config;
import user.User;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CreateConfigImpl {

    public CreateConfigImpl() {
    }

    public void initialize(int maxSize, List<String> folders , List<Integer> numOfFiles, List<String> fileTypes){
        if(!ToolManager.getInstance().getCurrentUser().equals(ToolManager.getInstance().getAdmin())){
            System.out.println(ToolManager.getInstance().getCurrentUser());
            System.out.println(ToolManager.getInstance().getAdmin());
            System.out.println("user " + ToolManager.getInstance().getCurrentUser() + " does not have premission to create config file");
            return;
        }
        ObjectMapper mapper = ToolManager.getInstance().getObjectMapper();
        File file = new File(ToolManager.getInstance().getDirectory() + "/" + "config.json");
        try {
            file.createNewFile();
            Config config = new Config(maxSize,folders,numOfFiles,fileTypes);
            mapper.writeValue(file,config);
            Config configs = ToolManager.getInstance().getObjectMapper().readValue(file, Config.class);
            ToolManager.getInstance().setDependentFolders(folders);
            ToolManager.getInstance().setDepedentFolderValues(numOfFiles);
            ToolManager.getInstance().setForbiddenFileTypes(fileTypes);
            System.out.println("config file succesfully created");
        } catch (IOException e) {
            System.out.println("error");
        }

    }

}
