package com.bovane.commandPattern;

public class CommandPatternTest {

    public static void main(String[] args) {
        Application application = new Application();
        IShow graphics = new Graphics(application);
        IShow document = new Document(application);
        User user = new User();

        user.show(document);
        user.show(graphics);
        user.show(graphics);
        user.show(graphics);

        System.out.println("------------- redo -------------");
        user.redo(3);

        System.out.println("------------- undo -------------");
        user.undo(2);
    }
}
