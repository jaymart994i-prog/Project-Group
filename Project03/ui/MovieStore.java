package ui;

public class MovieStore {
    private Media[] items;
    private int itemCount;

    public MovieStore(int maxSize) {
        items = new Media[maxSize];
        itemCount = 0;
    }

    public void addItem(Media item) {
        if (itemCount < items.length) {
            items[itemCount] = item;
            itemCount++;
        }
    }

    public Media[] getItems() {
        return items;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void showAllMovies() {
        for (int i = 0; i < itemCount; i++) {
            System.out.println(items[i].showDetail());
        }
    }

    public boolean processRent(String id) {
        for (int i = 0; i < itemCount; i++) {
            if (items[i].getId().equals(id)) {
                if (items[i] instanceof Movie) {
                    Movie movie = (Movie) items[i];
                    return movie.rentItem();
                }
            }
        }
        return false;
    }

    public boolean processReturn(String id) {
        for (int i = 0; i < itemCount; i++) {
            if (items[i].getId().equals(id)) {
                if (items[i] instanceof Movie) {
                    Movie movie = (Movie) items[i];
                    return movie.returnItem();
                }
            }
        }
        return false;
    }

    public boolean processBuy(String id) {
        for (int i = 0; i < itemCount; i++) {
            if (items[i].getId().equals(id)) {
                if (items[i] instanceof Movie) {
                    Movie movie = (Movie) items[i];
                    return movie.buyItem();
                }
            }
        }
        return false;
    }

    public void showMovieStatus(String id) {
        for (int i = 0; i < itemCount; i++) {
            if (items[i].getId().equals(id)) {
                System.out.println("Status: " + items[i].getStatus());
            }
        }
    }
}