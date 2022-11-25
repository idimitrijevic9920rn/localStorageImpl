package model;

import java.io.File;

public class RenameImpl {

    public RenameImpl() {
    }

    public void rename(String path, String newName){
        File file = new File(ToolManager.getInstance().getDirectory() + "/" + path);
        File newFile = new File(ToolManager.getInstance().getDirectory() + "/" + path.split(file.getName())[0] + newName);
        file.renameTo(newFile);
        ToolManager.getInstance().getDelete().deleteFiles(file);
    }


}
