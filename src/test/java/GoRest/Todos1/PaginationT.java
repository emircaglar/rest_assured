package GoRest.Todos1;



import java.util.List;

public class PaginationT {

    private int total;
    private int pages;
    private int page;
    private int limit;
    private List<LinkT> links;

    public void setLinks(List<LinkT> links) {
        this.links = links;
    }


    @Override
    public String toString() {
        return "Pagination{" +
                "total=" + total +
                ", pages=" + pages +
                ", limit=" + limit +
                ", page=" + page +
                ", links=" + links +
                '}';
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }


}
