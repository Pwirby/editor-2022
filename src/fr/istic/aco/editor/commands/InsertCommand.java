package fr.istic.aco.editor.commands;

import fr.istic.aco.editor.Engine;
import fr.istic.aco.editor.UserInterface;
import fr.istic.aco.editor.mementos.InsertMemento;
import fr.istic.aco.editor.mementos.Memento;
import fr.istic.aco.editor.mementos.Recorder;

public class InsertCommand implements Command{
    private final Engine engine;
    private final UserInterface userInterface;
    private final Recorder recorder;

    /**
     * The command to add content to the buffer
     * @param engine
     * @param userInterface
     * @param recorder where commands are being registered
     */
    public InsertCommand(Engine engine, UserInterface userInterface, Recorder recorder) {
        this.engine = engine;
        this.userInterface = userInterface;
        this.recorder = recorder;
    }

    @Override
    public void execute() {
        engine.insert(userInterface.getTextToInsert());
        recorder.save(this);
    }

    @Override
    public void setMemento(Memento m) {
        // L'userInterface contient déjà un attribut textToInsert
        userInterface.setTextToInsert(m.getState());
    }

    @Override
    public Memento getMemento() {
        InsertMemento m = new InsertMemento();
        m.setTextToInsert(userInterface.getTextToInsert());
        return m;
    }
}
