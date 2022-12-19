package fr.istic.aco.editor;

import fr.istic.aco.editor.mementos.EngineMemento;
import fr.istic.aco.editor.mementos.Memento;

import java.util.LinkedList;
import java.util.List;

public class UndoManager {
    private final List<Memento> pastStates;
    private final List<Memento> futureStates;
    public Engine engine;

    public UndoManager(Engine engine){
        pastStates = new LinkedList<>();
        futureStates = new LinkedList<>();
        this.engine = engine;
    }

    public void store(){
        pastStates.add(engine.getMemento());
        futureStates.clear();
        display();
    }

    public void undo(){
        if(!pastStates.isEmpty()){
            futureStates.add(engine.getMemento());
            engine.setMemento(pastStates.get(pastStates.size()-1));
            pastStates.remove(pastStates.size()-1);
        }

        display();
    }

    public void redo(){
        if(!futureStates.isEmpty()){
            pastStates.add(engine.getMemento());
            engine.setMemento(futureStates.get(futureStates.size()-1));
            futureStates.remove(futureStates.size()-1);

        }
        display();
    }

    void display(){
        System.out.println("Past");
        for (Memento m : pastStates) {
            System.out.println("Past T : " + ((EngineMemento) m).getBufferContent() + "  B : " + ((EngineMemento) m).getBeginIndex() + "  E : " + ((EngineMemento) m).getEndIndex());
        }
        System.out.println("Future");
        for (Memento m : futureStates) {
            System.out.println("Future T : " + ((EngineMemento) m).getBufferContent() + "  B : " + ((EngineMemento) m).getBeginIndex() + "  E : " + ((EngineMemento) m).getEndIndex());
        }
    }
}