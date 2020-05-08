package sample;

import com.opencsv.CSVReaderHeaderAware;
import javafx.fxml.FXML;
import javafx.scene.control.TableRow;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.TableView;
import java.io.*;
import java.util.Map;
import java.util.Scanner;

public class Controller
{
    private TableView tblMain;

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




        try (FileReader fr = new FileReader(file))
        {
            // Use 3rd party library to read .csv file into iterable data structure
            Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(fr);

            // Print headers
            // ...

            TableRow newRow = new TableRow();

            // For each row..
            for (CSVRecord record : records)
            {
                // For each cell
                for (int i = 0; i < record.size(); i++)
                {
                    // TEMP DEBUG ONLY
                    System.out.println(record.get(i));

                    // Add value to new TableView row
                    // ...
                }

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

    private void saveCSVToFile(File file)
    {
        // Maybe directory and string for file name instead?
        // We'll see
        // ...
    }

    private void initialiseTableView()
    {
        // Clear all data in the TableView

    }

}
