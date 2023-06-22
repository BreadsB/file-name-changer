package com.breadsb.namechaner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class ApplicationController implements Initializable {

    @FXML
    public TextField directoryLabel;
    @FXML
    public Spinner charNumberSpinner;
    public Button processButton;
    Integer numberOfChars;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        charNumberSpinner.valueProperty().addListener(((observableValue, o, t1) -> onSpinnerValueChanged()));
    }

    @FXML
    private void onClickChooseDirectoryButton(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        final DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select directory");
        directoryChooser.setInitialDirectory(new File("C:\\Users\\Public"));
        File file = directoryChooser.showDialog(stage);
        directoryLabel.setText(file.getAbsolutePath());
        checkProcessButtonToEnable();
    }

    @FXML
    private void onSpinnerValueChanged() {
        numberOfChars = (int) charNumberSpinner.getValue();
        checkProcessButtonToEnable();
    }

    private void checkProcessButtonToEnable() {
        if (!directoryLabel.getText().isBlank() && numberOfChars!=null) {
            processButton.setDisable(false);
        }
    }

    @FXML
    private void onProcessButtonClicked() {
//        get All Files as List
//        Change all files name
    }
}