package fr.istic.aco.editor.mementos;

public class InsertMemento implements Memento{

    private String textToInsert;

    public InsertMemento() {

    }

    @Override
    public String getState(){
        return getTextToInsert();
    }

    public String getTextToInsert() {
        return textToInsert;
    }

    public void setTextToInsert(String textToInsert) {
        this.textToInsert = textToInsert;
    }
}
