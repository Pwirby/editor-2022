package fr.istic.aco.editor.commands;

import fr.istic.aco.editor.mementos.Memento;
import fr.istic.aco.editor.mementos.Recorder;

public class StopRecordCommand implements Command {
    private final Recorder recorder;

    public StopRecordCommand(Recorder recorder) {
        this.recorder = recorder;
    }

    @Override
    public void execute() {
        recorder.stop();
    }

    @Override
    public void setMemento(Memento m) {

    }

    @Override
    public Memento getMemento() {
        return null;
    }
}