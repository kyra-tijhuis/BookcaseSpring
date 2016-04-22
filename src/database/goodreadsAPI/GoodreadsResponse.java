package database.goodreadsAPI;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Kyra on 19/04/2016.
 */
@XmlRootElement(name= "GoodreadsResponse")
class GoodreadsResponse {
    private Request request;

    @XmlElement(name = "Request")
    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    private Search search;

    @XmlElement(name = "search")
    public Search getSearch() {
        return search;
    }

    public void setSearch(Search search) {
        this.search = search;
    }
}
