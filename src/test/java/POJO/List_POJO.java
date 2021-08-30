package POJO;

import java.util.List;



public class List_POJO {

    private List<Pj> pj_list;

    public List<Pj> getPj_list() {
        return pj_list;
    }

    public void setPj_list(List<Pj> pj_list) {
        this.pj_list = pj_list;
    }

    @Override
    public String toString() {
        return "List_POJO{" +
                "pj_list=" + pj_list +
                '}';
    }


}
