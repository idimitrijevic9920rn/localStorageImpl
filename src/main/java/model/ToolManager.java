package model;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ToolManager {

    private String directory;
    private static final String downloads = "/Users/ilija/Downloads/downloads/downloads";
    public static final String dirName = "/Users/ilija/Downloads/downloads/root";

    private CreateImpl create;
    private DeleteImpl delete;
    private MoveImpl move;
    private DownloadImpl download;
    private ListFiles listFiles;
    private FilterImpl filterImpl;
    private RenameImpl rename;
    private InitializeDirecory initializeDirecory;
    private CreateConfigImpl createConfig;

    private List<File> currentFileList;
    private ObjectMapper objectMapper;

    private static ToolManager instance = null;


    public static ToolManager getInstance(){
        if (instance==null){
            instance = new ToolManager();
            instance.initialise();
        }
        return instance;
    }

    private void initialise(){
        this.setDirectory(dirName);

        this.rename = new RenameImpl();
        this.create = new CreateImpl();
        this.delete = new DeleteImpl();
        this.move = new MoveImpl();
        this.download = new DownloadImpl();
        this.filterImpl = new FilterImpl();
        this.listFiles = new ListFiles();
        this.objectMapper = new ObjectMapper();
        this.currentFileList = new ArrayList<>();
        this.initializeDirecory = new InitializeDirecory();
        this.createConfig = new CreateConfigImpl();
    }


    public FilterImpl getFilterImpl() {
        return filterImpl;
    }

    public List<File> getCurrentFileList() {
        return currentFileList;
    }

    public void setCurrentFileList(List<File> currentFileList) {
        this.currentFileList = currentFileList;
    }


    public RenameImpl getRename() {
        return rename;
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


    public InitializeDirecory getInitializeDirecory() {
        return initializeDirecory;
    }


    public CreateConfigImpl getCreateConfig() {
        return createConfig;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }


    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

}
