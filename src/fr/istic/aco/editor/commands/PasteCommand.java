package fr.istic.aco.editor.commands;

import fr.istic.aco.editor.Engine;
import fr.istic.aco.editor.UndoManager;
import fr.istic.aco.editor.UserInterface;
import fr.istic.aco.editor.mementos.InsertMemento;
import fr.istic.aco.editor.mementos.Memento;
import fr.istic.aco.editor.mementos.Recorder;

/**
 * Command to paste the content of the clipboard the selection
 */
public class PasteCommand implements Command{
    private final Engine engine;
    private final UserInterface userInterface;
    private final Recorder recorder;
    private final UndoManager undoManager;

    public PasteCommand(Engine engine, UserInterface userInterface, Recorder recorder, UndoManager undoManager) {
        this.engine = engine;
        this.userInterface = userInterface;
        this.recorder = recorder;
        this.undoManager = undoManager;
    }

    @Override
    public void execute(){
        undoManager.store();
        engine.pasteClipboard();
        recorder.save(this);
    }
    @Override
    public void setMemento(Memento m) {
        // L'userInterface contient déjà un attribut textToInsert
        userInterface.setTextToInsert(m.getState());
    }

    @Override
    public Memento getMemento() {
        InsertMemento m = new InsertMemento();
        m.setTextToInsert(engine.getClipboardContents());
        return m;
    }
}
