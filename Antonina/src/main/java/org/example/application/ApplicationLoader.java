package org.example.application;

public class ApplicationLoader {

    private static ApplicationLoader INSTANCE;

    private ApplicationLoader() {
    }

    public static ApplicationLoader getInstance() {
        if (INSTANCE == null) {
            return new ApplicationLoader();
        } else {
            return INSTANCE;
        }
    }

    public ApplicationContext load() {
        ApplicationContext context = ApplicationContext.getInstance();
        context.setGameField(100,20);
        return context;
    }
}
