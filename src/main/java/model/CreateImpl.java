package model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class CreateImpl {

    public CreateImpl() {
    }

    public void makeFolder(String dir, String path){
        File file = new File(dir + "/" + path);
        file.mkdir();
    }


    public void makeFolders(String dir, String path) {
        String[] arr = path.split(",");
        for(String s:arr){
            File file = new File(dir + "/" + s);
            file.mkdir();
        }
    }

    public void makeFile(String dir, String path) throws IOException {
        File file = new File(dir + "/" + path);
        file.createNewFile();
    }

    public void makeFiles(String dir, String path) throws IOException {
        String[] arr = path.split(",");
        for(String s:arr){
            File file = new File(dir + "/" + s);
            file.createNewFile();
        }
    }


}
