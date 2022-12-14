package fr.istic.aco.editor.commands;

import fr.istic.aco.editor.Engine;
import fr.istic.aco.editor.UserInterface;
import fr.istic.aco.editor.mementos.Memento;

public class DisplayCommand implements Command{
    private final Engine engine;
    private final UserInterface userInterface;

    public DisplayCommand(Engine engine, UserInterface userInterface){
        this.engine = engine;
        this.userInterface = userInterface;
    }

    @Override
    public void execute() {
        userInterface.displayContent(engine);
    }
    @Override
    public void setMemento(Memento m) {

    }

    @Override
    public Memento getMemento() {
        return null;
    }
}