package fr.istic.aco.editor.commands;

import fr.istic.aco.editor.Engine;

public class ExtendRightSelectionCommand implements Command{
    private final Engine engine;

    public ExtendRightSelectionCommand(Engine engine) {
        this.engine = engine;
    }

    @Override
    public void execute() {
        engine.getSelection().setEndIndex(engine.getSelection().getEndIndex()+1);
    }
}
