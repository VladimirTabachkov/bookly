package ui;

import exception.InvalidSearchParamException;
import exception.InvalidSearchUserIDException;
import model.Book;
import model.User;
import service.Library;

import java.util.HashMap;
import java.util.Scanner;

public class ConsoleMenu {
    private final Scanner scanner = new Scanner(System.in);
    Library library = Library.createLibrary();

    public void start() {
        System.out.println("Добро пожаловать в электронную библиотеку!");
        displayMenu();
        while (true) {
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1" -> addBook();
                case "2" -> addUser();
                case "3" -> Library.displayBooks();
                case "4" -> Library.displayUsers();
                case "5" -> findBooks();
                case "6" -> findUsers();
                case "Q", "q" -> System.exit(0);
                default -> displayMenu();
             }
        }
    }

    private static void displayMenu() {
        System.out.println("1. Добавить книгу");
        System.out.println("2. Добавить читателя");
        System.out.println("3. Просмотр всех книг");
        System.out.println("4. Просмотр всех читателей");
        System.out.println("5. Поиск книг по: названию, автору, году");
        System.out.println("6. Поиск пользователя по ID");
        System.out.println("Q. Выход");
    }

    // Добавить книгу
    private void addBook() {
        String title, author;
        int year, totalCopies;
        try {
            title = GetValue("Название книги:");
            author = GetValue("ФИО автора:");
            year = Integer.parseInt(GetValue("Год издания:"));
            totalCopies = Integer.parseInt(GetValue("Количество копий:"));
            Library.addBook(title, author, year, totalCopies);
            System.out.println("Книга добавлена");
        } catch (NumberFormatException e) {
            System.out.println("Не верные год издания или количество копий");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    // Добавить пользователя
    private void addUser() {
        String name, email;
        try {
            name = GetValue("ФИО читателя:");
            email = GetValue("E-Mail читателя:");
            Library.addUser(name, email);
            System.out.println("Читатель добавлен");
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    // Найти книгу по параметрам. Если параметр не известен, вводим пустую строку
    private void findBooks() {
        HashMap<Integer, Book> booksFind;
        try {
            booksFind = CheckBooks();
            if (booksFind != null) {
                Library.displayBooks(booksFind);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private HashMap<Integer, Book> CheckBooks() {
        String title, author;
        int year;
        HashMap<Integer, Book> booksFind = null;
        try {
            title = GetValue("Название книги:");
            author = GetValue("ФИО автора:");
            try {
                year = Integer.parseInt(GetValue("Год издания:"));
            } catch (NumberFormatException e) {
                year = 0;
            }
            if ((title == null || title.isBlank()) && (author == null || author.isBlank()) && year == 0) {
                throw new InvalidSearchParamException();
            }
            booksFind = Library.findBooks(title, author, year);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return booksFind;
    }

    // Найти пользователя по ID.
    private void findUsers() {
        HashMap<Integer, User> usersFind;
        try {
            usersFind = CheckUser();
            if (usersFind != null) {
                Library.displayUsers(usersFind);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private HashMap<Integer, User> CheckUser() {
        HashMap<Integer, User> usersFind = null;
        int id;
        try {
            try {
                id = Integer.parseInt(GetValue("ID читателя:"));
            } catch (NumberFormatException e) {
                id = 0;
            }
            if (id == 0) {
                throw new InvalidSearchUserIDException();
            }
            usersFind = Library.findUsers(id, null, null);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return usersFind;
    }

    private String GetValue(String s) {
        System.out.print(s);
        return scanner.nextLine();
    }
}
