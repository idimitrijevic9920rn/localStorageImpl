package model;

import com.fasterxml.jackson.databind.ObjectMapper;
import user.User;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ToolManager {

    private String directory;
    private String downloads = "/Users/macbookpro/Downloads/downloads";
    private String JSONFilePath;
    private File jSONFile;
    private CreateImpl create;
    private DeleteImpl delete;
    private MoveImpl move;
    private DownloadImpl download;
    private ChangeDirImpl changeDir;
    private ListFiles listFiles;
    private LogInImpl logIn;
    private LogOutImpl logOut;
    private CreateUserImpl createUser;
    private InitializeDirecory initializeDirecory;
    private CreateConfigImpl createConfig;
    private List<String> privilegeLevel = new ArrayList<>();
    private ObjectMapper objectMapper;
    private boolean logged;
    private boolean dirCreated;
    private int maxDirSize;
    private String admin;
    private String currentUser;
    private List<String> folders;
    private List<String> dependentFolders;
    private List<Integer> depedentFolderValues;
    private List<String> forbiddenFileTypes;


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
        this.createUser = new CreateUserImpl();
        this.logOut = new LogOutImpl();
        this.objectMapper = new ObjectMapper();
        this.initializeDirecory = new InitializeDirecory();
        this.changeDir = new ChangeDirImpl();
        this.createConfig = new CreateConfigImpl();
        this.depedentFolderValues = new ArrayList<>();
        this.depedentFolderValues = new ArrayList<>();
        this.forbiddenFileTypes = new ArrayList<>();
        this.folders = new ArrayList<>();
        this.logged = false;
        this.dirCreated = false;
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

    public long getFolderSize(File folder) {
        long length = 0;
        File[] files = folder.listFiles();
        int count = files.length;
        for (int i = 0; i < count; i++) {
            if (files[i].isFile()) {
                length += files[i].length();
            }
            else {
                length += getFolderSize(files[i]);
            }
        }
        return length;
    }

    public List<String> getFolders() {
        return folders;
    }

    public void setFolders(List<String> folders) {
        this.folders = folders;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public String getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
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

    public InitializeDirecory getInitializeDirecory() {
        return initializeDirecory;
    }

    public int getMaxDirSize() {
        return maxDirSize;
    }

    public void setMaxDirSize(int maxDirSize) {
        this.maxDirSize = maxDirSize;
    }

    public List<String> getPrivilegeLevel() {
        return privilegeLevel;
    }

    public boolean isDirCreated() {
        return dirCreated;
    }

    public CreateConfigImpl getCreateConfig() {
        return createConfig;
    }

    public List<String> getDependentFolders() {
        return dependentFolders;
    }

    public void setDependentFolders(List<String> dependentFolders) {
        this.dependentFolders = dependentFolders;
    }

    public List<Integer> getDepedentFolderValues() {
        return depedentFolderValues;
    }

    public void setDepedentFolderValues(List<Integer> depedentFolderValues) {
        this.depedentFolderValues = depedentFolderValues;
    }

    public List<String> getForbiddenFileTypes() {
        return forbiddenFileTypes;
    }

    public void setForbiddenFileTypes(List<String> forbiddenFileTypes) {
        this.forbiddenFileTypes = forbiddenFileTypes;
    }

    public void setJSONFilePath(String JSONFilePath) {
        this.JSONFilePath = JSONFilePath;
    }

    public ChangeDirImpl getChangeDir() {
        return changeDir;
    }

    public void setDirCreated(boolean dirCreated) {
        this.dirCreated = dirCreated;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public LogOutImpl getLogOut() {
        return logOut;
    }

    public CreateUserImpl getCreateUser() {
        return createUser;
    }

    public boolean isLogged() {
        return logged;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
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
