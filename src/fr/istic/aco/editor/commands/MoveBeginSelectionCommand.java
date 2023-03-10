package fr.istic.aco.editor.commands;

import fr.istic.aco.editor.Engine;

/**
 * Command to set the selection at the begin of the buffer
 */
public class MoveBeginSelectionCommand implements Command {
    private final Engine engine;

    public MoveBeginSelectionCommand(Engine engine) {
        this.engine = engine;
    }

    @Override
    public void execute() {
        engine.getSelection().setBeginIndex(engine.getSelection().getBufferBeginIndex());
        engine.getSelection().setEndIndex(engine.getSelection().getBufferBeginIndex());
    }
}