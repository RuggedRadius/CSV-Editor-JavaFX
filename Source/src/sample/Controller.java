package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import javafx.scene.control.TableView;

import java.io.*;

public class Controller
{
    private TableView tblMain;
    private ObservableList<Row> data;


    @FXML
    private void initialize() {
    }

    @FXML
    private void openCSVFile() {
        // Create file chooser dialog
        JFileChooser fileChooser = new JFileChooser();
        File baseDirectory = new File(getClass().getProtectionDomain().getCodeSource().getLocation().getFile());
        fileChooser.setCurrentDirectory(baseDirectory);

        FileFilter filter = new FileNameExtensionFilter("*.csv | Comma Separated Values", "csv");
        fileChooser.setFileFilter(filter);

        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION)
        {
            // Get selected file
            File selectedFile = fileChooser.getSelectedFile();

            // TEMP
            System.out.println("Selected file: " + selectedFile.getName());

            // Load selected file
            loadCSVToTableView(selectedFile);
        }
    }

    @FXML
    private void exitApplication() {
        int result = JOptionPane.showConfirmDialog(
                null,
                "Are you sure you want to exit?",
                "Exit Confirmation",
                JOptionPane.YES_NO_CANCEL_OPTION
        );

        switch (result)
        {
            case JOptionPane.YES_OPTION:
                // Exit the application
                System.exit(0);
                break;

            default:
                break;
        }
    }

    private void loadCSVToTableView(File file)
    {
        // Clear TableView
        initialiseTableView();

        try (FileReader fr = new FileReader(file))
        {
            // Use 3rd party library to read .csv file into iterable data structure
//            Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(fr);
            Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(fr);


            // Print headers
//            String[] headers = records.iterator().next().toString().split(",");
//            for (String header: headers) {
//                addColumnToTable(header);
//            }


            // Declare new row
            Row newRow;

            // For each row..
            for (CSVRecord record : records)
            {
                // Create new row
                newRow = new Row(record.size());

                // For each cell in new row..
                for (int i = 0; i < record.size(); i++)
                {
                    // TEMP DEBUG ONLY
                    System.out.println(record.get(i));

                    // Print value to row cell
                    newRow.cells[i] = record.get(i);
                }

                // Add row to data
                data.add(newRow);
            }
        }
        catch (FileNotFoundException ex)
        {
            // Couldnt find given file
            System.err.println("Load Error: Could not find .csv to load. Load failed.");
        }
        catch (Exception ex)
        {
            System.err.println("Load Error: " + ex.getMessage());
        }
    }

    private void saveCSVToFile(File file) {
        // Maybe directory and string for file name instead?
        // We'll see
        // ...
    }

    private void initialiseTableView() {
        // Create if doesnt exist
        if (tblMain == null)
        {
            tblMain = new TableView();
        }

        // Set data
        data = FXCollections.observableArrayList();

        // Set data
        tblMain.setItems(data);

//
//        // Clear all data in the TableView
//        tblMain.getItems().clear();
    }


    // Table methods
    private void addColumnToTable(String column) {
        System.out.println("Adding header '" + column + "' to TableView.");
        TableColumn newColumn = new TableColumn(column);
        newColumn.setCellValueFactory(new PropertyValueFactory<>(column));
        tblMain.getColumns().add(newColumn);
    }

}
