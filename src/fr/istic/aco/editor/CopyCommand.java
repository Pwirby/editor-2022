package fr.istic.aco.editor;

public class CopyCommand implements Command {
    private Engine engine;

    @Override
    public void execute() {
        engine.copySelectedText();
    }
}