package model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import config.Config;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CreateConfigImpl {

    public CreateConfigImpl() {
    }

    public void initialize(List<String> folders , List<Integer> numOfFiles, List<String> fileTypes, List<Integer> folderSizes){


        ObjectMapper mapper = ToolManager.getInstance().getObjectMapper();
        File file = new File(ToolManager.getInstance().getDirectory() + "/" + "config.json");
        System.out.printf(file.getAbsolutePath());
        try {
            file.createNewFile();
            Config config = new Config(folders,numOfFiles,fileTypes, folderSizes);
            mapper.writeValue(file,config);


            System.out.println("config file succesfully created");
        } catch (IOException e) {
            System.out.println("error");
        }

    }

}
