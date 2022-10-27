package fr.istic.aco.editor.commands;

import fr.istic.aco.editor.Engine;

public class CopyCommand implements Command {
    private final Engine engine;

    public CopyCommand(Engine engine) {
        this.engine = engine;
    }

    @Override
    public void execute() {
        engine.copySelectedText();
    }
}