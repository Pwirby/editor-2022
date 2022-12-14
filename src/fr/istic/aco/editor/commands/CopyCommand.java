package fr.istic.aco.editor.commands;

import fr.istic.aco.editor.Engine;
import fr.istic.aco.editor.mementos.Memento;

/**
 * Command to copy the content of the selection to the clipboard
 */
public class CopyCommand implements Command {
    private final Engine engine;

    public CopyCommand(Engine engine) {
        this.engine = engine;
    }

    @Override
    public void execute() {
        engine.copySelectedText();
    }

    @Override
    public void setMemento(Memento m) {

    }

    @Override
    public Memento getMemento() {
        return null;
    }
}