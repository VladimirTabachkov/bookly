import service.Library;
import ui.ConsoleMenu;

public class Main {
    public static void main(String[] args) {
        Library library = Library.createLibrary();
        library.init();
        ConsoleMenu consoleMenu = new ConsoleMenu();
        consoleMenu.start();
    }
}