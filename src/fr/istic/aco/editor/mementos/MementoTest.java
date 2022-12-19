package fr.istic.aco.editor.mementos;

import fr.istic.aco.editor.*;
import fr.istic.aco.editor.commands.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MementoTest {
    String string1 = "the quick brown fox jumps over the lazy dog";
    private Engine engine;
    private UserInterface userInterface;
    private Recorder recorder;
    private UndoManager undoManager;
    private Command insert, copy, cut, paste;
    private Command moveBegin, moveEnd, selectAll, extendLeft, extendRight, moveLeft, moveRight;
    private Command undo, redo;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        engine = new EngineImpl();
        recorder = new RecorderImpl();
        undoManager = new UndoManager(engine);
        userInterface = new UserInterfaceImpl(engine, recorder, undoManager);

        undo = new UndoCommand(undoManager);
        redo = new RedoCommand(undoManager);

        insert = new InsertCommand(engine, userInterface, recorder, undoManager);
        copy = new CopyCommand(engine, recorder, undoManager);
        cut = new CutCommand(engine, recorder, undoManager);
        paste = new PasteCommand(engine, userInterface, recorder, undoManager);

        selectAll = new SelectAllCommand(engine, recorder, undoManager);
        moveLeft = new MoveLeftSelectionCommand(engine, recorder, undoManager);
        moveRight = new MoveRightSelectionCommand(engine, recorder, undoManager);
        extendLeft = new ExtendLeftSelectionCommand(engine, recorder, undoManager);
        extendRight = new ExtendRightSelectionCommand(engine, recorder, undoManager);
        moveBegin = new MoveBeginSelectionCommand(engine, recorder, undoManager);
        moveEnd = new MoveEndSelectionCommand(engine, recorder, undoManager);
        // the quick brown fox jumps over the lazy dog|><|
        engine.insert(string1);
    }

    @Test
    @DisplayName("Insert a text inside the engine's buffer twice using replay")
    void InsertCommand() {
        userInterface.setTextToInsert(string1);
        recorder.start();
        // the quick brown fox jumps over the lazy dogthe quick brown fox jumps over the lazy dog|><|
        insert.execute();
        recorder.stop();
        // the quick brown fox jumps over the lazy dogthe quick brown fox jumps over the lazy dogthe quick brown fox jumps over the lazy dog|><|
        recorder.replay();

        assertEquals(string1 + string1 + string1, engine.getBufferContents());
    }

    @Test
    @DisplayName("Copy the paste the content of the clipboard inside the buffer twice")
    void CopyPasteCommandTest() {
        // |>the quick brown fox jumps over the lazy dog<|
        selectAll.execute();
        copy.execute();
        // the quick brown fox jumps over the lazy dog|><|
        moveEnd.execute();

        recorder.start();
        // the quick brown fox jumps over the lazy dogthe quick brown fox jumps over the lazy dog|><|
        paste.execute();
        recorder.stop();
        // the quick brown fox jumps over the lazy dogthe quick brown fox jumps over the lazy dogthe quick brown fox jumps over the lazy dog|><|
        recorder.replay();

        assertEquals(string1 + string1 + string1, engine.getBufferContents());
        assertEquals(string1, engine.getClipboardContents());
    }

    @Test
    @DisplayName("Cut then paste the content of the clipboard inside the buffer twice")
    void CutPasteCommandTest() {
        // |>the quick brown fox jumps over the lazy dog<|
        selectAll.execute();
        // |><|
        cut.execute();

        recorder.start();
        // the quick brown fox jumps over the lazy dog|><|
        paste.execute();
        recorder.stop();
        // the quick brown fox jumps over the lazy dogthe quick brown fox jumps over the lazy dog|><|
        recorder.replay();

        assertEquals(string1 + string1, engine.getBufferContents());
        assertEquals(string1, engine.getClipboardContents());
    }

    @Test
    @DisplayName("Extend the selection to the left by one character twice")
    void ExtendLeftCommandTest() {
        recorder.start();
        // the quick brown fox jumps over the lazy do|>g<|
        extendLeft.execute();
        recorder.stop();
        // the quick brown fox jumps over the lazy d|>og<|
        recorder.replay();
        assertEquals(engine.getBufferContents().length() - 2, engine.getSelection().getBeginIndex());
    }

    @Test
    @DisplayName("Extend the selection to the right by one character twice")
    void ExtendRightCommandTest() {
        // |><|the quick brown fox jumps over the lazy dog
        moveBegin.execute();
        recorder.start();
        // |>t<|he quick brown fox jumps over the lazy dog
        extendRight.execute();
        recorder.stop();
        // |>th<|e quick brown fox jumps over the lazy dog
        recorder.replay();
        assertEquals(engine.getSelection().getBufferBeginIndex() + 2, engine.getSelection().getEndIndex());
    }

    @Test
    @DisplayName("Move the selection to the begin of the buffer")
    void MoveBeginSelectionCommandTest() {
        recorder.start();
        // |><|the quick brown fox jumps over the lazy dog
        moveBegin.execute();
        recorder.stop();
        // the quick brown fox jumps over the lazy dog|><|
        moveEnd.execute();
        // |><|the quick brown fox jumps over the lazy dog
        recorder.replay();

        assertEquals(0, engine.getSelection().getBeginIndex());
        assertEquals(0, engine.getSelection().getEndIndex());
    }

    @Test
    @DisplayName("Move the selection to the end of the buffer")
    void MoveEndSelectionCommandTest() {
        recorder.start();
        // the quick brown fox jumps over the lazy dog|><|
        moveEnd.execute();
        recorder.stop();
        // |><|the quick brown fox jumps over the lazy dog
        moveBegin.execute();
        // the quick brown fox jumps over the lazy dog|><|
        recorder.replay();

        assertEquals(engine.getSelection().getBufferEndIndex(), engine.getSelection().getBeginIndex());
        assertEquals(engine.getSelection().getBufferEndIndex(), engine.getSelection().getEndIndex());
    }

    @Test
    @DisplayName("Move the selection to the left by one character twice")
    void MoveLeftSelectionCommandTest() {
        recorder.start();
        // the quick brown fox jumps over the lazy do|><|g
        moveLeft.execute();
        recorder.stop();
        // the quick brown fox jumps over the lazy d|><|og
        recorder.replay();

        assertEquals(engine.getSelection().getBufferEndIndex() - 2, engine.getSelection().getBeginIndex());
        assertEquals(engine.getSelection().getBufferEndIndex() - 2, engine.getSelection().getEndIndex());
    }

    @Test
    @DisplayName("Move the selection to the left by one character twice")
    void MoveRightSelectionCommandTest() {
        // |><|the quick brown fox jumps over the lazy dog
        moveBegin.execute();
        recorder.start();
        // t|><|he quick brown fox jumps over the lazy dog
        moveRight.execute();
        recorder.stop();
        // th|><|e quick brown fox jumps over the lazy dog
        recorder.replay();

        assertEquals(engine.getSelection().getBufferBeginIndex() + 2, engine.getSelection().getBeginIndex());
        assertEquals(engine.getSelection().getBufferBeginIndex() + 2, engine.getSelection().getEndIndex());
    }

    @Test
    @DisplayName("The selection should reselect the entire content of the buffer")
    void SelectAllCommandTest() {
        recorder.start();
        // |>the quick brown fox jumps over the lazy dog<|
        selectAll.execute();
        recorder.stop();
        // the quick brown fox jumps over the lazy dog|><|
        moveEnd.execute();
        // |>the quick brown fox jumps over the lazy dog<|
        recorder.replay();
        assertEquals(engine.getSelection().getBufferBeginIndex(), engine.getSelection().getBeginIndex());
        assertEquals(engine.getSelection().getBufferEndIndex(), engine.getSelection().getEndIndex());
    }

    @Test
    @DisplayName("Test the undo command")
    void UndoCommandTest() {
        // |>the quick brown fox jumps over the lazy dog<|
        selectAll.execute();
        // |><|
        cut.execute();
        // |>the quick brown fox jumps over the lazy dog<|
        undo.execute();
        assertEquals(engine.getSelection().getBeginIndex(), engine.getSelection().getBufferBeginIndex());
        assertEquals(engine.getSelection().getEndIndex(), engine.getSelection().getBufferEndIndex());
        assertEquals(string1, engine.getBufferContents());
        assertEquals(string1, engine.getClipboardContents());
    }

    @Test
    @DisplayName("Test the redo command")
    void RedoCommandTest() {
        // |>the quick brown fox jumps over the lazy dog<|
        selectAll.execute();
        copy.execute();
        // the quick brown fox jumps over the lazy dog|><|
        moveEnd.execute();
        // the quick brown fox jumps over the lazy dogthe quick brown fox jumps over the lazy dog|><|
        paste.execute();
        // the quick brown fox jumps over the lazy dog|><|
        undo.execute();
        // the quick brown fox jumps over the lazy dogthe quick brown fox jumps over the lazy dog|><|
        redo.execute();
        assertEquals(engine.getSelection().getBeginIndex(), engine.getSelection().getBufferEndIndex());
        assertEquals(engine.getSelection().getEndIndex(), engine.getSelection().getBufferEndIndex());
        assertEquals(string1+string1, engine.getBufferContents());
        assertEquals(string1, engine.getClipboardContents());
    }
}
