package marfan.billingtime;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by marfan on 4/4/16.
 */
public class Project implements Serializable {
    private String name;
    private List<Task> tasks;

    public Project (String name) {
        this.name = name;
        this.tasks = new ArrayList<Task>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public void addTask(Task task){
        if (this.tasks == null)
            this.tasks = new ArrayList<Task>();

        this.tasks.add(task);
    }

    public List<Task> getTasks(){
        return this.tasks;
    }
}
