package fr.istic.aco.editor.commands;

import fr.istic.aco.editor.Engine;
import fr.istic.aco.editor.UndoManager;
import fr.istic.aco.editor.mementos.Memento;
import fr.istic.aco.editor.mementos.Recorder;

/**
 * Command to move the selection to the left by one character
 */
public class MoveLeftSelectionCommand implements Command {
    private final Engine engine;
    private final Recorder recorder;
    private final UndoManager undoManager;

    /**
     * Move both selection's bounds to the left by one character
     *
     * @param engine
     * @param recorder
     */
    public MoveLeftSelectionCommand(Engine engine, Recorder recorder, UndoManager undoManager) {
        this.engine = engine;
        this.recorder = recorder;
        this.undoManager = undoManager;
    }

    @Override
    public void execute() {
        undoManager.store();
        engine.getSelection().setBeginIndex(engine.getSelection().getBeginIndex() - 1);
        engine.getSelection().setEndIndex(engine.getSelection().getEndIndex() - 1);
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
