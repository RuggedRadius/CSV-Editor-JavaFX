package sample;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;


public class Main extends Application
{
    @FXML private TableView tblMain;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("JMC CSV Editor");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }



    public TableView createTableView(String[] headers)
    {
        // Create new TableView
        TableView tbl = new TableView();
        tbl.setEditable(true);

        // Create Columns
        for (int i = 0; i < headers.length; i++) {
            TableColumn newColumn = new TableColumn(headers[i]);
            tbl.getColumns().add(newColumn);
        }

        return tbl;
    }




    private MenuBar createMenuBar() {
        // Create bar
        MenuBar menuBar = new MenuBar();

        // Create FILE menu
        Menu menuFile = createFileMenu();
        Menu menuHelp = createHelpMenu();

        // Add menu(s) to menu bar
        menuBar.getMenus().addAll(menuFile, menuHelp);

        return menuBar;
    }
    private Menu createFileMenu() {
        Menu file = new Menu();
        file.setText("File");

        // Populate FILE menu
        MenuItem itmNew = new MenuItem("New");
        file.getItems().add(itmNew);

        file.getItems().add(new SeparatorMenuItem());

        MenuItem itmLoad = new MenuItem("Load...");
        file.getItems().add(itmLoad);

        file.getItems().add(new SeparatorMenuItem());

        MenuItem itmSave = new MenuItem("Save");
        file.getItems().add(itmSave);
        MenuItem itmSaveAs = new MenuItem("Save As..");
        file.getItems().add(itmSaveAs);

        file.getItems().add(new SeparatorMenuItem());
        MenuItem itmExit = new MenuItem("Exit");
        file.getItems().add(itmExit);

        return file;
    }
    private Menu createHelpMenu() {
        Menu help = new Menu();
        help.setText("Help");

        // Populate FILE menu
        MenuItem itmNew = new MenuItem("Help");
        help.getItems().add(itmNew);

        MenuItem itmLoad = new MenuItem("About");
        help.getItems().add(itmLoad);

        return help;
    }
}
