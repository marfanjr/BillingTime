package marfan.billingtime;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by marfan on 4/13/16.
 */
@SuppressWarnings("unchecked")
@SuppressLint("CommitPrefEdits")
public class ProjectsRepo {
    private static final String PROJECTS_KEY = "projects";
    private SharedPreferences prefs;

    public ProjectsRepo(Context context) {
        prefs = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
    }

    public List<Project> readProjects() {
        String projectsJSONString = prefs.getString(PROJECTS_KEY, null);
        Type type = new TypeToken<List<Project>>(){}.getType();

        return (projectsJSONString == null) ?
                new ArrayList() : // no favorites, return empty new list
                (List<Project>) new Gson().fromJson(projectsJSONString, type); // return list of favorites
    }

    public void writeProjects(List projects) {
        String favoritesJSONString = new Gson().toJson(projects);
        SharedPreferences.Editor prefEditor = prefs.edit();
        prefEditor.putString(PROJECTS_KEY, favoritesJSONString);
        prefEditor.commit();
    }
}
