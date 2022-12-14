package fr.istic.aco.editor.commands;

import fr.istic.aco.editor.Engine;
import fr.istic.aco.editor.mementos.Memento;

/**
 * Command to move the selection to the right by one character
 */
public class MoveRightSelectionCommand implements Command{
    private final Engine engine;

    public MoveRightSelectionCommand(Engine engine) {
        this.engine = engine;
    }

    @Override
    public void execute() {
        engine.getSelection().setEndIndex(engine.getSelection().getEndIndex()+1);
        engine.getSelection().setBeginIndex(engine.getSelection().getBeginIndex()+1);
    }

    @Override
    public void setMemento(Memento m) {

    }

    @Override
    public Memento getMemento() {
        return null;
    }
}