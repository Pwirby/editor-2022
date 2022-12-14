package fr.istic.aco.editor.commands;

import fr.istic.aco.editor.Engine;
import fr.istic.aco.editor.mementos.Memento;

/**
 * Command to paste the content of the clipboard the selection
 */
public class PasteCommand implements Command{
    private final Engine engine;

    public PasteCommand(Engine engine) {
        this.engine = engine;
    }

    @Override
    public void execute(){
        engine.pasteClipboard();
    }
    @Override
    public void setMemento(Memento m) {

    }

    @Override
    public Memento getMemento() {
        return null;
    }
}
