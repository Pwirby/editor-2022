package fr.istic.aco.editor.commands;

import fr.istic.aco.editor.Engine;
import fr.istic.aco.editor.EngineImpl;
import fr.istic.aco.editor.UserInterface;
import fr.istic.aco.editor.UserInterfaceImpl;
import fr.istic.aco.editor.mementos.Recorder;
import fr.istic.aco.editor.mementos.RecorderImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class CommandTest {
    String string1 = "the quick brown fox jumps over the lazy dog";
    private Engine engine;
    private UserInterface userInterface;
    private Recorder recorder;
    private Command insert, copy, cut, paste, quit;
    private Command moveBegin, moveEnd, selectAll, extendLeft, extendRight, moveLeft, moveRight;

    @BeforeEach
    void setUp() {
        engine = new EngineImpl();
        recorder = new RecorderImpl();
        userInterface = new UserInterfaceImpl(engine, recorder);

        insert = new InsertCommand(engine, userInterface, recorder);
        copy = new CopyCommand(engine, recorder);
        cut = new CutCommand(engine, recorder);
        paste = new PasteCommand(engine, userInterface, recorder);
        quit = new QuitCommand(userInterface);

        selectAll = new SelectAllCommand(engine, recorder);
        moveLeft = new MoveLeftSelectionCommand(engine, recorder);
        moveRight = new MoveRightSelectionCommand(engine, recorder);
        extendLeft = new ExtendLeftSelectionCommand(engine, recorder);
        extendRight = new ExtendRightSelectionCommand(engine, recorder);
        moveBegin = new MoveBeginSelectionCommand(engine, recorder);
        moveEnd = new MoveEndSelectionCommand(engine, recorder);
        // the quick brown fox jumps over the lazy dog|><|
        engine.insert(string1);
    }

    @Test
    @DisplayName("Text within the selection should be copied in clipboard")
    void CopyCommandTest() {
        // |>the quick brown fox jumps over the lazy dog<|
        selectAll.execute();
        copy.execute();

        assertEquals(string1, engine.getClipboardContents());
    }

    @Test
    @DisplayName("Text within selection should be copied in clipboard and removed in the buffer")
    void CutCommandTest() {
        // |>the quick brown fox jumps over the lazy dog<|
        selectAll.execute();
        // |><|
        cut.execute();

        assertEquals(string1, engine.getClipboardContents());
        assertEquals("", engine.getBufferContents());
    }

    @Test
    @DisplayName("Extend the selection to the left by one character")
    void ExtendLeftCommandTest() {
        // the quick brown fox jumps over the lazy do|>g<|
        extendLeft.execute();

        assertEquals(engine.getBufferContents().length() - 1, engine.getSelection().getBeginIndex());
    }

    @Test
    @DisplayName("Extend the selection to the right by one character")
    void ExtendRightCommandTest() {
        // |><|the quick brown fox jumps over the lazy dog
        moveBegin.execute();
        // |>t<|he quick brown fox jumps over the lazy dog
        extendRight.execute();

        assertEquals(engine.getSelection().getBufferBeginIndex() + 1, engine.getSelection().getEndIndex());
    }


    @Test
    @DisplayName("Insert a text inside the engine's buffer")
    void InsertCommandTest() {
        // the quick brown fox jumps over the lazy dog|><|
        userInterface.setTextToInsert(string1);
        // the quick brown fox jumps over the lazy dogthe quick brown fox jumps over the lazy dog|><|
        insert.execute();

        assertEquals(string1+string1, engine.getBufferContents());
    }

    @Test
    @DisplayName("Move the selection to the begin of the buffer")
    void MoveBeginSelectionCommandTest() {
        // |><|the quick brown fox jumps over the lazy dog
        moveBegin.execute();

        assertEquals(0, engine.getSelection().getBeginIndex());
        assertEquals(0, engine.getSelection().getEndIndex());
    }

    @Test
    @DisplayName("Move the selection to the end of the buffer")
    void MoveEndSelectionCommandTest() {
        // |><|the quick brown fox jumps over the lazy dog
        moveBegin.execute();
        // the quick brown fox jumps over the lazy dog|><|
        moveEnd.execute();

        assertEquals(engine.getSelection().getBufferEndIndex(), engine.getSelection().getBeginIndex());
        assertEquals(engine.getSelection().getBufferEndIndex(), engine.getSelection().getEndIndex());
    }

    @Test
    @DisplayName("Move the selection to the left by one character")
    void MoveLeftSelectionCommandTest() {
        // the quick brown fox jumps over the lazy do|><|g
        moveLeft.execute();

        assertEquals(engine.getSelection().getBufferEndIndex() - 1, engine.getSelection().getBeginIndex());
        assertEquals(engine.getSelection().getBufferEndIndex() - 1, engine.getSelection().getEndIndex());
    }

    @Test
    @DisplayName("Move the selection to the left by one character")
    void MoveRightSelectionCommandTest() {
        // |><|the quick brown fox jumps over the lazy dog
        moveBegin.execute();
        // t|><|he quick brown fox jumps over the lazy dog
        moveRight.execute();

        assertEquals(engine.getSelection().getBufferBeginIndex() + 1, engine.getSelection().getBeginIndex());
        assertEquals(engine.getSelection().getBufferBeginIndex() + 1, engine.getSelection().getEndIndex());
    }

    @Test
    @DisplayName("Paste the content of the clipboard inside the buffer")
    void PasteCommandTest() {
        // |>the quick brown fox jumps over the lazy dog<|
        selectAll.execute();
        // |><|
        cut.execute();
        // the quick brown fox jumps over the lazy dog|><|
        paste.execute();

        assertEquals(string1, engine.getBufferContents());
        assertEquals(string1, engine.getClipboardContents());
    }

    @Test
    @DisplayName("Stop the program")
    void QuitCommandTest() {
        quit.execute();

        assertTrue(userInterface.getStopLoop());
    }

    @Test
    @DisplayName("The selection should select the entire content of the buffer")
    void SelectAllCommandTest() {
        // |>the quick brown fox jumps over the lazy dog<|
        selectAll.execute();

        assertEquals(engine.getSelection().getBufferBeginIndex(), engine.getSelection().getBeginIndex());
        assertEquals(engine.getSelection().getBufferEndIndex(), engine.getSelection().getEndIndex());
    }
}
