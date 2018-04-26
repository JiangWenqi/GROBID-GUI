package org.grobid.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import org.grobid.GrobidAplication;
import org.grobid.core.exceptions.GrobidResourceException;

import java.io.File;
import org.grobid.Chinese.MyGrobid;


public class GrobidController {
    @FXML
    public TextField inputPDF;
    @FXML
    public TextArea outputTEL;

    public void initialize() {


    }

//    public void initialize(URL location, Resources resources) {
//    }


    public void openFile(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("."));
        fileChooser.setTitle("选择文件");
        File file = fileChooser.showOpenDialog(GrobidAplication.getPrimaryStage());
        try {
            if (file.exists()) {
                inputPDF.setText(file.getAbsolutePath());
                outputTEL.textProperty().setValue("file size: " + file.length());
            }
        } catch (GrobidResourceException e) {

            e.printStackTrace();
        }
    }

    public void processPDF(ActionEvent actionEvent) {
        String pdf = inputPDF.getText();
        outputTEL.textProperty().setValue(MyGrobid.runGrobid(pdf));
    }
}
