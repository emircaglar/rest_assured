package POJO;

import java.util.List;



public class List_POJO {

    private List<Todo> pj_list;

    public List<Todo> getPj_list() {
        return pj_list;
    }

    public void setPj_list(List<Todo> pj_list) {
        this.pj_list = pj_list;
    }

    @Override
    public String toString() {
        return "List_POJO{" +
                "pj_list=" + pj_list +
                '}';
    }


}
