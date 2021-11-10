import model.ToolManager;
import repository.RepositoryImp;
import user.User;

import java.io.IOException;
import java.util.ArrayList;


public class Main {

    public static void main(String[] args) {

        RepositoryImp repositoryImp = new RepositoryImp();

        repositoryImp.logIn("user1","user1");
        repositoryImp.logIn("user2","user2");
        repositoryImp.logIn("user3","user3");



//        repositoryImp.deleteFile("f23",2);

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

//          repositoryImp.viewFile("32");
//        repositoryImp.moveFile("sad.txt", "asd");

        }

}
