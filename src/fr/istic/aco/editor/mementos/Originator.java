package fr.istic.aco.editor.mementos;

public interface Originator {
    /**
     * Set the memento to restore the state from
     * @param m the memento to save
     */
    void setMemento(Memento m);

    /**
     * Get the memento from which to retrieve the state
     * @return the memento saved
     */
    Memento getMemento();
}