package fr.istic.aco.editor.commands;

import fr.istic.aco.editor.UndoManager;
import fr.istic.aco.editor.mementos.Memento;

public class RedoCommand implements Command {
    private final UndoManager undoManager;

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