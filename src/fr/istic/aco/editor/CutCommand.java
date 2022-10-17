package fr.istic.aco.editor;

public class CutCommand implements Command{

    private Engine engine;

    @Override
    public void execute(){
        engine.cutSelectedText();
    };
}
