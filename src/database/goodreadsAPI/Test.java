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
        boolean print = false;
        try {
            JAXBContext context = JAXBContext.newInstance(GoodreadsResponse.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            URL url = new URL("https://www.goodreads.com/search/index.xml?key=ZybSk7B2vzOtJnVMY3DVA&q=Ender%27s+Game");
//            BufferedReader reader = new BufferedReader(stringReader);
//            StringBuffer result = new StringBuffer();
//            try {
//                String line;
//                while ( (line = reader.readLine() ) != null) {
//                    if (!print) {
//                        print = true;
//                    } else {
//                        System.out.println(line);
////                        result.append(line.trim());
//                    }
//                }
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }

//            DocumentBuilder parser = DocumentBuilderFactory.newInstance().newDocumentBuilder();
//            Document document = parser.parse("https://www.goodreads.com/search/index.xml?key=ZybSk7B2vzOtJnVMY3DVA&q=Ender%27s+Game");
//            Transformer transformer = TransformerFactory.newInstance().newTransformer();
//            Source source = new DOMSource(document);
//            Result output = new StreamResult(System.out);
//            transformer.transform(source, output);


            GoodreadsResponse response = (GoodreadsResponse) unmarshaller.unmarshal(url);
            List<Work> workList = response.getSearch().getResults().getWork();
            for (Work w : workList) {
                System.out.println(w.getId());
            }
        } catch (JAXBException e) {
            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (TransformerConfigurationException e) {
//            e.printStackTrace();
//        } catch (ParserConfigurationException e) {
//            e.printStackTrace();
//        } catch (SAXException e) {
//            e.printStackTrace();
//        } catch (TransformerException e) {
//            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }
}
