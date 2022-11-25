package fr.istic.aco.editor;

import fr.istic.aco.editor.commands.Command;

import java.io.IOException;
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
    void stopLoop();

    /**
     * Return the boolean of the runLoop
     * @return the stopLoop attribute
     */
    boolean getStopLoop();

    /**
     * Set the inputStream and connect to the bufferReader
     * @param inputStream The InputStream to read from
     * @throws IllegalArgumentException If the inputStream is null
     */
    void setReadStream(InputStream inputStream);

    /**
     * Add a Command in a hashmap
     * @param keyword Name of the command
     * @param command Command object to add
     * @throws IllegalArgumentException if one the parameters is null
     * @hidden Command name is set to lowercase
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
     * @param s Text to display
     */
    void DisplayText(String s);
}