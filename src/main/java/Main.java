import model.ToolManager;
import repository.RepositoryImp;
import user.User;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;


public class Main {

    public static void main(String[] args) {

        RepositoryImp repositoryImp = new RepositoryImp();

//        repositoryImp.logIn("admin","admin");

//        repositoryImp.createUser("admin","admin","aaa");
//        repositoryImp.logIn("admin1","admin");
//        repositoryImp.moveFile("fajl1/321","dir");
//        repositoryImp.initialiseDirectory("/Users/macbookpro/Downloads/dir1","admin4","admin");
        repositoryImp.changeDir("/Users/macbookpro/Downloads/dir1","admin4","admin");
//        System.out.println(ToolManager.getInstance().getJSONFilePath());

        repositoryImp.logIn("nekiUser1","123");
        repositoryImp.createFile("ee.exe",0);
//        repositoryImp.viewFile("folder2",8);


        //        repositoryImp.logIn("user1","user1");
//        repositoryImp.logOut();
//        repositoryImp.logIn("admin4","admin");
//        repositoryImp.createConfig(15000,Arrays.asList("folder1","folder2","folder3"),Arrays.asList(3,4,5),Arrays.asList("exe","txt"));

//        repositoryImp.changeDir("/Users/macbookpro/Downloads/dir3","admin4","admin");
//        repositoryImp.createStorage("folder1",0);
//        repositoryImp.createFile("folder1/nekifajl1.jpg",0);
//        repositoryImp.viewFile("folder1",8);
//        repositoryImp.logOut();
//        repositoryImp.createUser("us1","us","delete,create");
//        repositoryImp.logOut();
//        repositoryImp.logOut();
//        repositoryImp.logIn("admin1","admin");
//        repositoryImp.createUser("us123","us","delete,create");


//        repositoryImp.createConfig(10, Arrays.asList("folder1","folder2"),Arrays.asList(1,2), Arrays.asList("exe"));


//        System.out.println(ToolManager.getInstance().getDirectory());
//        System.out.println(ToolManager.getInstance().getJSONFilePath());
//
//        System.out.println("aa");
//        repositoryImp.logOut();
//        System.out.println(ToolManager.getInstance().getDirectory());
//        System.out.println(ToolManager.getInstance().getJSONFilePath());
//
//        repositoryImp.changeDir("/Users/macbookpro/Downloads/dir556","admin4","admin");
//        System.out.println(ToolManager.getInstance().getDirectory());
//        System.out.println(ToolManager.getInstance().getJSONFilePath());
//        repositoryImp.logIn("user1","user1");

//        repositoryImp.viewFile("32");



//        ToolManager.getInstance().getPrivilegeLevel().forEach(System.out::println);

//        repositoryImp.createUser("ne","da","create");
//        repositoryImp.logOut();
//        repositoryImp.logIn("ne","da");
//        ToolManager.getInstance().getPrivilegeLevel().forEach(System.out::println);



//        repositoryImp.logIn("user3","user3");

//
//
//        repositoryImp.logOut();
//        repositoryImp.logIn("aca","st");

//        repositoryImp.deleteFile("directory/fajl1,directory/fajl2,directory/fajl3",2);

//        repositoryImp.createFile("sadas.txt,dsadas.txt,dasd,sad.txt", 1);
//        repositoryImp.createStorage("asd,32,f23,23sa", 1);
//        repositoryImp.createFile("sadas.txt,dsadas.txt,dasd,sad.txt", 1);
//        repositoryImp.createStorage("32/asd,32/sds", 1);
//        repositoryImp.createFile("32/sadas.txt,32/dsadas.txt", 1);
//        repositoryImp.createFile("32/mm.txt,32/ff.txt", 1);
//        repositoryImp.createFile("32/mene.txt",1);


//        repositoryImp.createFile("user.json",0);



//        repositoryImp.downloadFile("32");


//        repositoryImp.moveFile("32", "asd");

//        repositoryImp.moveFile("sad.txt", "asd");

        }




}
