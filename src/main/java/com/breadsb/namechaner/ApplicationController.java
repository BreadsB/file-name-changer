package com.breadsb.namechaner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ApplicationController implements Initializable {

    @FXML
    public TextField directoryLabel;
    @FXML
    public Spinner charNumberSpinner;
    public Button processButton;
    private final UsefulMethods usefulMethods = new UsefulMethods();
    private Path folderPath;
    Integer numberChars;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        charNumberSpinner.valueProperty().addListener(((observableValue, o, t1) -> onSpinnerValueChanged()));
    }

    @FXML
    private void onClickChooseDirectoryButton(ActionEvent event) {
        File file = usefulMethods.invokeDirectoryChooser(event);
        if (file != null) {
            directoryLabel.setText(file.getAbsolutePath());
            folderPath = Paths.get(file.getAbsolutePath());
            checkProcessButtonToEnable((int) charNumberSpinner.getValue());
        }
    }

    @FXML
    private void onSpinnerValueChanged() {
        checkProcessButtonToEnable((int) charNumberSpinner.getValue());
    }

    private void checkProcessButtonToEnable(int value) {
        if (!directoryLabel.getText().isBlank() && value>0) {
            processButton.setDisable(false);
            numberChars = value;
        }
    }

    @FXML
    private void onProcessButtonClicked() {
        List<File> fileList = new ArrayList<>();

        try (Stream<Path> paths = Files.walk(folderPath)) {
            fileList = paths.filter(Files::isRegularFile)
                    .map(path -> new File(path.toUri()))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            UsefulMethods.showAlert(Alert.AlertType.ERROR, "Error", "Wrong Path");
        }
        if (fileList.size()>0) {
            UsefulMethods.showAlert(Alert.AlertType.INFORMATION, "List of files to convert", usefulMethods.showFilesAsString(fileList));
            List<File> wrongFiles = usefulMethods.renameFiles(fileList, numberChars, folderPath);
            if (wrongFiles.size()>0) {
                UsefulMethods.showAlert(Alert.AlertType.WARNING, "Not done", wrongFiles.toString());
            }
            UsefulMethods.showAlert(Alert.AlertType.INFORMATION, "Converted files", usefulMethods.howManyConverted(fileList.size()));
        } else {
            UsefulMethods.showAlert(Alert.AlertType.WARNING, "List of files", "No files inside folder!");
        }
    }
}