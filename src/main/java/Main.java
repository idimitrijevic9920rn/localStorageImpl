import model.ToolManager;
import repository.RepositoryImp;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;


public class Main {

    public static void main(String[] args) {

        RepositoryImp repositoryImp = new RepositoryImp();



//        repositoryImp.createStorage("folder1/ff3f,folder5/n3n",1);

//          repositoryImp.createFile("folder3/dds.txt", 0);
//        repositoryImp.createStorage("folder3/f1,folder3/f2",1);
//
//        repositoryImp.createFile("folder1/nekifajl1.jpg",0);
//        System.out.println(repositoryImp.viewFile( null,6, "ey.txt"));
//

        repositoryImp.createConfig(Arrays.asList("folder1","folder2"),Arrays.asList(1,2), Arrays.asList("exe"),Arrays.asList(1500,600));


//        System.out.println(ToolManager.getInstance().getDirectory());
//
//        System.out.println(ToolManager.getInstance().getDirectory());
//
//
//
//
//        repositoryImp.deleteFile("folder1 copy",1);
//
//        repositoryImp.createStorage("asd,32,f23,23sa", 1);
//        repositoryImp.createStorage("323/asd", 0);
//
//        repositoryImp.createFile("32/mm.txt,32/ff.txt", 1);
//
//        repositoryImp.createFile("32/asd/mene.jpg",0);
//
//
//        repositoryImp.createFile("folder1/mct.io",0);
//
//
//
//        repositoryImp.downloadFile("32");
//
//          repositoryImp.rename("32/e", "k");
//
//        repositoryImp.moveFile("folder1/mene.jpg", "32/asd");
//
//        repositoryImp.moveFile("sad.txt", "asd");

        }




}
