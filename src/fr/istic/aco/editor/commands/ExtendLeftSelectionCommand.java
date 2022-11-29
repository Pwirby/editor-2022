package fr.istic.aco.editor.commands;

import fr.istic.aco.editor.Engine;

/**
 * Command to extend the selection to the left by one character
 */
public class ExtendLeftSelectionCommand implements Command{
    private final Engine engine;

    public ExtendLeftSelectionCommand(Engine engine) {
        this.engine = engine;
    }

    @Override
    public void execute() {
        engine.getSelection().setBeginIndex(engine.getSelection().getBeginIndex()-1);
    }
}
