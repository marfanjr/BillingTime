package marfan.billingtime;

import java.util.ArrayList;

/**
 * Created by marfan on 4/4/16.
 */
public class Task {

    private String description;
    private ArrayList<TrackedTime> trackedTimes;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public ArrayList<TrackedTime> getTrackedTimes() {
        return trackedTimes;
    }

    public void setTrackedTimes(ArrayList<TrackedTime> trackedTimes) {
        this.trackedTimes = trackedTimes;
    }

    public void getTotalTime (){

    }
}
