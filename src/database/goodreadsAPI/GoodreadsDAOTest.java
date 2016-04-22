package database.goodreadsAPI;

import org.junit.*;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Kyra on 22/04/2016.
 */
public class GoodreadsDAOTest {

    private GoodreadsDAO goodreadsDAO;

    @Before
    public void setUp() throws Exception {
        goodreadsDAO = new GoodreadsDAO();
    }

    @Test
    public void testEmpty() {
        assertEquals("https://s.gr-assets.com/assets/nophoto/book/111x148-bcc042a9c91a29c1d680899eff700a03.png", goodreadsDAO.getImage(""));
    }

}