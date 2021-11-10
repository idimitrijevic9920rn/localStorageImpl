package model;

import com.fasterxml.jackson.databind.ObjectMapper;
import user.User;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ToolManager {

    private String directory = "/Users/macbookpro/Downloads/directory";
    private String downloads = "/Users/macbookpro/Downloads/downloads";
    private String JSONFilePath = "/Users/macbookpro/Downloads/directory/user.json";
    private File jSONFile;
    private CreateImpl create;
    private DeleteImpl delete;
    private MoveImpl move;
    private DownloadImpl download;
    private ListFiles listFiles;
    private LogInImpl logIn;
    private List<String> privilegeLevel = new ArrayList<>();
    private ObjectMapper objectMapper;

    private static ToolManager instance = null;


    private ToolManager() {

    }

    public static ToolManager getInstance(){
        if (instance==null){
            instance=new ToolManager();
            instance.initialise();
        }
        return instance;
    }

    private void initialise(){
        this.create = new CreateImpl();
        this.delete = new DeleteImpl();
        this.move = new MoveImpl();
        this.download = new DownloadImpl();
        this.listFiles = new ListFiles();
        this.logIn = new LogInImpl();
        this.objectMapper = new ObjectMapper();
    }

    public void createJSON(){
        File file = new File(this.getJSONFilePath());
        try {
            file.createNewFile();
            jSONFile = file;
            System.out.println("json file succesfully created");
        } catch (IOException e) {
            System.out.println("error");
        }
    }



    public CreateImpl getCreate() {
        return create;
    }

    public DeleteImpl getDelete() {
        return delete;
    }

    public MoveImpl getMove() {
        return move;
    }

    public String getDirectory() {
        return directory;
    }

    public DownloadImpl getDownload() {
        return download;
    }

    public String getDownloads() {
        return downloads;
    }

    public ListFiles getListFiles() {
        return listFiles;
    }

    public String getJSONFilePath() {
        return JSONFilePath;
    }

    public List<String> getPrivilegeLevel() {
        return privilegeLevel;
    }

    public void setPrivilegeLevel(List<String> privilegeLevel) {
        this.privilegeLevel = privilegeLevel;
    }

    public File getjSONFile() {
        return jSONFile;
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public LogInImpl getLogIn() {
        return logIn;
    }
}
