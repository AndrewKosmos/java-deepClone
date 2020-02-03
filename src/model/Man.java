package model;

import java.util.ArrayList;

public class Man {
    private String name;
    private int age;
    private ArrayList<String> favoriteBooks;

    public Man(){}

    public Man(String name, int age, ArrayList<String> favoriteBooks) {
        this.name = name;
        this.age = age;
        this.favoriteBooks = favoriteBooks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public ArrayList<String> getFavoriteBooks() {
        return favoriteBooks;
    }

    public void setFavoriteBooks(ArrayList<String> favoriteBooks) {
        this.favoriteBooks = favoriteBooks;
    }

    @Override
    public String toString() {
        String arrRes = "";
        for (String s : favoriteBooks) {
            arrRes += s + " / ";
        }
        String n = name == null ? "null" : name;
        return n + "  " + age + " and Books = {" + arrRes + "}";
    }
}
