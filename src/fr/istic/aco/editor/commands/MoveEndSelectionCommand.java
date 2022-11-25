package fr.istic.aco.editor.commands;

import fr.istic.aco.editor.Engine;

/**
 * Command to set the selection at the end of the buffer
 */
public class MoveEndSelectionCommand implements Command {
    private final Engine engine;

    public MoveEndSelectionCommand(Engine engine) {
        this.engine = engine;
    }

    @Override
    public void execute() {
        engine.getSelection().setEndIndex(engine.getSelection().getBufferEndIndex());
        engine.getSelection().setBeginIndex(engine.getSelection().getBufferEndIndex());
    }
}