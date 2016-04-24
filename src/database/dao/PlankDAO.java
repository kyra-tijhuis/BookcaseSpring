package database.dao;

import database.model.Plank;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.ArrayList;

/**
 * Created by Kyra on 15/04/2016.
 */
@Repository
public class PlankDAO {
    @PersistenceContext
    private EntityManager em;
//    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("bookcases");

    @Transactional
    Plank getPlank(int plankID) {
        return em.find(Plank.class, plankID);
    }

    /**
     *
     * @param plankID
     * @return -1 if plank does not exist, otherwise the first empty index of the plank
     */
    int firstEmptyOnPlank(int plankID) {
        Plank plank = getPlank(plankID);
        int result = -1;

        if (plank!=null) {
            result = plank.getBooks().size();
        }
        return result;
    }

    @Transactional
    Plank createPlank(int height) {
        Plank result = new Plank();
        result.setHeight(height);
        result.setBooks(new ArrayList<>());

        em.persist(result);
        return result;
    }

    /**
     * Update the Plank parameter before calling this method!
     * @param plank the updated Plank object
     * @return updated bookcase
     */
    @Transactional
    Plank updatePlank(Plank plank) {
        Plank result = em.merge(plank);
        return result;
    }
}
