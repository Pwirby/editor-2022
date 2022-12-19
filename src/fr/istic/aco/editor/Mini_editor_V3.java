package fr.istic.aco.editor;

import fr.istic.aco.editor.commands.*;
import fr.istic.aco.editor.mementos.Recorder;
import fr.istic.aco.editor.mementos.RecorderImpl;

public class Mini_editor_V3 {
    private Engine engine;
    private UserInterface userInterface;
    private Recorder recorder;
    private UndoManager undoManager;

    public static void main(String[] args){
        Mini_editor_V3 miniEditor = new Mini_editor_V3();
        miniEditor.run();
    }

    /**
     * Setting up of the engine and the user interface and launch of the editor
     */
    private void run(){
        engine = new EngineImpl();
        recorder = new RecorderImpl();
        undoManager = new UndoManager(engine);
        userInterface = new UserInterfaceImpl(engine, recorder, undoManager);

        userInterface.setReadStream(System.in);
        configureCommands();
        userInterface.runInvokerLoop();
    }

    /**
     * Setting up of the commands usable by the user
     */
    public void configureCommands(){
        userInterface.addCommand("copy", new CopyCommand(engine, recorder, undoManager));
        userInterface.addCommand("cut", new CutCommand(engine, recorder, undoManager));
        userInterface.addCommand("paste", new PasteCommand(engine, userInterface, recorder, undoManager));
        userInterface.addCommand("selectall", new SelectAllCommand(engine, recorder, undoManager));
        userInterface.addCommand("extleft", new ExtendLeftSelectionCommand(engine, recorder, undoManager));
        userInterface.addCommand("extright", new ExtendRightSelectionCommand(engine, recorder, undoManager));
        userInterface.addCommand("left", new MoveLeftSelectionCommand(engine, recorder, undoManager));
        userInterface.addCommand("right", new MoveRightSelectionCommand(engine, recorder, undoManager));
        userInterface.addCommand("begin", new MoveBeginSelectionCommand(engine, recorder, undoManager));
        userInterface.addCommand("end", new MoveEndSelectionCommand(engine, recorder, undoManager));
        userInterface.addCommand("quit", new QuitCommand(userInterface));

        userInterface.addCommand("replay", new ReplayCommand(recorder, undoManager));
        userInterface.addCommand("start", new StartRecordCommand(recorder));
        userInterface.addCommand("stop", new StopRecordCommand(recorder));

        userInterface.addCommand("undo", new UndoCommand(undoManager));
        userInterface.addCommand("redo", new RedoCommand(undoManager));
    }
}