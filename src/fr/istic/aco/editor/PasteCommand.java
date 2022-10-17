package fr.istic.aco.editor;

public class PasteCommand implements Command{
    private Engine engine;

    @Override
    public void execute(){
        engine.pasteClipboard();
    }
}
