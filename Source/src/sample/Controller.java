package sample;

import javafx.fxml.FXML;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class Controller
{
    @FXML
    private void initialize()
    {

    }

    @FXML
    private void openCSVFile()
    {
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
        }
    }

    @FXML
    private void exitApplication()
    {
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
}
