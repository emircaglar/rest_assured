package GoRest.All;

import java.util.List;

public class Meta {
   private Pagination pagination;

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    @Override
    public String toString() {
        return "Meta{" +
                "pagination=" + pagination +
                '}';
    }
}
