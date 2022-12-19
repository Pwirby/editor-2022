package fr.istic.aco.editor.commands;

import fr.istic.aco.editor.UndoManager;
import fr.istic.aco.editor.mementos.Memento;
import fr.istic.aco.editor.mementos.Recorder;

/**
 * Command to replay recorded commands
 */
public class ReplayCommand implements Command{
    private final Recorder recorder;
    private final UndoManager undoManager;

    public ReplayCommand(Recorder recorder, UndoManager undoManager){
        this.recorder = recorder;
        this.undoManager = undoManager;
    }

    @Override
    public void execute() {
        undoManager.store();
        recorder.replay();
    }
    @Override
    public void setMemento(Memento m) {
    }
    @Override
    public Memento getMemento() {
        return null;
    }
}
