package fr.istic.aco.editor.commands;

import fr.istic.aco.editor.Engine;
import fr.istic.aco.editor.UndoManager;
import fr.istic.aco.editor.mementos.Memento;
import fr.istic.aco.editor.mementos.Recorder;

/**
 * Command to extend the selection to the right by one character
 */
public class ExtendRightSelectionCommand implements Command{
    private final Engine engine;
    private final Recorder recorder;
    private final UndoManager undoManager;

    /**
     * Extend the selection to the right by one character
     *
     * @param engine
     * @param recorder where commands are being registered
     */
    public ExtendRightSelectionCommand(Engine engine, Recorder recorder, UndoManager undoManager) {
        this.engine = engine;
        this.recorder = recorder;
        this.undoManager = undoManager;
    }

    @Override
    public void execute() {
        undoManager.store();
        engine.getSelection().setEndIndex(engine.getSelection().getEndIndex()+1);
        recorder.save(this);
    }

    @Override
    public void setMemento(Memento m) {
    }

    @Override
    public Memento getMemento() {
        return null;
    }
}
