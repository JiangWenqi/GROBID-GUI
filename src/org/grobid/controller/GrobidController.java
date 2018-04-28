package org.grobid.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.DirectoryChooser;
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
    @FXML
    public TextField inputDir;
    @FXML
    public TextField outputDir;
    @FXML
    public RadioButton consolidateHeader;
    @FXML
    public RadioButton consolidateCitations;


    public void initialize() {
        new MyGrobid("/Users/vinci/lib/grobid/grobid-home");

    }

//    public void initialize(URL location, Resources resources) {
//    }


    public void openFile(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("."));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PDF", "*.pdf")

        );
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


    public void processPDFHedaer(ActionEvent actionEvent) {
        String pdf = inputPDF.getText();
        outputTEL.textProperty().setValue(MyGrobid.runGrobid(pdf));
    }


    public void processPDFDir(ActionEvent actionEvent) {
        String pdfDir = inputDir.getText();
        String outputPath = outputDir.getText();
        boolean withHeader = consolidateHeader.isSelected();
        boolean withCitation = consolidateCitations.isSelected();

        MyGrobid.runGrobid(pdfDir, outputPath, withHeader, withCitation);


    }

    public void openInputDir(ActionEvent actionEvent) {

        DirectoryChooser dirChooser = new DirectoryChooser();
        dirChooser.setTitle("选择目录");
        dirChooser.setInitialDirectory(new File("."));
        File pdfDir = dirChooser.showDialog(GrobidAplication.getPrimaryStage());
        try {
            if (pdfDir.exists()) {
                inputDir.setText(pdfDir.getAbsolutePath());
            }
        } catch (GrobidResourceException e) {
            e.printStackTrace();
        }
    }

    public void openOutputDir(ActionEvent actionEvent) {
        DirectoryChooser dirChooser = new DirectoryChooser();
        dirChooser.setTitle("选择目录");
        dirChooser.setInitialDirectory(new File("."));
        File pdfDir = dirChooser.showDialog(GrobidAplication.getPrimaryStage());
        try {
            if (pdfDir.exists()) {
                outputDir.setText(pdfDir.getAbsolutePath());
            }
        } catch (GrobidResourceException e) {
            e.printStackTrace();
        }
    }

}
