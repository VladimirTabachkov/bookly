package model;

public class User {
    private final int id;
    private String name;
    private String email;
    private static int counter = 1;

    public User(String name, String email) {
        this.id = counter;
        this.name = name;
        this.email = email;
        counter++;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        int recNo = id;
        return recNo + "  ----  " + name + ", " + email;
    }}
