package fr.istic.aco.editor;

import fr.istic.aco.editor.commands.Command;

import java.io.InputStream;

public interface UserInterface {

    void runInvokerLoop();

    //void stopLoop();

    void setReadStream(InputStream inputStream);

    void addCommand(String keyword, Command command);

    void DisplayBuffer();

    void DisplayClipboard();

    void DisplayText(String text, int maxLengthOfLine);
}