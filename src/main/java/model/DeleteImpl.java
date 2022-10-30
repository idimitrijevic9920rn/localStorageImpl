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
        String pathh = ToolManager.getInstance().getDirectory() + "/" + str;
        if(str.equals(dir.getName().toString())){
            pathh = ToolManager.getInstance().getDirectory();
        }
        File file = new File(pathh);
        try {
            Files.walk(file.toPath()).sorted(Comparator.reverseOrder()).forEach(path -> {
                try {
                    File toDelete = new File(path.toString());
                    if(toDelete.getName().equals("user.json"))
                        System.out.println("user.json can not be deleted");
                    else if(!path.toString().equals(file.getPath()))
                        Files.delete(path);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }catch (IOException e){
            System.out.println("delete error");
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
