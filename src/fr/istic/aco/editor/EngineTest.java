package fr.istic.aco.editor;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EngineTest {
    String string1 = "the quick brown fox jumps over the lazy dog";
    private Engine engine;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        engine = new EngineImpl();
    }

    private void todo() {
        fail("Unimplemented test");
    }

    @Test
    @DisplayName("Buffer must be empty after initialisation")
    void getSelection() {
        //|><|
        Selection selection = engine.getSelection();
        assertEquals(selection.getBufferBeginIndex(), selection.getBeginIndex());
        assertEquals("", engine.getBufferContents());
    }

    @Test
    void getBufferContents() {
        //the quick brown fox jumps over the lazy dog|><|
        engine.insert(string1);
        assertEquals(string1, engine.getBufferContents());
    }

    @Test
    void getClipboardContents() {
        //the quick brown fox jumps over the lazy dog|><|
        engine.insert(string1);
        Selection selection = engine.getSelection();
        //|>the quick brown fox jumps over the lazy dog<|
        selection.setBeginIndex(engine.getSelection().getBufferBeginIndex());
        engine.copySelectedText();
        assertEquals(string1, engine.getClipboardContents());
        assertEquals(string1, engine.getBufferContents());
    }

    @Test
    void cutSelectedText() {
        //the quick brown fox jumps over the lazy dog|><|
        engine.insert(string1);
        Selection selection = engine.getSelection();
        //|>the quick brown fox jumps over the lazy dog<|
        selection.setBeginIndex(engine.getSelection().getBufferBeginIndex());
        //|><|
        engine.cutSelectedText();
        assertEquals(string1, engine.getClipboardContents());
        assertEquals("", engine.getBufferContents());
    }

    @Test
    void copySelectedText() {
        //the quick brown fox jumps over the lazy dog|><|
        engine.insert(string1);
        Selection selection = engine.getSelection();
        //|>the quick brown fox jumps over the lazy dog<|
        selection.setBeginIndex(engine.getSelection().getBufferBeginIndex());
        engine.copySelectedText();
        assertEquals(string1, engine.getClipboardContents());
        assertEquals(string1, engine.getBufferContents());
    }

    @Test
    void pasteClipboard() {
        //the quick brown fox jumps over the lazy dog|><|
        engine.insert(string1);
        Selection selection = engine.getSelection();
        //|>the quick brown fox jumps over the lazy dog<|
        selection.setBeginIndex(engine.getSelection().getBufferBeginIndex());
        engine.copySelectedText();
        //the quick brown fox jumps over the lazy dog|><|
        selection.setEndIndex(0);
        //the quick brown fox jumps over the lazy dog|>the quick brown fox jumps over the lazy dog<|
        engine.pasteClipboard();
        assertEquals(string1, engine.getClipboardContents());
        assertEquals(string1+string1, engine.getBufferContents());
    }
}
