package fr.istic.aco.editor;

import fr.istic.aco.editor.mementos.EngineMemento;
import fr.istic.aco.editor.mementos.Memento;

public class EngineImpl implements Engine {

    private Selection selection;
    private StringBuffer buffer;
    private String clipboard;

    public EngineImpl() {

        this.buffer = new StringBuffer();
        this.selection = new SelectionImpl(buffer);
        this.clipboard = "";
    }

    /**
     * Provides access to the selection control object
     *
     * @return the selection object
     */
    @Override
    public Selection getSelection() {
        return this.selection;
    }

    /**
     * Provides the whole contents of the buffer, as a string
     *
     * @return a copy of the buffer's contents
     */
    @Override
    public String getBufferContents() {
        return this.buffer.toString();
    }

    /**
     * Provides the clipboard contents
     *
     * @return a copy of the clipboard's contents
     */
    @Override
    public String getClipboardContents() {
        return this.clipboard;
    }

    /**
     * Removes the text within the interval
     * specified by the selection control object,
     * from the buffer.
     */
    @Override
    public void cutSelectedText() {
        copySelectedText();
        delete();
    }

    /**
     * Copies the text within the interval
     * specified by the selection control object
     * into the clipboard.
     */
    @Override
    public void copySelectedText() {
        this.clipboard = this.buffer.substring(this.selection.getBeginIndex(), this.selection.getEndIndex());
    }

    /**
     * Replaces the text within the interval specified by the selection object with
     * the contents of the clipboard.
     */
    @Override
    public void pasteClipboard() {
        insert(this.clipboard);
    }

    /**
     * Inserts a string in the buffer, which replaces the contents of the selection
     *
     * @param s the text to insert
     */
    @Override
    public void insert(String s) {
        this.buffer.replace(this.selection.getBeginIndex(), this.selection.getEndIndex(), s);
        this.selection.setEndIndex(this.selection.getBeginIndex() + s.length());
        this.selection.setBeginIndex(this.selection.getBeginIndex() + s.length());
    }

    /**
     * Removes the contents of the selection in the buffer
     */
    @Override
    public void delete() {
        this.buffer.delete(this.selection.getBeginIndex(), this.selection.getEndIndex());
        this.selection.setBeginIndex(this.selection.getBeginIndex());
        this.selection.setEndIndex(this.selection.getBeginIndex());
    }

    /**
     * Display the Engine current state
     *
     * @return the buffer and bounds of the selection as a String
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i <= this.getBufferContents().length(); i++) {
            if (i == this.selection.getBeginIndex()) result.append("|>");
            if (i == this.selection.getEndIndex()) result.append("<|");
            if (i < this.getBufferContents().length()) result.append(this.buffer.charAt(i));
        }
        return result.toString();
    }

    @Override
    public void setMemento(Memento m) {
        EngineMemento em = (EngineMemento) m;
        this.buffer = new StringBuffer();
        this.selection = new SelectionImpl(buffer);
        insert(em.getBufferContent());
        this.selection.setEndIndex(em.getEndIndex());
        this.selection.setBeginIndex(em.getBeginIndex());
    }

    @Override
    public Memento getMemento() {
        return new EngineMemento(getBufferContents(), getSelection().getBeginIndex(), getSelection().getEndIndex());
    }
}