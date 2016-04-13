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
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  implements AdapterView.OnItemSelectedListener {

    private Chronometer chronometer;
    private ImageButton play;
    private ImageButton stop;
    private EditText etTaskDescription;
    private Spinner spnProject;
    private List<Project> projects;
    private ProjectsRepo projectsRepo;
    private ArrayAdapter<Project> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUiViews();
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.start();

            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chronometer.stop();
            }
        });

        projectsRepo = new ProjectsRepo(this);

        projects = projectsRepo.readProjects();

        //Cria um ArrayAdapter usando um padrão de layout da classe R do android, passando o ArrayList nomes
        arrayAdapter = new ArrayAdapter<Project>(this, android.R.layout.simple_spinner_dropdown_item, projects);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spnProject.setAdapter(arrayAdapter);

        spnProject.setOnItemSelectedListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setUiViews() {
        play = (ImageButton) findViewById(R.id.btn_play);
        stop = (ImageButton) findViewById(R.id.btn_stop);

        chronometer = (Chronometer) findViewById(R.id.chronometer);

        etTaskDescription = (EditText) findViewById(R.id.doing_activity);
        spnProject = (Spinner) findViewById(R.id.doing_project);

    }

    public void newProject(View view) {
        Intent intent = new Intent(this, ProjectActivity.class);
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Context context = getApplicationContext();
        CharSequence text = "Hello toast!" + position   ;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
