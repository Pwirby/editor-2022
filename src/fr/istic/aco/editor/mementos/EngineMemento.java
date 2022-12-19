package fr.istic.aco.editor.mementos;

public class EngineMemento implements Memento{
    private final String bufferContent;
    private final int beginIndex;
    private final int endIndex;

    public EngineMemento(String bufferContent, int beginIndex, int endIndex) {
        this.bufferContent = bufferContent;
        this.beginIndex = beginIndex;
        this.endIndex = endIndex;
    }

    public String getBufferContent() {
        return bufferContent;
    }

    public int getBeginIndex() {
        return beginIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }

    @Override
    public String getState() {
        return null;
    }
}
