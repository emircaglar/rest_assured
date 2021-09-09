package GoRest.Todos1;

import GoRest.All.Meta;
import GoRest.Posts.Data;

public class Alle {
    private GoRest.All.Meta meta;
    private GoRest.Posts.Data data;

    @Override
    public String toString() {
        return "Alle{" +
                "meta=" + meta +
                ", data=" + data +
                '}';
    }

    public GoRest.All.Meta getMeta() {
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
