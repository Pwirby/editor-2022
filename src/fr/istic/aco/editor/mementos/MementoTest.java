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
    private Command insert, copy, cut, paste, quit;
    private Command moveBegin, moveEnd, selectAll, extendLeft, extendRight, moveLeft, moveRight;


    @org.junit.jupiter.api.BeforeEach
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
    }

    @Test
    @DisplayName("Insert a text inside the engine's buffer")
    void InsertCommand(){
        userInterface.setTextToInsert(string1);
        recorder.start();
        // the quick brown fox jumps over the lazy dog|><|
        insert.execute();
        recorder.stop();
        // the quick brown fox jumps over the lazy dogthe quick brown fox jumps over the lazy dog|><|
        recorder.replay();

        assertEquals(string1+string1, engine.getBufferContents());
    }
    @Test
    @DisplayName("Paste the content of the clipboard inside the buffer")
    void CopyPasteCommandTest(){
        userInterface.setTextToInsert(string1);
        // the quick brown fox jumps over the lazy dog|><|
        insert.execute();
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

        assertEquals(string1+string1+string1, engine.getBufferContents());
        assertEquals(string1, engine.getClipboardContents());
    }
    @Test
    @DisplayName("Paste the content of the clipboard inside the buffer")
    void CutPasteCommandTest(){
        userInterface.setTextToInsert(string1);
        // the quick brown fox jumps over the lazy dog|><|
        insert.execute();
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

        assertEquals(string1+string1, engine.getBufferContents());
        assertEquals(string1, engine.getClipboardContents());
    }
}
