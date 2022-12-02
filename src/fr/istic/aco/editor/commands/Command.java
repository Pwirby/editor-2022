package fr.istic.aco.editor.commands;

import fr.istic.aco.editor.mementos.Originator;

/**
 * Common interface for concrete commands
 */
public interface Command extends Originator {
    /**
     * Call an operation on a receiver
     */
    void execute();
}
