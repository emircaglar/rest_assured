package GoRest.Posts;


import GoRest.All.Meta;

import java.util.List;

public class Alle {
    private Meta meta;
    private Data data;

    @Override
    public String toString() {
        return "Alle{" +
                "meta=" + meta +
                ", data=" + data +
                '}';
    }

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

}
