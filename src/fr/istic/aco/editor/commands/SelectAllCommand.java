package fr.istic.aco.editor.commands;

import fr.istic.aco.editor.Engine;

public class SelectAllCommand implements Command{
    private final Engine engine;

    public SelectAllCommand(Engine engine){
        this.engine = engine;
    }

    @Override
    public void execute() {
        engine.getSelection().setBeginIndex(engine.getSelection().getBufferBeginIndex());
        engine.getSelection().setEndIndex(engine.getSelection().getBufferEndIndex());
    }
}
