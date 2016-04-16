package marfan.billingtime;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity  implements AdapterView.OnItemSelectedListener {

    private Chronometer chronometer;
    private ImageButton play;
    private ImageButton stop;
    private EditText etTaskDescription;
    private Spinner spnProject;
    private Button btnNewProject;

    private Project currentProject;
    private Task currentTask;
    private TrackedTime currentTrackedTime;

    private List<Project> projects;
    private ProjectsRepo projectsRepo;
    private ArrayAdapter<Project> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUiViews();
        configBtnPlayComportament();
        configBtnStopComportament();
    }

    @Override
    public void onStart(){
        super.onStart();
        initProjects();
        if (projects.isEmpty()) {
            showToast("Você precisa criar um projeto antes de iniciar uma atividade.");
            newProject(btnNewProject);
            return;
        }
        configSpnProjects();
    }

    private void setUiViews() {
        play = (ImageButton) findViewById(R.id.btn_play);
        stop = (ImageButton) findViewById(R.id.btn_stop);
        chronometer = (Chronometer) findViewById(R.id.chronometer);
        etTaskDescription = (EditText) findViewById(R.id.doing_activity);
        spnProject = (Spinner) findViewById(R.id.doing_project);
        btnNewProject = (Button) findViewById(R.id.btn_new_project);
    }

    private void configBtnPlayComportament(){
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCurrentTask();
                long chronometerBase = SystemClock.elapsedRealtime();
                chronometer.setBase(chronometerBase);
                chronometer.start();
                Date newDate = new Date();
                setCurrentTrackedTimeStart(newDate);
            }
        });
    }

    private void configBtnStopComportament() {
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chronometer.stop();
                chronometer.setText("00:00");
                setCurrentTrackedTimeStop(new Date());
                currentTask.addTrackedTime(getCurrentTrackedTime());
                currentProject.addTask(getcurrentTask());
                projectsRepo.writeProjects(projects);

            }
        });
    }

    private void setCurrentTask() {
        String taskDescription = etTaskDescription.getText().toString();
        if (taskDescription.equals("")) {
            taskDescription = null;
        }
        this.currentTask = new Task(taskDescription);
    }

    private void setCurrentTrackedTimeStart(Date date) {
        if (currentTrackedTime == null)
            currentTrackedTime = new TrackedTime();
        this.currentTrackedTime.setStartTime(date);
    }

    private void setCurrentTrackedTimeStop(Date date) {
        this.currentTrackedTime.setEndTime(date);
    }

    private TrackedTime getCurrentTrackedTime() {
        return this.currentTrackedTime;
    }

    private Task getcurrentTask() {
        return this.currentTask;
    }

    public void newProject(View view) {
        Intent intent = new Intent(this, ProjectActivity.class);
        startActivity(intent);
    }

    private void initProjects() {
        projectsRepo = new ProjectsRepo(this);
        projects = projectsRepo.readProjects();
    }

    private void configSpnProjects(){
        arrayAdapter = new ArrayAdapter<Project>(this, android.R.layout.simple_spinner_dropdown_item, projects);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spnProject.setAdapter(arrayAdapter);
        spnProject.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        currentProject = projects.get(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {}

    private void showToast(String msg) {
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(this, msg, duration);
        toast.show();
    }
}
