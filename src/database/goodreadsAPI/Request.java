package database.goodreadsAPI;

/**
 * Created by Kyra on 19/04/2016.
 */
class Request {
    private String key;
    private String method;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
