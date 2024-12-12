package org.example;

import org.example.application.ApplicationContext;
import org.example.application.ApplicationLoader;
import org.example.application.GameLoop;

public class Main {
    public static void main(String[] args) {
        ApplicationLoader loader = ApplicationLoader.getInstance();
        ApplicationContext context = loader.load();
        GameLoop loop = new GameLoop(context);

        Thread thread = new Thread(loop);
        thread.start();
    }
}