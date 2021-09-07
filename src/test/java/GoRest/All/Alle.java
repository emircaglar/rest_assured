package GoRest.All;

import java.util.List;

public class Alle {
    private Meta meta;
    private Data data;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Alle{" +
                "meta=" + meta +
                ", data=" + data +
                '}';
    }
}
