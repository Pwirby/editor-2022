package fr.istic.aco.editor;

public class EngineImpl implements Engine {
	
	private Selection selection;
	private StringBuffer buffer;
	private String clipboard;
	
	
	public EngineImpl() {
        this.selection = new SelectionImpl(buffer);
        this.buffer = new StringBuffer();
        this.clipboard = "";
	}


    /**
     * Changes the buffer content
     */
    public void setBuffer(String s) {
        delete();
        this.buffer.append(s);
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
        this.clipboard = this.buffer.substring(this.selection.getBeginIndex(),this.selection.getEndIndex());
        delete();
    }

    /**
     * Copies the text within the interval
     * specified by the selection control object
     * into the clipboard.
     */
    @Override
    public void copySelectedText() {
        int begin = this.selection.getBeginIndex(), end = this.selection.getEndIndex();
        if(end > begin){
            this.clipboard = this.buffer.substring(begin,end);
        }
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
        delete();
        this.buffer.replace(this.selection.getBeginIndex(),this.selection.getEndIndex(), s);
    }

    /**
     * Removes the contents of the selection in the buffer
     */
    @Override
    public void delete() {
        this.buffer.delete(this.selection.getBeginIndex(),this.selection.getEndIndex());
    }
}
