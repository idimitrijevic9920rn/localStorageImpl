package model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.StandardCopyOption;

public class DownloadImpl {

    public DownloadImpl() {
    }

    public void download(String file) throws IOException {
        String[] parts = file.split("/");
        String target = ToolManager.getInstance().getDownloads();
        String dir = ToolManager.getInstance().getDirectory();
        File f = new File(ToolManager.getInstance().getDirectory() + "/" + file);
        File location = new File(ToolManager.getInstance().getDownloads() + "/" + f.getName());
        try {
            Files.copy(f.toPath(), location.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("file downloaded succefully");
        } catch (NoSuchFileException e) {
            System.out.println("file does not exists");
        }
    }

}
