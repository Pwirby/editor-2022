package fr.istic.aco.editor.commands;

import fr.istic.aco.editor.UserInterface;

public class QuitCommand implements Command{
    private final UserInterface userInterface;

    public QuitCommand(UserInterface userInterface){
        this.userInterface = userInterface;
    }

    @Override
    public void execute() {
        userInterface.stopLoop();
    }
}
