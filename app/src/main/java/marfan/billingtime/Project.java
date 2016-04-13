package marfan.billingtime;

import java.util.ArrayList;

/**
 * Created by marfan on 4/4/16.
 */
public class Project {
    private String name;
    private ArrayList<Task> tasks;

    public Project (String name) {
        this.name = name;
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
}
