package marfan.billingtime;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class ProjectActivity extends AppCompatActivity {
    private ListView lvProjects;
    private EditText etProject;
    private Button btnNewProject;
    private List<Project> projects;
    private ProjectsRepo projectsRepo;
    private ArrayAdapter<Project> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);

        setUiViews();
        projectsRepo = new ProjectsRepo(this);

        projects = projectsRepo.readProjects();

        //Cria um ArrayAdapter usando um padrão de layout da classe R do android, passando o ArrayList nomes
        arrayAdapter = new ArrayAdapter<Project>(this, android.R.layout.simple_list_item_1, projects);

        lvProjects.setAdapter(arrayAdapter);

        btnNewProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String projectName = etProject.getText().toString();

                Project newProject = new Project(projectName);
                projects.add(newProject);

                projectsRepo.writeProjects(projects);
                arrayAdapter.notifyDataSetChanged();

                etProject.setText("");
            }
        });

        lvProjects.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Project clickedProject = (Project) parent.getAdapter().getItem(position);
            }
        });

    }

    private void setUiViews(){
        lvProjects = (ListView) findViewById(R.id.projects_list);
        etProject = (EditText) findViewById(R.id.new_project_name);
        btnNewProject = (Button) findViewById(R.id.create_project_btn);
    }

}
