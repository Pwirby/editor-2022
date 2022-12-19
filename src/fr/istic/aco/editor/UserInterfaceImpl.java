package fr.istic.aco.editor;

import fr.istic.aco.editor.commands.Command;
import fr.istic.aco.editor.commands.DisplayCommand;
import fr.istic.aco.editor.commands.InsertCommand;
import fr.istic.aco.editor.commands.StoreCommand;
import fr.istic.aco.editor.mementos.Recorder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class UserInterfaceImpl implements UserInterface {
    private final Map<String, Command> commands = new HashMap<>();
    private boolean stopLoop = false;
    private final char commandToken = '/';
    private final int charPerLine = 70;
    private InputStream inputStream;
    private BufferedReader bufferedReader;
    private String textToInsert;

    /**
     * The constructor to make a new {@link UserInterfaceImpl}
     *
     * @param engine
     * @param recorder where the command history will be recorded
     */
    public UserInterfaceImpl(Engine engine, Recorder recorder, UndoManager undoManager) {
        textToInsert = engine.getBufferContents();
        addCommand("insert", new InsertCommand(engine, this, recorder, undoManager));
        addCommand("display", new DisplayCommand(engine, this));
        addCommand("store", new StoreCommand(undoManager));
    }

    /**
     * The main loop for the text editor that takes commands or texts
     *
     * @exception IOException On input error
     * @see IOException
     */
    @Override
    public void runInvokerLoop() {
        while (!this.getStopLoop()) {
            commands.get(commandToken + "display").execute();
            String userInput = null;
            try {
                userInput = readUserInput();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (userInput == null) {
                break;
            }
            //commands.get(commandToken+"store").execute();
            if (commands.containsKey(userInput.toLowerCase())) {
                Command cmdToExecute = commands.get(userInput.toLowerCase());
                cmdToExecute.execute();
            } else {
                setTextToInsert(userInput);
                commands.get(commandToken + "insert").execute();
            }
        }
    }

    /**
     * Stop the main loop in runIvokerLoop and quit the application
     */
    @Override
    public void stopLoop() {
        stopLoop = true;
    }

    /**
     * Return the state of the runLoop
     *
     * @return true if the loop is stopped, false otherwise
     */
    @Override
    public boolean getStopLoop() {
        return stopLoop;
    }

    /**
     * Read from the bufferedReader
     *
     * @throws IOException On input error
     * @see IOException
     */
    private String readUserInput() throws IOException {
        return bufferedReader.readLine();
    }

    /**
     * Set the inputStream and connect to the bufferReader
     *
     * @param inputStream The InputStream to read from
     * @throws IllegalArgumentException If the inputStream is null
     */
    @Override
    public void setReadStream(InputStream inputStream) {
        if (inputStream == null) {
            throw new IllegalArgumentException("null inputStream");
        }
        this.inputStream = inputStream;
        this.bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
    }

    /**
     * Add a {@link Command} in a hashmap
     *
     * @param keyword Name of the command
     * @param command Command object to add
     * @hidden Command name is set to lowercase
     */
    @Override
    public void addCommand(String keyword, Command command) {
        if ((keyword == null) || (command == null)) {
            throw new IllegalArgumentException("null parameter");
        }
        commands.put(commandToken + keyword.toLowerCase(), command);
    }


    /**
     * Display the contents of the buffer and eventually
     * the clipboard in the terminal
     */
    public void displayContent(Engine engine) {
        System.out.println("========== Buffer ===========================================================");
        displayText(engine.toString());
        if (!engine.getClipboardContents().isEmpty()) {
            System.out.println("========== Clipboard ========================================================");
            displayText(engine.getClipboardContents());
        }
    }

    /**
     * Print the text, line by line, where the CharPerLine
     * attribute is the maximum length of a line
     *
     * @param s Text to display
     */
    @Override
    public void displayText(String s) {
        int lastLine = s.length() - s.length() % charPerLine;
        for (int i = 0; i < lastLine; i += charPerLine) {
            System.out.println("|   " + s.substring(i, i + charPerLine));
        }
        System.out.println("|   " + s.substring(lastLine));
    }

    /**
     * Set the text used to insert text in the engine
     *
     * @param s Text to insert
     */
    @Override
    public void setTextToInsert(String s) {
        textToInsert = s;
    }

    /**
     * Provide the text to insert in the engine's buffer
     *
     * @return the text to insert
     */
    @Override
    public String getTextToInsert() {
        return this.textToInsert;
    }


}