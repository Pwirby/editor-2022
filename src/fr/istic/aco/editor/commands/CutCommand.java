package fr.istic.aco.editor.commands;

import fr.istic.aco.editor.Engine;

public class CutCommand implements Command{
    private final Engine engine;

    public CutCommand(Engine engine) {
        this.engine = engine;
    }

    @Override
    public void execute(){
        engine.cutSelectedText();
    };
}
