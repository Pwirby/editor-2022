package fr.istic.aco.editor;

public class CopyCommand implements Command {
    private Engine engine;

    public CopyCommand(Engine engine) {
        this.engine = engine;
    }

    @Override
    public void execute() {
        engine.copySelectedText();
    }
}