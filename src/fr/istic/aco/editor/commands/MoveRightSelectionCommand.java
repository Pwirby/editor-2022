package fr.istic.aco.editor.commands;

import fr.istic.aco.editor.Engine;

public class MoveRightSelectionCommand implements Command{
    private final Engine engine;

    public MoveRightSelectionCommand(Engine engine) {
        this.engine = engine;
    }

    @Override
    public void execute() {
        engine.getSelection().setEndIndex(engine.getSelection().getEndIndex()+1);
        engine.getSelection().setBeginIndex(engine.getSelection().getBeginIndex()+1);
    }
}