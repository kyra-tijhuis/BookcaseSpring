package database.goodreadsAPI;

import java.util.List;

/**
 * Created by Kyra on 19/04/2016.
 */
class Results {

    private List<Work> work;

    public List<Work> getWork() {
        return work;
    }

    public void setWork(List<Work> work) {
        this.work = work;
    }

    @Override
    public String toString() {
        return "Results :D";
    }
}
