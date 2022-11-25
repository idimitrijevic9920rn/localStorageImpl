package model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Comparator;


public class DeleteImpl {

    public DeleteImpl() {
    }


    public void deleteAll(String str){
        File dir = new File(ToolManager.getInstance().getDirectory());

        String[] filesToDelete = str.split(",");

        for(String s: filesToDelete){

            File toDelete = new File(ToolManager.getInstance().getDirectory() + "/" + s);
            toDelete.delete();
        }

    }

    public void deleteFiles(File file) {
        File[] contents = file.listFiles();
        if (contents != null) {
            for (File f : contents) {
                deleteFiles(f);
            }
        }
        file.delete();
    }

}
