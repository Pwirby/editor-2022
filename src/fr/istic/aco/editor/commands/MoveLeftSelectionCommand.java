package fr.istic.aco.editor.commands;

import fr.istic.aco.editor.Engine;

/**
 * Command to move the selection to the left by one character
 */
public class MoveLeftSelectionCommand implements Command{
    private final Engine engine;

    public MoveLeftSelectionCommand(Engine engine) {
        this.engine = engine;
    }

    @Override
    public void execute() {
        engine.getSelection().setBeginIndex(engine.getSelection().getBeginIndex()-1);
        engine.getSelection().setEndIndex(engine.getSelection().getEndIndex()-1);
    }
}
