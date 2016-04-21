package database.goodreadsAPI;

/**
 * Created by Kyra on 19/04/2016.
 */
class Work {
    private int id;
    private Best_Book best_book;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Best_Book getBest_book() {
        return best_book;
    }

    public void setBest_book(Best_Book best_book) {
        this.best_book = best_book;
    }
}
