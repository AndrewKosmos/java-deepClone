import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    static class Man {
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

    public static void main(String args[]) {
        testCloner();
    }


    static void testCloner() {
        Cloner cloner = new Cloner();
        ArrayList<String> lst = new ArrayList<>();
        lst.add("book1");
        lst.add("book2");
        Man man1 = new Man("Andrew",23, lst);
        System.out.println("Man1 == " + man1);
        Man man2 = cloner.deepClone(man1);
        man1.age = 55;
        man1.name = "Petro";
        man1.favoriteBooks.set(0, "Bla");
        man1.favoriteBooks.set(1, "Bla");
        System.out.println("Man1 moded == " + man1);
        if (man2 == null) {
            System.out.println("man2 = null");
        }
        else {
            System.out.println("Man2 == " + man2);
        }
    }

}
