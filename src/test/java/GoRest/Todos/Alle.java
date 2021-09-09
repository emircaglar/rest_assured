package GoRest.Todos;


import GoRest.All.Meta;
import GoRest.Posts.Data;

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

    public GoRest.Posts.Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

}
