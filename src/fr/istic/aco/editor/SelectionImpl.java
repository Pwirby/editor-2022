package fr.istic.aco.editor;

public class SelectionImpl implements Selection {
    private int beginIndex;
    private int endIndex;
    private StringBuffer buffer;
    private int bufferBeginIndex;

    /**
     * The constructor to a new {@link SelectionImpl} object
     * @param buffer
     */
    public SelectionImpl(StringBuffer buffer) {
        this(buffer, 0);
    }

    /**
     * The constructor to a new {@link SelectionImpl} object
     * @param buffer the content of the editor
     * @param bufferBeginIndex the minimum of the selection beginIndex
     */
    public SelectionImpl(StringBuffer buffer, int bufferBeginIndex) {
        if (bufferBeginIndex < 0)
            throw new IllegalArgumentException("Buffer begin index must be >= 0");
        if (bufferBeginIndex > buffer.length())
            throw new IllegalArgumentException("Buffer begin index cannot be bigger than buffer length");

        this.buffer = buffer;
        this.bufferBeginIndex = this.beginIndex = this.endIndex = bufferBeginIndex;
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
     * @param beginIndex the new index where the selection will begin
     * @throws IllegalArgumentException if the beginIndex is out of buffer's bounds
     */
    @Override
    public void setBeginIndex(int beginIndex) {
        checkIndexValidity(beginIndex);
        if (beginIndex > this.endIndex) {
            throw new IllegalArgumentException("Begin index cannot be bigger than end index");
        }
        this.beginIndex = beginIndex;
    }

    /**
     * Changes the value of the end index of the selection
     *
     * @param endIndex the new index where the selection will end
     * @throws IllegalArgumentException if the beginIndex is out of buffer's bounds
     */
    @Override
    public void setEndIndex(int endIndex) {
        checkIndexValidity(endIndex);
        if (endIndex < this.beginIndex) {
            throw new IllegalArgumentException("End index cannot be bigger than begin index");
        }
        this.endIndex = endIndex;
    }

    /**
     * Checks if the index is in the bounds of the buffer
     *
     * @param index the index to verify
     * @throws IllegalArgumentException if the index is out of buffer's bound
     */
    private void checkIndexValidity(int index) {
        if (index < this.getBufferBeginIndex()) {
            throw new IllegalArgumentException("Index cannot be lower than buffer beginning index");
        }
        if (index > this.getBufferEndIndex()) {
            throw new IllegalArgumentException("Index cannot be bigger than buffer end index");
        }
    }
}

