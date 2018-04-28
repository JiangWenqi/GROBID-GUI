package org.grobid;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;


public class GrobidAplication extends Application {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(GrobidAplication.class);

    private static Stage primaryStage;
    private BorderPane rootLayout;


    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        primaryStage.setTitle("中文论文逻辑结构抽取及训练系统");
        initRootLayout();
        showGrobidLayout();
    }

    /**
     * Initializes the root layout
     */

    private void initRootLayout() {
        try {
            // Load root layout from fxml file
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(GrobidAplication.class.getResource("view/RootLayout.fxml"));
            rootLayout = loader.load();
            // Show the scene containing the root layout
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Shows the Grobid  inside the root layout.
     */
    public void showGrobidLayout() {
        try {
            // Load Grobid Layout
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(GrobidAplication.class.getResource("view/GrobidLayout.fxml"));
            VBox personOverview = loader.load();

            // Set grobid layout into the center of root layout.
            rootLayout.setCenter(personOverview);
        } catch (IOException e) {
            LOGGER.error("目录有误");
            e.printStackTrace();
        }
    }



    /**
     * Returns the main stage.
     *
     * @return
     */
    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }

}
