package fr.istic.aco.editor;

import fr.istic.aco.editor.commands.Command;

import java.io.InputStream;

/**
 * Main API for the text editing interface
 *
 * @author tcarrasco
 * @version 1.0
 */

public interface UserInterface {

    /**
     * The main loop for the text editor that takes commands or texts
     */
    void runInvokerLoop();

    /**
     * Stop the main loop in runIvokerLoop and quit the application
     */
    //void stopLoop();

    /**
     * Set the inputStream and connect to the bufferReader
     * @param inputStream the InputStream to
     */
    void setReadStream(InputStream inputStream);

    /**
     * Add a Command in a hashmap
     * @param keyword name of the command
     * @param command Command to add
     */
    void addCommand(String keyword, Command command);

    /**
     * Display the content of the buffer of the engine
     */
    void DisplayBuffer();

    /**
     * Display the content of the clipboard of the engine
     */
    void DisplayClipboard();

    /**
     * Function to display a text in the terminal
     * @param s the text to display
     * @param maxLengthOfLine the number of characters to display per line
     */
    void DisplayText(String s, int maxLengthOfLine);
}