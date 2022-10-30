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
        boolean exists = exists(file,target);

        File f = new File(ToolManager.getInstance().getDirectory() + "/" + file);
        File dir = new File( ToolManager.getInstance().getDirectory());
        if(exists==false){
            if(target.equals(dir.getName())) {
                target = dir.getAbsolutePath() + "/" + f.getName();
            }
            else
                target = dir.getAbsolutePath() + "/" + target + "/" + f.getName();
            File location = new File(target);
            try {
                Files.copy(f.toPath(),location.toPath(),StandardCopyOption.REPLACE_EXISTING);
                File ff = new File(ToolManager.getInstance().getDirectory() + "/" + file);
                ToolManager.getInstance().getDelete().deleteFiles(ff);
                System.out.println("file copied succefully");
            }catch (NoSuchFileException e){
                System.out.println("file does not exists");
            }
        }else
            System.out.println("file " + file + " in " + target + " alredy exists");


    }

    private boolean exists(String fileName, String target){
        File dir = new File(ToolManager.getInstance().getDirectory());
        File file = new File(ToolManager.getInstance().getDirectory() + "/" + fileName);
        int flag = 0;
        File targ;
        if(target.equals(dir.getName())){
            targ = dir;
        }else{
            targ = new File(ToolManager.getInstance().getDirectory() + "/" + target);
        }

        for(File ff:targ.listFiles()){
            if(ff.getName().equals(file.getName())){
                return true;
            }
        }
        return false;
    }

}
