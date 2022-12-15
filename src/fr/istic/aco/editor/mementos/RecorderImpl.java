package fr.istic.aco.editor.mementos;

import fr.istic.aco.editor.commands.Command;

import java.util.ArrayList;

public class RecorderImpl implements Recorder {
    private ArrayList<Pair> history;
    private boolean isRecording;

    /**
     * Class used to associate a Command with its Memento
     */
    private static class Pair {
        private final Command command;
        private final Memento memento;
        Pair(Command c, Memento m) {
            this.command = c;
            this.memento = m;
        }
        private Command getCommand() {
            return command;
        }
        private Memento getMemento() {
            return memento;
        }
    }

    public RecorderImpl(){
        history = new ArrayList<>();
        isRecording = false;
    }

    /**
     * Store a command with its memento into a history
     * @param c The command to store
     */
    @Override
    public void save(Command c) {
        if(isRecording)
            history.add(new Pair(c, c.getMemento()));
    }

    /**
     * Starts the recording of commands and empties the history
     */
    @Override
    public void start() {
        isRecording = true;
        history = new ArrayList<>();
    }

    /**
     * Stop the recording of commands
     */
    @Override
    public void stop() {
        isRecording = false;
    }

    /**
     * Execute recorded commands in history, if it's not recording
     */
    @Override
    public void replay() {
        if (!isRecording){
            for (Pair pair : history) {
                pair.getCommand().setMemento(pair.getMemento());
                pair.getCommand().execute();
            }
        }
    }

    public String toString(){
        StringBuilder str = new StringBuilder("Commands :\n");
        for (Pair pair : history) {

            str.append("    ").append(pair.getCommand().toString()).append("\n");
        }
        return str.toString();
    }
}
