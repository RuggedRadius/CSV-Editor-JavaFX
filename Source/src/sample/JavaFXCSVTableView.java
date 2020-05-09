//package sample;
//
//import java.io.*;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javafx.application.Application;
//import javafx.beans.property.SimpleStringProperty;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.scene.Group;
//import javafx.scene.Scene;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
//import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.scene.layout.VBox;
//import javafx.stage.Stage;
//
//import javax.swing.*;
//import javax.swing.filechooser.FileFilter;
//import javax.swing.filechooser.FileNameExtensionFilter;
//
///**
// * @web http://java-buddy.blogspot.com/
// */
//public class JavaFXCSVTableView extends Application {
//
//    public class Record {
//        //Assume each record have 6 elements, all String
//
//        private SimpleStringProperty f1, f2, f3, f4, f5, f6;
//
//        public String getF1() {
//            return f1.get();
//        }
//
//        public String getF2() {
//            return f2.get();
//        }
//
//        public String getF3() {
//            return f3.get();
//        }
//
//        public String getF4() {
//            return f4.get();
//        }
//
//        public String getF5() {
//            return f5.get();
//        }
//
//        public String getF6() {
//            return f6.get();
//        }
//
//        Record(String f1, String f2, String f3) {
//            this.f1 = new SimpleStringProperty(f1);
//            this.f2 = new SimpleStringProperty(f2);
//            this.f3 = new SimpleStringProperty(f3);
////            this.f4 = new SimpleStringProperty(f4);
////            this.f5 = new SimpleStringProperty(f5);
////            this.f6 = new SimpleStringProperty(f6);
//        }
//
//    }
//
//    private final TableView<Record> tableView = new TableView<>();
//
//
//
//    @Override
//    public void start(Stage primaryStage) {
//        primaryStage.setTitle("java-buddy.blogspot.com");
//
//        Group root = new Group();
//
//        TableColumn columnF1 = new TableColumn("f1");
//        columnF1.setCellValueFactory(new PropertyValueFactory<>("f1"));
//
//        TableColumn columnF2 = new TableColumn("f2");
//        columnF2.setCellValueFactory(new PropertyValueFactory<>("f2"));
//
//        TableColumn columnF3 = new TableColumn("f3");
//        columnF3.setCellValueFactory(new PropertyValueFactory<>("f3"));
////
////        TableColumn columnF4 = new TableColumn("f4");
////        columnF4.setCellValueFactory(
////                new PropertyValueFactory<>("f4"));
////
////        TableColumn columnF5 = new TableColumn("f5");
////        columnF5.setCellValueFactory(
////                new PropertyValueFactory<>("f5"));
////
////        TableColumn columnF6 = new TableColumn("f6");
////        columnF6.setCellValueFactory(
////                new PropertyValueFactory<>("f6"));
//
//        tableView.setItems(data);
//        tableView.getColumns().addAll(columnF1, columnF2, columnF3);
//
//        VBox vBox = new VBox();
//        vBox.setSpacing(10);
//        vBox.getChildren().add(tableView);
//
//        root.getChildren().add(vBox);
//
//        primaryStage.setScene(new Scene(root, 700, 250));
//        primaryStage.show();
//
//        openCSVFile();
//    }
//
//    private void openCSVFile() {
//        // Create file chooser dialog
//        JFileChooser fileChooser = new JFileChooser();
//        File baseDirectory = new File(getClass().getProtectionDomain().getCodeSource().getLocation().getFile());
//        fileChooser.setCurrentDirectory(baseDirectory);
//
//        FileFilter filter = new FileNameExtensionFilter("*.csv | Comma Separated Values", "csv");
//        fileChooser.setFileFilter(filter);
//
//        int result = fileChooser.showOpenDialog(null);
//        if (result == JFileChooser.APPROVE_OPTION)
//        {
//            // Get selected file
//            File selectedFile = fileChooser.getSelectedFile();
//
//            // TEMP
//            System.out.println("Selected file: " + selectedFile.getName());
//
//            // Load selected file
//            readCSV(selectedFile);
//        }
//    }
//
//    private void readCSV(File file) {
//
//        String CsvFile = "Test.csv";
//        String FieldDelimiter = ",";
//
//        BufferedReader br;
//
//        try {
//            br = new BufferedReader(new FileReader(file));
//
//            String line;
//            while ((line = br.readLine()) != null)
//            {
//                String[] fields = line.split(FieldDelimiter, -1);
//
//
//                Record record = new Record(fields[0], fields[1], fields[2]);
//                data.add(record);
//
//            }
//
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(JavaFXCSVTableView.class.getName())
//                    .log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(JavaFXCSVTableView.class.getName())
//                    .log(Level.SEVERE, null, ex);
//        }
//
//    }
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//
//}