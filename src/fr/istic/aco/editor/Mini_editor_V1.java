package fr.istic.aco.editor;

import fr.istic.aco.editor.commands.*;

public class Mini_editor_V1{

    private Engine engine;
    private UserInterface userInterface;

    public static void main(String[] args){
        Mini_editor_V1 miniEditor = new Mini_editor_V1();
        miniEditor.run();
    }


    private void run(){

        engine = new EngineImpl();
        userInterface = new UserInterfaceImpl(engine);

        userInterface.setReadStream(System.in);

        configureCommands();

        userInterface.runInvokerLoop();
    }

    public void configureCommands(){
        userInterface.addCommand("Copy", new CopyCommand(engine));
        userInterface.addCommand("Cut", new CutCommand(engine));
        userInterface.addCommand("Paste", new PasteCommand(engine));
        userInterface.addCommand("SelectAll", new SelectAllCommand(engine));
    }

}