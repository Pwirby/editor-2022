package fr.istic.aco.editor.commands;

import fr.istic.aco.editor.UserInterface;
import fr.istic.aco.editor.mementos.Memento;

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
    @Override
    public void setMemento(Memento m) {

    }

    @Override
    public Memento getMemento() {
        return null;
    }
}
