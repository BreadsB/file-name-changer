package com.breadsb.namechanger;

import com.breadsb.namechanger.helpers.DirectoryChooserHelper;
import com.breadsb.namechanger.helpers.FileHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ResourceBundle;

public class ApplicationController implements Initializable {

    @FXML
    public Label directoryTextField;
    @FXML
    public Spinner charNumberSpinner;
    @FXML
    public Button processButton;
    @FXML
    public RadioButton leftSideRadio;
    @FXML
    public RadioButton rightSideRadio;

    private final DirectoryChooserHelper dch = new DirectoryChooserHelper();
    private final FileHelper fh = new FileHelper();
    public static int numberOfCharsToRemove = 18;
    public static Path folderPath;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        charNumberSpinner.valueProperty().addListener(((observableValue, o, t1) -> onSpinnerValueChanged()));
        charNumberSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, numberOfCharsToRemove));
    }

    @FXML
    private void onClickChooseDirectoryButton(ActionEvent event) {
        File file = dch.invokeDirectoryChooser(event);
        if (file != null) {
            directoryTextField.setText(file.getAbsolutePath());
            folderPath = Paths.get(file.getAbsolutePath());
            checkProcessButtonToEnable(numberOfCharsToRemove);
        }
    }

    @FXML
    private void onSpinnerValueChanged() {
        updateSpinner();
        checkProcessButtonToEnable(numberOfCharsToRemove);
    }

    @FXML
    private void onProcessButtonClicked() {
        List<File> fileList = dch.getAllRegularFilesFromDirectory(folderPath);
        boolean haveFileNamesSufficientLength = fh.checkFileNamesHaveSufficientLength(fileList, numberOfCharsToRemove);
        if (haveFileNamesSufficientLength) {
            boolean cuttingCharsFromLeft = cutCharsFromLeft();
            fh.renameFiles(fileList, numberOfCharsToRemove, folderPath, cuttingCharsFromLeft);
        }
    }

    private void checkProcessButtonToEnable(int value) {
        File file = new File(directoryTextField.getText());
        if (file.isDirectory() && value>0) {
            processButton.setDisable(false);
        }
    }

    private void updateSpinner() {
        numberOfCharsToRemove = (int) charNumberSpinner.getValue();
    }

    private boolean cutCharsFromLeft() {
        return leftSideRadio.isSelected();
    }
}