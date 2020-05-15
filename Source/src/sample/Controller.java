package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class Controller
{
    public TableView tblMain;
    private ObservableList<Row> data;
    private int columnLimits = 10; // Any increases to this require modifying the 'Row' class.
    private File currentFile;

    // General methods
    @FXML private void initialize() {}
    @FXML private void exitApplication() {
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
    @FXML private boolean launchHelp() {
        System.out.println("Launching help");

        // Get dynamic launch directory
        Path currentRelativePath = Paths.get("");
        String relPath = currentRelativePath.toAbsolutePath().toString() + "\\help\\index.html";

        // Get file from within launch directory
        File helpFile = new File(relPath);

        // Open the file in default browser
        try
        {
            Desktop.getDesktop().open(helpFile);
            return true;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    // Saving / Loading methods
    @FXML private void openCSVFile() {
        // Show dialog and get file
        File file = openFile();
        if (file != null)
        {
            String extension = "";

            int extIndex = file.getName().lastIndexOf('.');
            if (extIndex > 0)
            {
                extension = file.getName().substring(extIndex+1);
            }

            if (extension.equals("csv"))
            {
                // Load selected file
                loadCSVToTableView(file);
            }
            else
            {
                // Show error
                JOptionPane.showMessageDialog(null, "Unsupported file type. Only use .csv (Comma Separated Values) files.", "Unsupported file type", JOptionPane.ERROR_MESSAGE);
            }
        }
        else
        {
            System.out.println("No file opened");
        }
    }
    public File openFile() {
        // Create file chooser dialog
        FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("*.csv | Comma Separated Values", "csv");
        fileChooser.setSelectedExtensionFilter(filter);

        // Show dialog and get file
        return fileChooser.showOpenDialog(null);
    }
    @FXML private void saveCurrentCSVFile() {

        System.out.println("Saving file...");

        // Determine output file
        String outputFile = currentFile.getAbsolutePath();

        CSVPrinter csvFilePrinter = null;
        CSVFormat csvFileFormat = CSVFormat.EXCEL.withHeader();

        try
        {
            FileWriter fileWriter = new FileWriter(outputFile);
            csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);

            // Writing records
            for (Row row : data)
            {
                System.out.println(tblMain.getItems().get(0));


                String[] rowData = row.getAllColumns();
                for (int i = 0; i < columnLimits; i++)
                {

                    try
                    {
                        fileWriter.write(rowData[i]);
                    }
                    catch (Exception ex)
                    {
                        // No data to write to file, totally fine
                    }


                    if (i == rowData.length - 1)
                    {
                        fileWriter.write("\r\n");
                    }
                    else
                    {
                        fileWriter.write(",");
                    }

                    System.out.println("Writing record " + i);
                }
            }

            fileWriter.flush();
            fileWriter.close();
            csvFilePrinter.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    @FXML private void saveCSVToNewFile() {


        FileChooser fileChooser = new FileChooser();

        //Set extension filter for text files
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extFilter);

        //Show save file dialog
        File file = fileChooser.showSaveDialog(null);

        if (file != null)
        {

                // Use 3rd party library to write csv to file
                CSVPrinter csvFilePrinter = null;
                CSVFormat csvFileFormat = CSVFormat.EXCEL.withHeader();

                try {
                    // Assign components
                    FileWriter fileWriter = new FileWriter(file);
                    csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);

                    // Writing records
                    for (Row row : data) {
                        // Get row data into string array
                        String[] rowData = row.getAllColumns();

                        // For each column in row...
                        for (int i = 0; i < columnLimits; i++) {
                            try {
                                // Write to file
                                fileWriter.write(rowData[i]);
                            } catch (Exception ex) {
                                // No data to write to file, totally fine
                            }

                            // Determine cell ending to write
                            if (i == rowData.length - 1) {
                                // End of row
                                fileWriter.write("\r\n");
                            } else {
                                // Not the end of the row
                                fileWriter.write(",");
                            }
                        }
                    }

                    // Flush and close all the shiznit
                    fileWriter.flush();
                    fileWriter.close();
                    csvFilePrinter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

        }
    }
    @FXML private void loadCSVToTableView(File file) {

        // Clear table view
        initialiseTableView();


        try
        {
            // Read .csv file using 3rd party library
            Reader reader = Files.newBufferedReader(Paths.get(file.getAbsolutePath()));
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);

            for (CSVRecord record : csvParser)
            {
                // Create Row
                Row myRow = new Row();
                try
                {
                    System.out.println("Starting column updating..");

                    // Get values from record
                    String[] newData = new String[columnLimits];
                    for (int i = 0; i < newData.length; i++)
                    {
                        // Get value for record for cell
                        newData[i] = record.get(i);

                        // Update values in row object, overkill but otherwise only full rows will update, ****ing java.
                        myRow.updateColumns(newData);
                    }
                    System.out.println("Ended column updating!");
                }
                catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
                // Add row to data
                data.add(myRow);
            }
        }
        catch (Exception ex) {
            System.err.println("Error: " + ex.getLocalizedMessage());
        }

        tblMain.setItems(data);
    }

    // Table methods
    @FXML private void initialiseTableView() {
        // Clear TableView
        tblMain.getColumns().clear();
        tblMain.getItems().clear();

        // Create the columns...
        createColumns(columnLimits);

        // Assign new data
        data = FXCollections.observableArrayList();
        tblMain.setItems(data);

        // Make editable
        tblMain.setEditable(true);
    }
    @FXML private boolean addRowToTable() {
        if (data == null)
            initialiseTableView();

        // Create Row
        Row myRow = new Row();

        // Add row to data
        data.add(myRow);

        return true;
    }
    public boolean createColumns(int cols) {

        int startValue = 65;
        for (int i = 0; i < cols; i++) {
            addColumnToTable(Character.toString(startValue++));
        }
        return true;
    }
    private boolean addColumnToTable(String column) {
        System.out.println("Adding header '" + column + "' to TableView.");

        // Create column
        TableColumn newColumn = new TableColumn(column);

        // Set cell factory
        newColumn.setCellValueFactory(new PropertyValueFactory<>("col" + column.toUpperCase()));
        newColumn.setCellFactory(TextFieldTableCell.forTableColumn());



        //(TableColumn.CellEditEvent<Row, String> event)
        // On Cell edit commit
        newColumn.setOnEditCommit((new EventHandler<TableColumn.CellEditEvent>() {
            @Override
            public void handle(TableColumn.CellEditEvent cellEditEvent) {
                // Get new value
                Object newValue = cellEditEvent.getNewValue();

                // Get row
                TablePosition<Row, String> pos = cellEditEvent.getTablePosition();
                int rowNumber = pos.getRow();
                Object myRow = cellEditEvent.getTableView().getItems().get(rowNumber);

                // Get column
                TableColumn col = cellEditEvent.getTableColumn();

                // Update data
                updateData((Row) myRow, tblMain.getColumns().indexOf(col), (String) newValue);
            }
        }));

        // Add column to TableView
        tblMain.getColumns().add(newColumn);

        return true;
    }
    private void updateData(Row myRow, int columnNumber, String newValue) {
        int colAsciiCode = columnNumber + 65;

        // Update according column
        switch (colAsciiCode)
        {
            case 65:
                myRow.setColA(newValue);
                break;
            case 66:
                myRow.setColB(newValue);
                break;
            case 67:
                myRow.setColC(newValue);
                break;
            case 68:
                myRow.setColD(newValue);
                break;
            case 69:
                myRow.setColE(newValue);
                break;
            case 70:
                myRow.setColF(newValue);
                break;
            case 71:
                myRow.setColG(newValue);
                break;
            case 72:
                myRow.setColH(newValue);
                break;
            case 73:
                myRow.setColI(newValue);
                break;
            case 74:
                myRow.setColJ(newValue);
                break;
//            case 75:
//                myRow.setColK(newValue);
//                break;
//            case 76:
//                myRow.setColL(newValue);
//                break;
//            case 77:
//                myRow.setColM(newValue);
//                break;
//            case 78:
//                myRow.setColN(newValue);
//                break;
//            case 79:
//                myRow.setColO(newValue);
//                break;
//            case 80:
//                myRow.setColP(newValue);
//                break;
//            case 81:
//                myRow.setColQ(newValue);
//                break;
//            case 82:
//                myRow.setColR(newValue);
//                break;
//            case 83:
//                myRow.setColS(newValue);
//                break;
//            case 84:
//                myRow.setColT(newValue);
//                break;
//            case 85:
//                myRow.setColU(newValue);
//                break;
//            case 86:
//                myRow.setColV(newValue);
//                break;
//            case 87:
//                myRow.setColW(newValue);
//                break;
//            case 88:
//                myRow.setColX(newValue);
//                break;
//            case 89:
//                myRow.setColY(newValue);
//                break;
//            case 90:
//                myRow.setColZ(newValue);
//                break;
            default:
                break;
        }
    }


    // JUnit Testing
    @Test
    public void testJUnitMethod() {
        boolean test = true;
        assertEquals(test, true);
    }

    @Test
    public void testHelp() {
        assertEquals(launchHelp(), true);
    }
}
