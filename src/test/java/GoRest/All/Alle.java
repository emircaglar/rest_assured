package GoRest.All;

import java.util.List;

public class Alle {
    private List<Pagination> datalist;
    private List<Data> metalist;

    public List<Pagination> getDatalist() {
        return datalist;
    }

    public void setDatalist(List<Pagination> datalist) {
        this.datalist = datalist;
    }

    public List<Data> getMetalist() {
        return metalist;
    }

    public void setMetalist(List<Data> metalist) {
        this.metalist = metalist;
    }

    @Override
    public String toString() {
        return "Alle{" +
                "datalist=" + datalist +
                ", metalist=" + metalist +
                '}';
    }
}
