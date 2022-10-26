package fr.istic.aco.editor;

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

    @Override
    public void runInvokerLoop() {
        while (!stopLoop) {
            // TODO afficher les informations du buffer et du clipboard
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
            // TODO vérifier que l'input entré correspond bien à une commande
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
    @Override
    public void stopLoop() {
        stopLoop = true;
    }
    */

    private String readUserInput() throws IOException {
        return bufferedReader.readLine();
    }

    @Override
    public void setReadStream(InputStream inputStream) {
        if(inputStream == null) {
            throw new IllegalArgumentException("null inputStream");
        }
        this.inputStream = inputStream;
        this.bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
    }

    @Override
    public void addCommand(String keyword, Command cmd) {
        if ((keyword == null) || (cmd == null)) {
            throw new IllegalArgumentException("null parameter");
        }
        commands.put(keyword,cmd);
    }


    //TODO ajouter les fontions en lien avec l'engine pour l'affichage

    @Override
    public void DisplayBuffer() {
        System.out.println("========== Buffer ===========================================================");
        System.out.println(engine.toString());
        //System.out.printf("=============================================================================");
    }

    @Override
    public void DisplayClipboard() {
        System.out.println("========== Clipboard ========================================================");
        System.out.println(engine.getClipboardContents());
        //System.out.printf("=============================================================================");
    }


}