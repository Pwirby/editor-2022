package fr.istic.aco.editor.mementos;

import fr.istic.aco.editor.commands.Command;
import fr.istic.aco.editor.mementos.Memento;
import fr.istic.aco.editor.mementos.Recorder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RecorderImpl implements Recorder {
    private ArrayList<Pair> history;
    private boolean isRecording;

    private class Pair {
        Command command;
        Memento memento;
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
        history = new ArrayList<Pair>();
        isRecording = false;
    }

    @Override
    public void save(Command c) {
        if(isRecording)
            history.add(new Pair(c, c.getMemento()));
        for (Pair pair : history) {
            System.out.println(pair.command.toString() + " : " + pair.memento.getState());
        }
    }

    @Override
    public void start() {
        isRecording = true;
        history = new ArrayList<Pair>();
    }

    @Override
    public void stop() {
        isRecording = false;
    }

    @Override
    public void replay() {
        if (!isRecording){
            for (int i = 0; i < history.size(); i++) {
                history.get(i).getCommand().setMemento(history.get(i).getMemento());
                history.get(i).getCommand().execute();
            }
        }
    }
}
