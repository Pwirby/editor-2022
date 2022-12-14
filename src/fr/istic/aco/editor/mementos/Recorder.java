package fr.istic.aco.editor.mementos;

import fr.istic.aco.editor.commands.Command;

/**
 * API of the Recorder
 *
 */
public interface Recorder {
    /**
     * Store a command with its memento into a history
     * @param c The command to store
     */
    void save(Command c);

    /**
     * Starts the recording of commands and empties the history
     */
    void start();

    /**
     * Stop the recording of commands
     */
    void stop();

    /**
     * Execute recorded commands in history,
     * if it's not recording
     */
    void replay();

}