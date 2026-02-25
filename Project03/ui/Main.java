package ui;

public class Main {
    public static void main(String[] args) {
        MovieStore store = new MovieStore(100);
        store.addItem(new Movie("M01", "The Matrix", 50.0, 300.0));
        store.addItem(new Movie("M02", "Inception", 60.0, 350.0));
        store.addItem(new Movie("M03", "Interstellar", 55.0, 320.0));

        new page(store);
    }
}