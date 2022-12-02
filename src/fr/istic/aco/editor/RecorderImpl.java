package fr.istic.aco.editor;

import fr.istic.aco.editor.commands.Command;
import fr.istic.aco.editor.mementos.Memento;

import java.util.ArrayList;
import java.util.List;

public class RecorderImpl implements Recorder{
    private List<Pair> history  = new ArrayList<Pair>();

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

    }

    @Override
    public void saveCommand(Command c) {
        history.add(new Pair(c, c.getMemento()));
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void replay() {

    }
}
