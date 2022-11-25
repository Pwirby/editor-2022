package fr.istic.aco.editor.commands;

/**
 * Common interface for concrete commands
 */
@FunctionalInterface
public interface Command {
    /**
     * Call an operation on a receiver
     */
    void execute();
}
