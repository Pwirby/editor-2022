package fr.istic.aco.editor.commands;

import fr.istic.aco.editor.UserInterface;

/**
 * Command to quit the editor
 */
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
