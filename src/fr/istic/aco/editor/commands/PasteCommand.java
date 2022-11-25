package fr.istic.aco.editor.commands;

import fr.istic.aco.editor.Engine;

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
}
