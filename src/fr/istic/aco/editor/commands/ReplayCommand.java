package fr.istic.aco.editor.commands;

import fr.istic.aco.editor.mementos.Memento;
import fr.istic.aco.editor.mementos.Recorder;

/**
 * Command to replay recorded commands
 */
public class ReplayCommand implements Command{
    private final Recorder recorder;

    public ReplayCommand(Recorder recorder){
        this.recorder = recorder;
    }

    @Override
    public void execute() {
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
