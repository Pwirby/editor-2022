package fr.istic.aco.editor.commands;

import fr.istic.aco.editor.UndoManager;
import fr.istic.aco.editor.mementos.Memento;

public class StoreCommand implements Command {
    private final UndoManager undoManager;

    public StoreCommand(UndoManager undoManager) {
        this.undoManager = undoManager;
    }

    @Override
    public void execute() {
        undoManager.store();
    }

    @Override
    public void setMemento(Memento m) {

    }
    @Override
    public Memento getMemento() {
        return null;
    }
}