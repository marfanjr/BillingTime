package marfan.billingtime;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

/**
 * Created by marfan on 4/4/16.
 */
public class Task {

    public Task (String description){
        setDescription(description);
    }

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

    public void addTrackedTime(TrackedTime trackedTime){
        if (this.trackedTimes == null)
            this.trackedTimes = new ArrayList<TrackedTime>();
        this.trackedTimes.add(trackedTime);
    }
    public void setTrackedTimes(ArrayList<TrackedTime> trackedTimes) {
        this.trackedTimes = trackedTimes;
    }

    public long getTotalTime() {
        ArrayList<TrackedTime> trackedTimes = getTrackedTimes();

        Iterator<TrackedTime> trackedTimeIterator = trackedTimes.iterator();
        long totalTime = 0;
        while (trackedTimeIterator.hasNext()) {
            totalTime += trackedTimeIterator.next().differenceInMillis();

        }

        return totalTime;

    }

    @Override
    public String toString() {
        long total = getTotalTime();
        String dateFormatted = String.format("%02d:%02d:%02d",
                                            TimeUnit.MILLISECONDS.toHours(total),
                                            TimeUnit.MILLISECONDS.toMinutes(total) -
                                            TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(total)), // The change is in this line
                                            TimeUnit.MILLISECONDS.toSeconds(total) -
                                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(total)));
        
        String description = this.description == null ? "Atividade sem descrição" : this.description;
        return description + "    Total time: " + dateFormatted;
    }
}
