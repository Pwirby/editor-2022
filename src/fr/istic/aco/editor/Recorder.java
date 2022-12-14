package fr.istic.aco.editor;

import fr.istic.aco.editor.commands.Command;

public interface Recorder {
    /**
     * Save the command in the recorder.
     * @param c the command to save
     */
    void saveCommand(Command c);
    // Start recording at the beginning ?
    /**
     * Start the recording of every executed command.
     */
    void start();
    /**
     * Stop the recording of every executed command.
     */
    void stop();

    /**
     * Re-execute every command saved.
     */
    void replay();

}
