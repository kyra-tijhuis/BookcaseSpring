package database.goodreadsAPI;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created by Kyra on 21/04/2016.
 */
public class GoodreadsDAO {
    public String getImage(String bookIdentifier) {
        try {
            JAXBContext context = JAXBContext.newInstance(GoodreadsResponse.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            String encoded = URLEncoder.encode(bookIdentifier, "UTF-8");
            URL url = new URL("https://www.goodreads.com/search/index.xml?key=ZybSk7B2vzOtJnVMY3DVA&q=" + encoded);
            GoodreadsResponse response = (GoodreadsResponse) unmarshaller.unmarshal(url);
            List<Work> works = response.getSearch().getResults().getWork();
            if (works.size()>0) {
                return works.get(0).getBest_book().getImage_url();
            } else {
                return "https://s.gr-assets.com/assets/nophoto/book/111x148-bcc042a9c91a29c1d680899eff700a03.png";
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "https://s.gr-assets.com/assets/nophoto/book/111x148-bcc042a9c91a29c1d680899eff700a03.png";
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return "https://s.gr-assets.com/assets/nophoto/book/111x148-bcc042a9c91a29c1d680899eff700a03.png";
        } catch (JAXBException e) {
            e.printStackTrace();
            return "https://s.gr-assets.com/assets/nophoto/book/111x148-bcc042a9c91a29c1d680899eff700a03.png";
        }
    }
}
