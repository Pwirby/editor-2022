package fr.istic.aco.editor;

import fr.istic.aco.editor.commands.*;
import fr.istic.aco.editor.mementos.Recorder;
import fr.istic.aco.editor.mementos.RecorderImpl;

public class Mini_editor_V2 {
    private Engine engine;
    private UserInterface userInterface;
    private Recorder recorder;

    public static void main(String[] args){
        Mini_editor_V2 miniEditor = new Mini_editor_V2();
        miniEditor.run();
    }

    /**
     * Setting up of the engine and the user interface and launch of the editor
     */
    private void run(){
        engine = new EngineImpl();
        recorder = new RecorderImpl();
        userInterface = new UserInterfaceImpl(engine, recorder);

        userInterface.setReadStream(System.in);
        configureCommands();
        userInterface.runInvokerLoop();
    }

    /**
     * Setting up of the commands usable by the user
     */
    public void configureCommands(){
        userInterface.addCommand("copy", new CopyCommand(engine, recorder));
        userInterface.addCommand("cut", new CutCommand(engine, recorder));
        userInterface.addCommand("paste", new PasteCommand(engine, userInterface, recorder));
        userInterface.addCommand("selectall", new SelectAllCommand(engine, recorder));
        userInterface.addCommand("extleft", new ExtendLeftSelectionCommand(engine, recorder));
        userInterface.addCommand("extright", new ExtendRightSelectionCommand(engine, recorder));
        userInterface.addCommand("left", new MoveLeftSelectionCommand(engine, recorder));
        userInterface.addCommand("right", new MoveRightSelectionCommand(engine, recorder));
        userInterface.addCommand("begin", new MoveBeginSelectionCommand(engine, recorder));
        userInterface.addCommand("end", new MoveEndSelectionCommand(engine, recorder));
        userInterface.addCommand("quit", new QuitCommand(userInterface));

        userInterface.addCommand("replay", new ReplayCommand(recorder));
        userInterface.addCommand("start", new StartRecordCommand(recorder));
        userInterface.addCommand("stop", new StopRecordCommand(recorder));
    }
}