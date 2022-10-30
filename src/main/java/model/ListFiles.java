package model;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;


public class ListFiles {

    public ListFiles() {
    }

    public List<File> listTxtFilesFromDirectory(String str){

       List<File> files = new ArrayList<>();

        File file = new File(ToolManager.getInstance().getDirectory() + "/" + str);
        for(File f:file.listFiles()){
            String extension = "";

            int i = f.getName().lastIndexOf('.');
            if (i > 0) {
                extension = f.getName().substring(i+1);
                if(extension.equals("txt")){
                    files.add(f);
                }
            }
        }

        ToolManager.getInstance().setCurrentFileList(files);

        return files;
    }

    public List<File> listAllFilesFromDirectoryAndSubdirectories(String str){

        List<File> files = new ArrayList<>();

        File f = new File(ToolManager.getInstance().getDirectory() + "/" + str);

        try {
            Files.walk(f.toPath()).sorted(Comparator.reverseOrder()).forEach(path -> {
                File file = new File(path.toString());
                if(file.isFile()){
                    files.add(file);
                }
            });
        }catch (IOException e){
            System.out.println("folder not found");
        }

        ToolManager.getInstance().setCurrentFileList(files);

        return files;
    }

    public List<File> listAllFilesWithExtension(String ext){
        List<File> files = new ArrayList<>();

        File root = new File(ToolManager.getInstance().getDirectory());

        try {
            Files.walk(root.toPath()).sorted(Comparator.reverseOrder()).forEach(path -> {
                File file = new File(path.toString());
                String extension = "";

                int i = file.getName().lastIndexOf('.');
                if (i > 0) {
                    extension = file.getName().substring(i+1);
                    if(extension.equals(ext)){
                        files.add(file);
                    }
                }
            });
        }catch (IOException e){
            System.out.println("folder not found");
        }

        ToolManager.getInstance().setCurrentFileList(files);

        return files;

    }

    public List<File> listAllFilesWithSubstring(String sub){

        List<File> files = new ArrayList<>();

        File root = new File(ToolManager.getInstance().getDirectory());

        try {
            Files.walk(root.toPath()).sorted(Comparator.reverseOrder()).forEach(path -> {
                File file = new File(path.toString());

                if(file.isFile() && file.getName().contains(sub)){
                    files.add(file);
                }
            });
        }catch (IOException e){
            System.out.println("folder not found");
        }

        ToolManager.getInstance().setCurrentFileList(files);

        return files;

    }

    public List<File> getFileFolder(String fileName){

        List<File> files = new ArrayList<>();

        File root = new File(ToolManager.getInstance().getDirectory());

        try {
            Files.walk(root.toPath()).sorted(Comparator.reverseOrder()).forEach(path -> {
                File file = new File(path.toString());

                if(file.isFile() && file.getName().equals(fileName)){
                   files.add(file.getParentFile());
                }
            });
        }catch (IOException e){
            System.out.println("folder not found");
        }

        ToolManager.getInstance().setCurrentFileList(files);

        return files;
    }

    public List<File> getSortedFiles(String value, String param){


        File root = new File(ToolManager.getInstance().getDirectory());
        List<File> files = new ArrayList<>();
        int i = 0;

        try {
            Files.walk(root.toPath()).sorted(Comparator.reverseOrder()).forEach(path -> {
                File file = new File(path.toString());
                files.add(file);
            });
        }catch (IOException e){
            System.out.println("folder not found");
        }

        File[] fileArray = new File[files.size()];

        for(File f:files){
            fileArray[i++] = f;
        }


        if(value.equals("modified")){
            Arrays.sort(fileArray, Comparator.comparingLong(File::lastModified).reversed());
            if(param.equals("desc")){
                ToolManager.getInstance().setCurrentFileList(Arrays.asList(fileArray));
                return Arrays.asList(fileArray);

            }
            if(param.equals("asc")){
                Arrays.sort(fileArray, Comparator.comparingLong(File::lastModified).reversed());
                ToolManager.getInstance().setCurrentFileList(Arrays.asList(fileArray));
                return Arrays.asList(fileArray);
            }
        }

        if(value.equals("created")){
            if(param.equals("desc")) {
                ToolManager.getInstance().setCurrentFileList(Arrays.asList(fileArray));
                return Arrays.asList(this.sortByDateCreated(fileArray));
            }
            if(param.equals("asc")){
                ToolManager.getInstance().setCurrentFileList(Arrays.asList(fileArray));
                return Arrays.asList(this.reverseList(sortByDateCreated(fileArray)));
            }
        }

        return null;

    }

    private File[] sortByDateCreated(File[] files){

        Comparator<File> comparator = Comparator.comparing(file -> {
            try {
                return Files.readAttributes(Paths.get(file.toURI()), BasicFileAttributes.class).creationTime();
            } catch (IOException e) {
                return null;
            }
        });

        Arrays.sort(files, comparator);

        return files;
    }

    private File[] reverseList(File[] files){

            List<File> list = Arrays.asList(files);
            Collections.reverse(list);
            File[] reversedArray = list.toArray(files);
            return reversedArray;
    }

    public List<File> getCreatedAndModifiedFiles(String dir, List<String> values) {

        File root = new File(ToolManager.getInstance().getDirectory() + "/" + dir);
        List<File> files = new ArrayList<>();
        Date d1 = null;
        Date d2 = null;

        try {
            d1 = new SimpleDateFormat("dd/MM/yyyy").parse(values.get(0));
            d2 = new SimpleDateFormat("dd/MM/yyyy").parse(values.get(1));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        try {
            Date finalD1 = d1;
            Date finalD2 = d2;

            Files.walk(root.toPath()).sorted(Comparator.reverseOrder()).forEach(path -> {
                File file = new File(path.toString());

                Date dateTime = new Date( file.lastModified());

                if(file.isFile() && dateTime.after(finalD1) && dateTime.before(finalD2)){
                   files.add(file);
                }

            });
        }catch (IOException e){
            System.out.println("folder not found");
        }

        ToolManager.getInstance().setCurrentFileList(files);
        return files;
    }



}
