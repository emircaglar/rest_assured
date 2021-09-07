package GoRest.All;

import java.util.List;

public class Meta {
   private List<Data> metalist;

    public List<Data> getMetalist() {
        return metalist;
    }

    public void setMetalist(List<Data> metalist) {
        this.metalist = metalist;
    }

    @Override
    public String toString() {
        return "Meta{" +
                "metalist=" + metalist +
                '}';
    }
}
