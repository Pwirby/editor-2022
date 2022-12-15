package fr.istic.aco.editor.commands;

import fr.istic.aco.editor.Engine;
import fr.istic.aco.editor.mementos.Memento;
import fr.istic.aco.editor.mementos.Recorder;

/**
 * Command to set the selection at the begin of the buffer
 */
public class MoveBeginSelectionCommand implements Command {
    private final Engine engine;
    private final Recorder recorder;

    public MoveBeginSelectionCommand(Engine engine, Recorder recorder) {
        this.engine = engine;
        this.recorder = recorder;
    }

    @Override
    public void execute() {
        engine.getSelection().setBeginIndex(engine.getSelection().getBufferBeginIndex());
        engine.getSelection().setEndIndex(engine.getSelection().getBufferBeginIndex());
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