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
        Selection selection = engine.getSelection();
        assertEquals(selection.getBufferBeginIndex(), selection.getBeginIndex());
        assertEquals("", engine.getBufferContents());
    }

    @Test
    void getBufferContents() {
        engine.insert(string1);
        assertEquals(string1, engine.getBufferContents());
    }

    @Test
    void getClipboardContents() {
        engine.insert(string1);
        Selection selection = engine.getSelection();
        selection.setBeginIndex(engine.getSelection().getBufferBeginIndex());
        engine.copySelectedText();
        assertEquals(string1, engine.getClipboardContents());
        assertEquals(string1, engine.getBufferContents());
    }

    @Test
    void cutSelectedText() {
        engine.insert(string1);
        Selection selection = engine.getSelection();
        selection.setBeginIndex(engine.getSelection().getBufferBeginIndex());
        engine.cutSelectedText();
        assertEquals(string1, engine.getClipboardContents());
        assertEquals("", engine.getBufferContents());
    }

    @Test
    void copySelectedText() {
        engine.insert(string1);
        Selection selection = engine.getSelection();
        selection.setBeginIndex(engine.getSelection().getBufferBeginIndex());
        engine.copySelectedText();
        assertEquals(string1, engine.getClipboardContents());
        assertEquals(string1, engine.getBufferContents());
    }

    @Test
    void pasteClipboard() {
        engine.insert(string1);
        Selection selection = engine.getSelection();
        selection.setBeginIndex(engine.getSelection().getBufferBeginIndex());
        engine.copySelectedText();
        selection.setEndIndex(0);
        engine.pasteClipboard();
        assertEquals(string1, engine.getClipboardContents());
        assertEquals(string1+string1, engine.getBufferContents());
    }
}
