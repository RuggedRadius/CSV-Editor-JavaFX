package sample;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Material;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.stage.Stage;


public class Main extends Application
{
    @FXML private TableView tblMain;
    @FXML public AnchorPane apMenu;
    @FXML public MenuBar menuBar;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("JMC CSV Editor");

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/sample/styles.css");
        scene.getStylesheets().add("https://fonts.googleapis.com/css2?family=DM+Mono:wght@300&display=swap");
        scene.getStylesheets().add("https://fonts.googleapis.com/css2?family=Teko:wght@300&display=swap");

        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setMaximized(true);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
