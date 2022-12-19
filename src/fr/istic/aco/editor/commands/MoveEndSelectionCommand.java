package fr.istic.aco.editor.commands;

import fr.istic.aco.editor.Engine;
import fr.istic.aco.editor.UndoManager;
import fr.istic.aco.editor.mementos.Memento;
import fr.istic.aco.editor.mementos.Recorder;

/**
 * Command to set the selection at the end of the buffer
 */
public class MoveEndSelectionCommand implements Command {
    private final Engine engine;
    private final Recorder recorder;
    private final UndoManager undoManager;

    /**
     * Move both selection's bounds to the end of the buffer
     * @param engine
     * @param recorder
     */
    public MoveEndSelectionCommand(Engine engine, Recorder recorder, UndoManager undoManager) {
        this.engine = engine;
        this.recorder = recorder;
        this.undoManager = undoManager;
    }

    @Override
    public void execute() {
        undoManager.store();
        engine.getSelection().setEndIndex(engine.getSelection().getBufferEndIndex());
        engine.getSelection().setBeginIndex(engine.getSelection().getBufferEndIndex());
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