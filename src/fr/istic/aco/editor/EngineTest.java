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
    @DisplayName("Selector bounds should be placed after the insertion")
    void getBufferContents() {
        //the quick brown fox jumps over the lazy dog|><|
        engine.insert(string1);
        selection = engine.getSelection();

        assertEquals(string1, engine.getBufferContents());
        assertEquals(string1.length(), selection.getBeginIndex(), selection.getEndIndex());
    }

    @Test
    @DisplayName("Clipboard should contain last copied/cut text")
    void getClipboardContents() {
        //the quick brown fox jumps over the lazy dog|><|
        engine.insert(string1);

        //|>the quick brown fox jumps over the lazy dog<|
        selection = engine.getSelection();
        selection.setBeginIndex(selection.getBufferBeginIndex());
        engine.copySelectedText();

        assertEquals(string1, engine.getClipboardContents());
        assertEquals(string1, engine.getBufferContents());
        assertEquals(selection.getBufferBeginIndex(), selection.getBeginIndex());
        assertEquals(string1.length(), selection.getEndIndex());
    }

    @Test
    @DisplayName("Cut text should be stored in clipboard and selector bounds placed where the text was")
    void cutSelectedText() {
        //the quick brown fox jumps over the lazy dog|><|
        engine.insert(string1);

        //|>the quick brown fox jumps over the lazy dog<|
        selection = engine.getSelection();
        selection.setBeginIndex(selection.getBufferBeginIndex());

        //|><|
        engine.cutSelectedText();

        assertEquals(string1, engine.getClipboardContents());
        assertEquals("", engine.getBufferContents());
        assertEquals(selection.getBufferBeginIndex(), selection.getBeginIndex());
        assertEquals(selection.getBufferBeginIndex(), selection.getEndIndex());
    }

    @Test
    @DisplayName("Copied text should be stored in clipboard and selection indexes unchanged")
    void copySelectedText() {
        //the quick brown fox jumps over the lazy dog|><|
        engine.insert(string1);

        //|>the quick brown fox jumps over the lazy dog<|
        selection = engine.getSelection();
        selection.setBeginIndex(selection.getBufferBeginIndex());
        engine.copySelectedText();

        assertEquals(string1, engine.getClipboardContents());
        assertEquals(string1, engine.getBufferContents());
        assertEquals(selection.getBufferBeginIndex(), selection.getBeginIndex());
        assertEquals(string1.length(), selection.getEndIndex());

    }

    @Test
    @DisplayName("Copy/pasting should result in the clipboard still containing the copy, and the buffer twice that text")
    void pasteClipboard() {
        //the quick brown fox jumps over the lazy dog|><|
        engine.insert(string1);

        //|>the quick brown fox jumps over the lazy dog<|
        Selection selection = engine.getSelection();
        selection.setBeginIndex(selection.getBufferBeginIndex());
        engine.copySelectedText();

        //|><|the quick brown fox jumps over the lazy dog
        selection.setEndIndex(selection.getBufferBeginIndex());

        //the quick brown fox jumps over the lazy dog|><|the quick brown fox jumps over the lazy dog
        engine.pasteClipboard();

        assertEquals(string1, engine.getClipboardContents());
        assertEquals(string1.concat(string1), engine.getBufferContents());
        assertEquals(string1.length(), selection.getBeginIndex(), selection.getEndIndex());
    }

    @Test
    @DisplayName("Setting the begin index before the buffer begin index throws an error")
    void setBeginIndex() {
        //the quick brown fox jumps over the lazy dog|><|
        engine.insert(string1);

        //|><- the quick brown fox jumps over the lazy dog<|
        Selection selection = engine.getSelection();
        assertThrows(IllegalArgumentException.class, () -> selection.setBeginIndex(selection.getBufferBeginIndex() - 1));
    }

    @Test
    @DisplayName("Setting the begin index before the buffer begin index throws an error")
    void setEndIndex() {
        //the quick brown fox jumps over the lazy dog|><|
        engine.insert(string1);

        //the quick brown fox jumps over the lazy dog|> -><|
        Selection selection = engine.getSelection();
        assertThrows(IllegalArgumentException.class, () -> selection.setEndIndex(selection.getBufferEndIndex() + 1));
    }
}
