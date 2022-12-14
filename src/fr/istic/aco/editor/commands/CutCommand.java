package fr.istic.aco.editor.commands;

import fr.istic.aco.editor.Engine;
import fr.istic.aco.editor.mementos.Memento;

/**
 * Command to cut the content of the selection to the clipboard
 */
public class CutCommand implements Command{
    private final Engine engine;

    public CutCommand(Engine engine) {
        this.engine = engine;
    }

    @Override
    public void execute(){
        engine.cutSelectedText();
    }
    @Override
    public void setMemento(Memento m) {

    }

    @Override
    public Memento getMemento() {
        return null;
    }
}
