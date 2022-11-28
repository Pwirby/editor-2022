package fr.istic.aco.editor;

import fr.istic.aco.editor.commands.Command;

public interface Recorder {

    void saveCommand(Command c);
    // Start recording at the beginning ?
    void start();
    void stop();
    void replay();

}
