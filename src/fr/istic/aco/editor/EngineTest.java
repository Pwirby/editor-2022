package fr.istic.aco.editor;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EngineTest {
    String string1 = "the quick brown fox jumps over the lazy dog";
    private Engine engine;
    private Selection selection;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        engine = new EngineImpl();
        selection = engine.getSelection();
    }

    private void todo() {
        fail("Unimplemented test");
    }

    @Test
    @DisplayName("Buffer must be empty after initialisation")
    void getSelection() {
        //|><|
        selection = engine.getSelection();
        assertEquals(selection.getBufferBeginIndex(), selection.getBeginIndex());
        assertEquals("", engine.getBufferContents());
        assertEquals(0, selection.getBeginIndex());
        assertEquals(0, selection.getEndIndex());
    }

    @Test
    void getBufferContents() {
        //the quick brown fox jumps over the lazy dog|><|
        engine.insert(string1);
        selection = engine.getSelection();

        assertEquals(string1, engine.getBufferContents());
        assertEquals(string1.length(), selection.getBeginIndex());
        assertEquals(string1.length(), selection.getEndIndex());
    }

    @Test
    void getClipboardContents() {
        //the quick brown fox jumps over the lazy dog|><|
        engine.insert(string1);

        //|>the quick brown fox jumps over the lazy dog<|
        selection = engine.getSelection();
        selection.setBeginIndex(engine.getSelection().getBufferBeginIndex());
        engine.copySelectedText();

        assertEquals(string1, engine.getClipboardContents());
        assertEquals(string1, engine.getBufferContents());
        assertEquals(0, selection.getBeginIndex());
        assertEquals(string1.length(), selection.getEndIndex());
    }

    @Test
    @DisplayName("cutSelectedText")
    void cutSelectedText() {
        //the quick brown fox jumps over the lazy dog|><|
        engine.insert(string1);

        //|>the quick brown fox jumps over the lazy dog<|
        selection = engine.getSelection();
        selection.setBeginIndex(engine.getSelection().getBufferBeginIndex());

        //|><|
        engine.cutSelectedText();
        selection = engine.getSelection();

        assertEquals(string1, engine.getClipboardContents());
        assertEquals("", engine.getBufferContents());
        assertEquals(0, selection.getBeginIndex());
        assertEquals(0, selection.getEndIndex());
    }

    @Test
    void copySelectedText() {
        //the quick brown fox jumps over the lazy dog|><|
        engine.insert(string1);

        //|>the quick brown fox jumps over the lazy dog<|
        selection = engine.getSelection();
        selection.setBeginIndex(engine.getSelection().getBufferBeginIndex());
        engine.copySelectedText();

        assertEquals(string1, engine.getClipboardContents());
        assertEquals(string1, engine.getBufferContents());
        assertEquals(0, selection.getBeginIndex());
        assertEquals(string1.length(), selection.getEndIndex());

    }

    @Test
    void pasteClipboard() {
        //the quick brown fox jumps over the lazy dog|><|
        engine.insert(string1);

        //|>the quick brown fox jumps over the lazy dog<|
        Selection selection = engine.getSelection();
        selection.setBeginIndex(engine.getSelection().getBufferBeginIndex());
        engine.copySelectedText();

        //|><|the quick brown fox jumps over the lazy dog
        selection.setEndIndex(0);

        //the quick brown fox jumps over the lazy dog|><|the quick brown fox jumps over the lazy dog
        engine.pasteClipboard();

        selection = engine.getSelection();
        assertEquals(string1, engine.getClipboardContents());
        assertEquals(string1 + string1, engine.getBufferContents());
        assertEquals(string1.length(), selection.getBeginIndex());
        assertEquals(string1.length(), selection.getEndIndex());
    }
}
