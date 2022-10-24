package fr.istic.aco.editor;

import java.io.InputStream;

public interface UserInterface {

    void runInvokerLoop();

    //void stopLoop();

    void setReadStream(InputStream inputStream);

    void addCommand(String keyword, Command command);

}