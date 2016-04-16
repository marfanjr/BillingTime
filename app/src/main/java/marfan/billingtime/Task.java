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
        if (this.description.equals(""))
            return null;
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
        int hours = (int) TimeUnit.MILLISECONDS.toHours(total);
        int minutes = (int) (TimeUnit.MILLISECONDS.toMinutes(total) - TimeUnit.HOURS.toMinutes(hours));
        int seconds = (int) (TimeUnit.MILLISECONDS.toSeconds(total) - TimeUnit.MINUTES.toSeconds(minutes));

        String dateFormatted = String.format("%02d:%02d:%02d", hours, minutes, seconds);

//        TODO: Pegar essa string do resource de strings
        String defaultDescription = "Task without description";
        String description = getDescription() == null ? defaultDescription : this.description;

        return description + " - Total time: " + dateFormatted;
    }
}
