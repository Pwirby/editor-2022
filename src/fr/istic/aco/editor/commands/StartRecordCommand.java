package fr.istic.aco.editor.commands;

import fr.istic.aco.editor.mementos.Memento;
import fr.istic.aco.editor.mementos.Recorder;

public class StartRecordCommand implements Command{
    private final Recorder recorder;

    public StartRecordCommand(Recorder recorder){
        this.recorder = recorder;
    }

    @Override
    public void execute() {
        recorder.start();
    }

    @Override
    public void setMemento(Memento m) {

    }

    @Override
    public Memento getMemento() {
        return null;
    }
}
