package GoRest.Todos1;

import GoRest.Posts.Pagination;

public class Meta {
    private GoRest.Posts.Pagination pagination;

    public GoRest.Posts.Pagination getPagination() {
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
