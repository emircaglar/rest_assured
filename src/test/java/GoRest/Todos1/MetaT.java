package GoRest.Todos1;



public class MetaT {
    private PaginationT pagination;

    public PaginationT getPagination() {
        return pagination;
    }

    public void setPagination(PaginationT pagination) {
        this.pagination = pagination;
    }

    @Override
    public String toString() {
        return "Meta{" +
                "pagination=" + pagination +
                '}';
    }
}
