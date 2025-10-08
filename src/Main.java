import service.Library;
import ui.ConsoleMenu;

public class Main {
    public static void main(String[] args) {
//        Library.createLibrary();
        ConsoleMenu consoleMenu = new ConsoleMenu();
        consoleMenu.start();
    }
}