package fr.istic.aco.editor.commands;

import fr.istic.aco.editor.UndoManager;
import fr.istic.aco.editor.mementos.Memento;

/**
 * Command to redo a previously undone command
 */
public class RedoCommand implements Command {
    private final UndoManager undoManager;

    /**
     * Redo a previously undone command
     *
     * @param undoManager managing the states of the engine
     */
    public RedoCommand(UndoManager undoManager) {
        this.undoManager = undoManager;
    }

    @Override
    public void execute() {
        undoManager.redo();
    }

    @Override
    public void setMemento(Memento m) {

    }

    @Override
    public Memento getMemento() {
        return null;
    }
}