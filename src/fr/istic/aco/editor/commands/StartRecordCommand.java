package fr.istic.aco.editor.commands;

import fr.istic.aco.editor.mementos.Memento;
import fr.istic.aco.editor.mementos.Recorder;

/**
 * Command to start recording commands entered
 */
public class StartRecordCommand implements Command{
    private final Recorder recorder;

    /**
     * @param recorder where to save the command about to be saved
     */
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
