package model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.StandardCopyOption;

public class MoveImpl {

    public MoveImpl() {
    }


    public void moveFile(String file, String target) throws IOException {
        String[] parts = file.split("/");
        String dir = ToolManager.getInstance().getDirectory();
        if(target.equals("directory")) {
            target = dir + parts[parts.length-1];
        }
        else
            target = dir + "/" + target + "/" + parts[parts.length - 1];
        String f1 = dir + file;
        File f = new File(f1);
        File location = new File(target);
        try {
            Files.copy(f.toPath(),location.toPath(),StandardCopyOption.REPLACE_EXISTING);
            ToolManager.getInstance().getDelete().deleteFile(file,dir);
            System.out.println("file copied succefully");
        }catch (NoSuchFileException e){
            System.out.println("file does not exists");

        }
    }

}
