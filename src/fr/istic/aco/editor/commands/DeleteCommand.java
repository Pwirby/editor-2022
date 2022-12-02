package fr.istic.aco.editor.commands;

import fr.istic.aco.editor.Engine;

public class DeleteCommand implements Command{
    private final Engine engine;

    public DeleteCommand(Engine engine) {
        this.engine = engine;
    }

    @Override
    public void execute(){
        engine.delete();
    }
}
