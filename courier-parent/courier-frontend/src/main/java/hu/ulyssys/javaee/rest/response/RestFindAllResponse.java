package hu.ulyssys.javaee.rest.response;

import java.util.List;

public class RestFindAllResponse<T> {

    private List<T> list;

    public RestFindAllResponse() {
    }

    public RestFindAllResponse(List<T> list) {
        this.list = list;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
