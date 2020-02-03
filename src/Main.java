import cloners.Cloner;
import model.Man;

import java.util.ArrayList;

public class Main {

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
        man1.setAge(55);
        man1.setName("Petro");
        man1.getFavoriteBooks().set(0, "Bla");
        man1.getFavoriteBooks().set(1, "Bla");
        System.out.println("Man1 moded == " + man1);
        if (man2 == null) {
            System.out.println("man2 = null");
        }
        else {
            System.out.println("Man2 == " + man2);
        }
    }

}
