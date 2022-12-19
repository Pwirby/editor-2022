package fr.istic.aco.editor.commands;

import fr.istic.aco.editor.Engine;
import fr.istic.aco.editor.UndoManager;
import fr.istic.aco.editor.mementos.Memento;
import fr.istic.aco.editor.mementos.Recorder;

/**
 * Command to move the selection to the right by one character
 */
public class MoveRightSelectionCommand implements Command{
    private final Engine engine;
    private final Recorder recorder;
    private final UndoManager undoManager;

    public MoveRightSelectionCommand(Engine engine, Recorder recorder, UndoManager undoManager) {
        this.engine = engine;
        this.recorder = recorder;
        this.undoManager = undoManager;
    }

    @Override
    public void execute() {
        undoManager.store();
        engine.getSelection().setEndIndex(engine.getSelection().getEndIndex()+1);
        engine.getSelection().setBeginIndex(engine.getSelection().getBeginIndex()+1);
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