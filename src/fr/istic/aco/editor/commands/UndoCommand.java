package fr.istic.aco.editor.commands;

import fr.istic.aco.editor.UndoManager;
import fr.istic.aco.editor.mementos.Memento;
/**
 * Command to undo the previous command executed
 */
public class UndoCommand implements Command{
    private final UndoManager undoManager;

    public UndoCommand(UndoManager undoManager){
        this.undoManager = undoManager;
    }

    @Override
    public void execute() {
        undoManager.undo();
    }

    @Override
    public void setMemento(Memento m) {

    }

    @Override
    public Memento getMemento() {
        return null;
    }
}
