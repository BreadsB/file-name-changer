package com.breadsb.namechanger.helpers;

import javafx.scene.control.Alert;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

public class FileHelper {

    private final static String NEW_LINE = System.lineSeparator();

    public void renameFiles(List<File> fileList, int charNumbers, Path folderPath) {
        if(fileList.size()>0) {
            AlertHelper.showAlert(Alert.AlertType.INFORMATION, "List of files to convert", showFilesAsString(fileList));

            boolean done = false;
            String renamed = "";
            File renamedFile;

            for (File oldFile : fileList) {
                if (removeExtension(oldFile).length() < charNumbers) {
                    AlertHelper.showAlert(Alert.AlertType.ERROR,
                            "Error at converting " + oldFile.getName(),
                            "Characters to remove must be less than " + removeExtension(oldFile).length());
                    break;
                }
                renamed = oldFile.getName().substring(charNumbers);
                renamedFile = new File(folderPath.toString() + "\\" + renamed);
                done = oldFile.renameTo(renamedFile);
            }
        }   else {
            AlertHelper.showAlert(Alert.AlertType.WARNING, "List of files", "No files inside folder!");
        }
    }

    private String showFilesAsString(List<File> fileList) {
        StringBuilder sb = new StringBuilder();
        for (File file : fileList) {
            sb.append(file.getName()).append(NEW_LINE);
        }
        return sb.toString();
    }

    private String removeExtension(File file) {
        return file.getName().substring(0, file.getName().lastIndexOf("."));
    }
}
