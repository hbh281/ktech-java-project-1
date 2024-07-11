import java.time.LocalDate;

public class Todo extends TodoApp {


    private String title;
    private LocalDate deadline;
    private boolean isDone;





    public Todo(){}
    public Todo(String title, LocalDate deadline) {
        this.title = title;
        this.deadline = deadline;
        this.isDone = false;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }


}
