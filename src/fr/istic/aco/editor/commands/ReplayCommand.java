package fr.istic.aco.editor.commands;

import fr.istic.aco.editor.Recorder;

public class ReplayCommand implements Command{

    private Recorder recorder;

    public ReplayCommand(Recorder recorder){
        this.recorder = recorder;
    }

    @Override
    public void execute() {
        recorder.replay();
    }
}
