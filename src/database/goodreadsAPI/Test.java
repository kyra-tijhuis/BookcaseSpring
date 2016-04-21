package database.goodreadsAPI;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by Kyra on 19/04/2016.
 */
public class Test {

    public static void main(String[] args) {
        GoodreadsDAO goodreadsDAO = new GoodreadsDAO();
        System.out.println(goodreadsDAO.getImage("Over het water"));
    }
}
