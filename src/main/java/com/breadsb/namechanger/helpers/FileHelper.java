package com.breadsb.namechanger.helpers;

import javafx.scene.control.Alert;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

public class FileHelper {

    private final static String NEW_LINE = System.lineSeparator();

    public void renameFiles(List<File> fileList, int charNumbers, Path folderPath, boolean leftSide) {
        System.out.println("Cutting chars from left: " + leftSide);
        String cuttedName;
        String extension;
        boolean done;
        if (leftSide) {
            for (File file : fileList) {
                cuttedName = file.getName().substring(charNumbers);
                done = file.renameTo(new File(folderPath+"\\"+cuttedName));
            }
        } else {
            for (File file : fileList) {
                cuttedName = file.getName().substring(0, (removeExtension(file).length()-charNumbers) );
                extension = file.getName().substring(file.getName().lastIndexOf("."));
                done = file.renameTo(new File(folderPath+"\\"+cuttedName+extension));
            }
        }
    }

    private String showFilesAsString(List<File> fileList) {
        StringBuilder sb = new StringBuilder();
        for (File file : fileList) {
            sb.append(file.getName()).append(NEW_LINE);
        }
        return sb.toString();
    }

    public String removeExtension(File file) {
        return file.getName().substring(0, file.getName().lastIndexOf("."));
    }

    public boolean checkFileNamesHaveSufficientLength(List<File> oldFileList, int charNumberToRemove) {
        if (oldFileList.size()==1) {
            AlertHelper.showAlert(Alert.AlertType.WARNING, "List of files", "No files inside folder!");
            return false;
        } else {
            for (File oldFile : oldFileList) {
                if (removeExtension(oldFile).length() < charNumberToRemove) {
                    AlertHelper.showAlert(Alert.AlertType.ERROR,
                            "Error at converting " + oldFile.getName(),
                            "Characters to remove must be less than " + removeExtension(oldFile).length());
                    return false;
                }
            }
            return true;
        }
    }
}