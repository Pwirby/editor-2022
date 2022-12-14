package fr.istic.aco.editor.commands;

import fr.istic.aco.editor.Engine;
import fr.istic.aco.editor.mementos.Memento;

/**
 * Command to extend the selection to the right by one character
 */
public class ExtendRightSelectionCommand implements Command{
    private final Engine engine;

    public ExtendRightSelectionCommand(Engine engine) {
        this.engine = engine;
    }

    @Override
    public void execute() {
        engine.getSelection().setEndIndex(engine.getSelection().getEndIndex()+1);
    }
    @Override
    public void setMemento(Memento m) {

    }

    @Override
    public Memento getMemento() {
        return null;
    }
}
