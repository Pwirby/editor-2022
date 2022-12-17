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
    Command insert, copy, cut, paste, selectAll;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        engine = new EngineImpl();
        engine = new EngineImpl();
        recorder = new RecorderImpl();
        userInterface = new UserInterfaceImpl(engine, recorder);

        insert = new InsertCommand(engine, userInterface, recorder);
        paste = new PasteCommand(engine, userInterface, recorder);
        selectAll = new SelectAllCommand(engine, recorder);
        copy = new CopyCommand(engine, recorder);
        cut = new CutCommand(engine, recorder);
    }

    @Test
    @DisplayName("Insert a text inside the engine's buffer")
    void InsertCommand(){
        userInterface.setTextToInsert(string1);
        recorder.start();
        insert.execute();
        recorder.stop();
        recorder.replay();

        assertEquals(string1+string1, engine.getBufferContents());
    }
    @Test
    @DisplayName("Paste the content of the clipboard inside the buffer")
    void CopyPasteCommandTest(){
        userInterface.setTextToInsert(string1);
        insert.execute();
        selectAll.execute();
        copy.execute();

        recorder.start();
        paste.execute();
        recorder.stop();
        recorder.replay();

        assertEquals(string1+string1+string1, engine.getBufferContents());
        assertEquals(string1, engine.getClipboardContents());
    }
    @Test
    @DisplayName("Paste the content of the clipboard inside the buffer")
    void CutPasteCommandTest(){
        userInterface.setTextToInsert(string1);
        insert.execute();
        selectAll.execute();
        cut.execute();

        recorder.start();
        paste.execute();
        recorder.stop();
        recorder.replay();

        assertEquals(string1+string1, engine.getBufferContents());
        assertEquals(string1, engine.getClipboardContents());
    }
}
