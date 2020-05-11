package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
//import com.gembox.spreadsheet.*;

public class Controller
{
    public TableView tblMain;
//    private ObservableList<String[]> data;


    @FXML
    private void initialize() {
//        tblMain.setItems(data);

        TESTLOAD();
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
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());

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

    private void TESTLOAD()
    {
        File testFile = new File("C:\\Users\\Ben\\Documents\\GitHub\\JavaIII_AT2.6\\Source\\out\\production\\Source\\Test.csv");
        loadCSVToTableView(testFile);
    }

    @FXML
    private void loadCSVToTableView(File file)
    {
        // Clear table view
//        initialiseTableView();

        try
        {
            // Read .csv file
            Reader reader = Files.newBufferedReader(Paths.get(file.getAbsolutePath()));
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);

            // Adds a column for each header (first row elements)
            CSVRecord headers = csvParser.iterator().next();
            for (int i = 0; i < headers.size(); i++) {
                addColumnToTable(headers.get(i));
            }

            // Get rows
            ArrayList<String[]> rows = new ArrayList<>();
            for (CSVRecord record : csvParser)
            {
                // Create String array
                String[] newRow = new String[record.size()];
                for (int i = 0; i < record.size(); i++)
                {
                    // Add cell contents to String array
                    newRow[i] = record.get(i);
                    System.out.println(record.get(i));
                }

                // Add String array to list of rows
                rows.add(newRow);
            }

            //  which will make your table view dynamic
            ObservableList<ObservableList> csvData = FXCollections.observableArrayList();

            for(String[] rowData : rows)
            {
                ObservableList<String> row = FXCollections.observableArrayList();

                for(String cellData : rowData)
                {
                    row.add(cellData);
                }

                // add each row to cvsData
                csvData.add(row);
            }

            // finally add data to tableview
            tblMain.setItems(csvData);
        }
        catch (Exception ex)
        {
            System.err.println("Error: " + ex.getLocalizedMessage());
        }
    }

    private void saveCSVToFile(File file) {
        // Maybe directory and string for file name instead?
        // We'll see
        // ...
    }

    public static TableView createTableView(String[] headers)
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


    // Table methods
    private void addColumnToTable(String column) {
        System.out.println("Adding header '" + column + "' to TableView.");
        TableColumn newColumn = new TableColumn(column);
//        newColumn.setCellValueFactory(new PropertyValueFactory<>(column));
        tblMain.getColumns().add(newColumn);
    }

    @FXML
    private void initialiseTableView()
    {
//        tblMain.getColumns().clear();
//        tblMain.getItems().clear();
    }

}
