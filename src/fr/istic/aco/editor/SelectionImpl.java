package fr.istic.aco.editor;

public class SelectionImpl implements Selection {
    private int beginIndex;
    private int endIndex;
    private StringBuffer buffer;
    private int bufferBeginIndex;

    public SelectionImpl(StringBuffer buffer) {
        this.beginIndex = this.endIndex = this.bufferBeginIndex = 0;
        this.buffer = buffer;
    }

    public SelectionImpl(StringBuffer buffer, int beginIndex, int endIndex) {
        this.buffer = buffer;
        this.bufferBeginIndex = 0;
        if (beginIndex > endIndex || beginIndex < this.getBeginIndex() || endIndex > this.getBufferEndIndex()) {
            throw new IndexOutOfBoundsException();
        }
        this.endIndex = endIndex;
        this.beginIndex = beginIndex;
    }

    /**
     * Provides the index of the first character designated
     * by the selection.
     *
     * @return the beginning index
     */
    @Override
    public int getBeginIndex() {
        return this.beginIndex;
    }

    /**
     * Provides the index of the first character
     * after the last character designated
     * by the selection.
     *
     * @return the end index
     */
    @Override
    public int getEndIndex() {
        return this.endIndex;
    }

    /**
     * Provides the index of the first character in the buffer
     *
     * @return the buffer's begin index
     */
    @Override
    public int getBufferBeginIndex() {
        return this.bufferBeginIndex;
    }

    /**
     * Provides the index of the first "virtual" character
     * after the end of the buffer
     *
     * @return the post end buffer index
     */
    @Override
    public int getBufferEndIndex() {
        return this.buffer.length();
    }

    /**
     * Changes the value of the start index of the selection
     *
     * @param beginIndex@throws IndexOutOfBoundsException if the beginIndex is out of bounds
     */
    @Override
    public void setBeginIndex(int beginIndex) {
        if (beginIndex < this.getBufferBeginIndex() || beginIndex > this.getBufferEndIndex()) {
            throw new IndexOutOfBoundsException();
        } else {
            if (beginIndex > this.endIndex) {
                this.beginIndex = this.endIndex;
                this.endIndex = beginIndex;
            } else {
                this.beginIndex = beginIndex;
            }
        }
    }

    /**
     * Changes the value of the end index of the selection
     *
     * @param endIndex@throws IndexOutOfBoundsException if the beginIndex is out of bounds
     */
    @Override
    public void setEndIndex(int endIndex) {
        if (endIndex < this.getBufferBeginIndex() || endIndex > this.getBufferEndIndex()) {
            throw new IndexOutOfBoundsException();
        } else {
            if (endIndex < this.beginIndex) {
                this.endIndex = this.beginIndex;
                this.beginIndex = endIndex;
            } else {
                this.endIndex = endIndex;
            }
        }
    }
}
