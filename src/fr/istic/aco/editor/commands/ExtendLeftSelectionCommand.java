package fr.istic.aco.editor.commands;

import fr.istic.aco.editor.Engine;
import fr.istic.aco.editor.mementos.Memento;
import fr.istic.aco.editor.mementos.Recorder;

/**
 * Command to extend the selection to the left by one character
 */
public class ExtendLeftSelectionCommand implements Command{
    private final Engine engine;
    private final Recorder recorder;

    public ExtendLeftSelectionCommand(Engine engine, Recorder recorder) {
        this.engine = engine;
        this.recorder = recorder;
    }

    @Override
    public void execute() {
        engine.getSelection().setBeginIndex(engine.getSelection().getBeginIndex()-1);
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
