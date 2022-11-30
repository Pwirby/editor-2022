package fr.istic.aco.editor.commands;

import fr.istic.aco.editor.Engine;
import fr.istic.aco.editor.UserInterface;

public class InsertCommand implements Command{
    private final Engine engine;
    private final UserInterface userInterface;

    public InsertCommand(Engine engine, UserInterface userInterface) {
        this.engine = engine;
        this.userInterface = userInterface;
    }

    @Override
    public void execute() {
        engine.insert(userInterface.getTextToInsert());
    }
}
