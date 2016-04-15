package marfan.billingtime;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class TaskListActivity extends AppCompatActivity {

    private Project project;
    private ProjectsRepo projectsRepo;
    private List<Project> projects;
    private ArrayAdapter<Task> arrayAdapter;
    private ListView lvTasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        int position = intent.getIntExtra("PROJECT_POSITION", 0);
        setUiViews();
        initProjects();
        initProject(position);
        configLvTasks();

    }

    private void initProjects() {

        projectsRepo = new ProjectsRepo(this);
        projects = projectsRepo.readProjects();

    }


    private void initProject(int position){
        this.project = projects.get(position);
    }


    private void setUiViews() {
        lvTasks = (ListView) findViewById(R.id.tasks_list);
    }

    private void configLvTasks(){
        //Cria um ArrayAdapter usando um padrão de layout da classe R do android, passando o ArrayList nomes
        arrayAdapter = new ArrayAdapter<Task>(this, android.R.layout.simple_list_item_1, this.project.getTasks());
        lvTasks.setAdapter(arrayAdapter);

    }

}
