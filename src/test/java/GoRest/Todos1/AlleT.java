package GoRest.Todos1;




public class AlleT {
    private MetaT meta;
    private DataT data;

    public MetaT getMeta() {
        return meta;
    }

    public void setMeta(MetaT meta) {
        this.meta = meta;
    }

    public DataT getData() {
        return data;
    }

    public void setData(DataT data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "AlleT{" +
                "meta=" + meta +
                ", data=" + data +
                '}';
    }
}
