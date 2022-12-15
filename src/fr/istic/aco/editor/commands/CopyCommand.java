package fr.istic.aco.editor.commands;

import fr.istic.aco.editor.Engine;
import fr.istic.aco.editor.mementos.Memento;
import fr.istic.aco.editor.mementos.Recorder;

/**
 * Command to copy the content of the selection to the clipboard
 */
public class CopyCommand implements Command {
    private final Engine engine;
    private final Recorder recorder;

    public CopyCommand(Engine engine, Recorder recorder) {
        this.engine = engine;
        this.recorder = recorder;
    }

    @Override
    public void execute() {
        engine.copySelectedText();
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