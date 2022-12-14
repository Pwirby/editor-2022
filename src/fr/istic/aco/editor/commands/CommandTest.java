package fr.istic.aco.editor.commands;

import fr.istic.aco.editor.Engine;
import fr.istic.aco.editor.EngineImpl;
import fr.istic.aco.editor.UserInterface;
import fr.istic.aco.editor.UserInterfaceImpl;
import fr.istic.aco.editor.mementos.Recorder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class CommandTest {
    String string1 = "the quick brown fox jumps over the lazy dog";
    private Engine engine;
    private UserInterface userInterface;
    private Recorder recorder;
    private Command command;

    @BeforeEach
    void setUp() {
        engine = new EngineImpl();
        userInterface = new UserInterfaceImpl(engine, recorder);
    }

    @Test
    @DisplayName("Text within the selection should be copied in clipboard")
    void CopyCommand(){
        command = new CopyCommand(engine);

        engine.insert(string1);
        engine.getSelection().setBeginIndex(engine.getSelection().getBufferBeginIndex());
        engine.getSelection().setEndIndex(engine.getSelection().getBufferEndIndex());

        command.execute();

        assertEquals(string1, engine.getClipboardContents());
    }

    @Test
    @DisplayName("Text within selection should be copied in clipboard and removed in the buffer")
    void CutCommand(){
        command = new CutCommand(engine);

        engine.insert(string1);
        engine.getSelection().setBeginIndex(engine.getSelection().getBufferBeginIndex());
        engine.getSelection().setEndIndex(engine.getSelection().getBufferEndIndex());

        command.execute();

        assertEquals(string1, engine.getClipboardContents());
        assertEquals("", engine.getBufferContents());
    }

    @Test
    @DisplayName("Extend the selection to the left by one character")
    void ExtendLeftCommand(){
        command = new ExtendLeftSelectionCommand(engine);

        engine.insert(string1);
        command.execute();

        assertEquals(engine.getBufferContents().length()-1, engine.getSelection().getBeginIndex());
    }

    @Test
    @DisplayName("Extend the selection to the right by one character")
    void ExtendRightCommand(){
        command = new ExtendRightSelectionCommand(engine);

        engine.insert(string1);
        engine.getSelection().setBeginIndex(engine.getSelection().getBufferBeginIndex());
        engine.getSelection().setEndIndex(engine.getSelection().getBufferBeginIndex());

        command.execute();

        assertEquals(engine.getSelection().getBufferBeginIndex()+1, engine.getSelection().getEndIndex());
    }


    @Test
    @DisplayName("Insert a text inside the engine's buffer")
    void InsertCommand(){
        command = new InsertCommand(engine, userInterface, recorder);

        userInterface.setTextToInsert(string1);

        command.execute();

        assertEquals(string1, engine.getBufferContents());
    }

    @Test
    @DisplayName("Move the selection to the begin of the buffer")
    void MoveBeginSelectionCommand(){
        command = new MoveBeginSelectionCommand(engine);

        engine.insert(string1);

        command.execute();

        assertEquals(0, engine.getSelection().getBeginIndex());
        assertEquals(0, engine.getSelection().getEndIndex());
    }

    @Test
    @DisplayName("Move the selection to the end of the buffer")
    void MoveEndSelectionCommand(){
        command = new MoveEndSelectionCommand(engine);

        engine.insert(string1);
        engine.getSelection().setBeginIndex(engine.getSelection().getBufferBeginIndex());
        engine.getSelection().setEndIndex(engine.getSelection().getBufferBeginIndex());

        command.execute();

        assertEquals(engine.getSelection().getBufferEndIndex(), engine.getSelection().getBeginIndex());
        assertEquals(engine.getSelection().getBufferEndIndex(), engine.getSelection().getEndIndex());
    }

    @Test
    @DisplayName("Move the selection to the left by one character")
    void MoveLeftSelectionCommand(){
        command = new MoveLeftSelectionCommand(engine);

        engine.insert(string1);

        command.execute();

        assertEquals(engine.getSelection().getBufferEndIndex()-1, engine.getSelection().getBeginIndex());
        assertEquals(engine.getSelection().getBufferEndIndex()-1, engine.getSelection().getEndIndex());
    }

    @Test
    @DisplayName("Move the selection to the left by one character")
    void MoveRightSelectionCommand(){
        command = new MoveRightSelectionCommand(engine);

        engine.insert(string1);
        engine.getSelection().setBeginIndex(engine.getSelection().getBufferBeginIndex());
        engine.getSelection().setEndIndex(engine.getSelection().getBufferBeginIndex());

        command.execute();

        assertEquals(engine.getSelection().getBufferBeginIndex()+1, engine.getSelection().getBeginIndex());
        assertEquals(engine.getSelection().getBufferBeginIndex()+1, engine.getSelection().getEndIndex());
    }

    @Test
    @DisplayName("Paste the content of the clipboard inside the buffer")
    void PasteCommand(){
        command = new PasteCommand(engine, userInterface, recorder);

        engine.insert(string1);
        engine.getSelection().setBeginIndex(engine.getSelection().getBufferBeginIndex());
        engine.cutSelectedText();

        command.execute();

        assertEquals(string1, engine.getBufferContents());
        assertEquals(string1, engine.getClipboardContents());
    }

    @Test
    @DisplayName("Stop the program")
    void QuitCommand(){
        command = new QuitCommand(userInterface);

        command.execute();

        assertTrue(userInterface.getStopLoop());
    }

    @Test
    @DisplayName("The selection should select the entire content of the buffer")
    void SelectAllCommand(){
        command = new SelectAllCommand(engine);

        engine.insert(string1);

        command.execute();

        assertEquals(engine.getSelection().getBufferBeginIndex(), engine.getSelection().getBeginIndex());
        assertEquals(engine.getSelection().getBufferEndIndex(), engine.getSelection().getEndIndex());

    }
}
