package ToDo;

import com.sun.java.swing.plaf.windows.WindowsProgressBarUI;

import java.time.LocalDate;

public class Task {

    private String name;
    private LocalDate dueDate;
    private boolean inProgress;
    private boolean isComplete;

    public String getDueDate() {
        return dueDate.toString();
    }

    public void setDueDate(String dueDate) {
        this.dueDate = LocalDate.parse(dueDate);
    }

    public boolean isInProgress() {
        return inProgress;
    }

    public void setInProgress(boolean inProgress) {
        this.inProgress = inProgress;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Task(String name, LocalDate date, boolean inProgress, boolean isComplete){

        this.name = name;
        this.dueDate = date;
        this.inProgress = inProgress;
        this.isComplete = isComplete;
    }
}
