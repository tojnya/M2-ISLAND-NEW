package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.application.ApplicationContext;
import org.example.application.ApplicationLoader;

public class Main {
    public static void main(String[] args) throws JsonProcessingException {
        ApplicationLoader loader = ApplicationLoader.getInstance();
        ApplicationContext context = loader.load();

        // GameLoop loop = new GameLoop(context);
        // loop.start();
    }
}