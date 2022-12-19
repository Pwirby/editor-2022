package fr.istic.aco.editor.commands;

import fr.istic.aco.editor.mementos.Memento;
import fr.istic.aco.editor.mementos.Recorder;

/**
 * The command to stop recording commands being entered
 */
public class StopRecordCommand implements Command {
    private final Recorder recorder;

    /**
     * Stop recording commands being entered
     *
     * @param recorder where commands are being registered
     */
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