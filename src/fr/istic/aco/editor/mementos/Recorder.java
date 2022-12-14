package fr.istic.aco.editor.mementos;

import fr.istic.aco.editor.commands.Command;

public interface Recorder {

    void save(Command c);
    // Start recording at the beginning ?
    void start();
    void stop();
    void replay();

}
