package earth.sochi.quwi.project;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import earth.sochi.quwi.data.Project;
import earth.sochi.quwi.data.ProjectDao;
import earth.sochi.quwi.data.ProjectDatabase;
import io.reactivex.Maybe;

public class ProjectViewModel extends AndroidViewModel {
        private ProjectDatabase db;
        private ProjectDao dataSource;
        private final String TAG = "PVM";
        // The current project
        private androidx.lifecycle.Observer<List<Project>> observer;

        public MutableLiveData<List<Project>> _projects;
        public List<Project> projects;

    public ProjectViewModel (Application application) {
        super(application);
        db = ProjectDatabase.getInstance(application);
        dataSource = db.projectDao();
        //projects = ArrayList<>();
        projects=dataSource.getAllProjects().blockingGet();
        //_projects = new MutableLiveData<>();

        observer = new androidx.lifecycle.Observer<List<Project>>() {
            @Override
            public void onChanged(List<Project> getProjects) {
                projects=getProjects;
            }
        };
//        try {
//            _projects.setValue(dataSource.getAllProjects());
//            //_projects = (MutableLiveData<List<Project>>) dataSource.getAllProjects();
//        } catch ( Exception e) {
//            Log.d(TAG,e.toString());
//        }
    }
    public MutableLiveData<List<Project>> getProjects() {
        if (_projects == null) {
            _projects = new MutableLiveData<>() ;
            _projects.setValue( dataSource.getAllProjects().blockingGet());
      }
        return _projects;
    }
}
