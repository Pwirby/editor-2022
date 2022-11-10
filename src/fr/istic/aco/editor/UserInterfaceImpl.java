package fr.istic.aco.editor;

import fr.istic.aco.editor.commands.Command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;


public class UserInterfaceImpl implements UserInterface{
    private Map<String, Command> commands = new HashMap<>();
    private boolean stopLoop = false;
    private InputStream inputStream;
    private BufferedReader bufferedReader;
    private Engine engine;

    public UserInterfaceImpl(Engine engine) {
        this.engine = engine;
    }

    /**
     * The main loop for the text editor that takes commands or texts
     */
    @Override
    public void runInvokerLoop() {
        while (!stopLoop) {
            DisplayBuffer();
            DisplayClipboard();
            String userInput = null;
            try {
                userInput = readUserInput();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(userInput == null) {
                break;
            }
            if(commands.containsKey(userInput)){
                Command cmdToExecute = commands.get(userInput);
                if (cmdToExecute != null) {
                    cmdToExecute.execute();
                }
            }
            else{
                engine.insert(userInput);
            }
        }
    }

    /*
    /**
     * Stop the main loop in runIvokerLoop and quit the application

    @Override
    public void stopLoop() {
        stopLoop = true;
    }
    */

    private String readUserInput() throws IOException {
        return bufferedReader.readLine();
    }

    /**
     * Set the inputStream and connect to the bufferReader
     * @param inputStream the InputStream to
     */
    @Override
    public void setReadStream(InputStream inputStream) {
        if(inputStream == null) {
            throw new IllegalArgumentException("null inputStream");
        }
        this.inputStream = inputStream;
        this.bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
    }

    /**
     * Add a Command in a hashmap
     * @param keyword name of the command
     * @param command Command to add
     */
    @Override
    public void addCommand(String keyword, Command command) {
        if ((keyword == null) || (command == null)) {
            throw new IllegalArgumentException("null parameter");
        }
        commands.put(keyword,command);
    }

    /**
     * Display the content of the buffer of the engine
     */
    @Override
    public void DisplayBuffer() {
        System.out.println("========== Buffer ===========================================================");
        DisplayText(engine.toString(), 70);
    }

    /**
     * Display the content of the clipboard of the engine
     */
    @Override
    public void DisplayClipboard() {
        System.out.println("========== Clipboard ========================================================");
        DisplayText(engine.getClipboardContents(), 70);
    }

    /**
     * Function to display a text in the terminal
     * @param s the text to display
     * @param maxLengthOfLine the number of characters to display per line
     */
    @Override
    public void DisplayText(String s, int maxLengthOfLine){
        for (int i=0; i<s.length(); i+=maxLengthOfLine){
            System.out.print("|   ");
            int lengthOfLine;
            if(s.length() - i < maxLengthOfLine){
                lengthOfLine = s.length() - i;
            }
            else {
                lengthOfLine = maxLengthOfLine;
            }
            for (int j=0; j<lengthOfLine; j++){
                System.out.print(s.charAt(i+j));
            }
            System.out.println();
        }
    }
}