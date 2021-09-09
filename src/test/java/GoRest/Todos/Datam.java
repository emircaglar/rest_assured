package GoRest.Todos;

public class Datam {
    private int id;
    private int user_id;
    private String due_on;
    private String status;
    private String title;
    @Override
    public String toString() {
        return "Data{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", due_on='" + due_on + '\'' +
                ", status='" + status + '\'' +
                ", title='" + title + '\'' +
                '}';
    }




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getDue_on() {
        return due_on;
    }

    public void setDue_on(String due_on) {
        this.due_on = due_on;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}