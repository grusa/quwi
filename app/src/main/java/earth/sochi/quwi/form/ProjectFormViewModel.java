package earth.sochi.quwi.form;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import earth.sochi.quwi.data.Project;
import earth.sochi.quwi.data.ProjectDao;
import earth.sochi.quwi.data.ProjectDatabase;

public class ProjectFormViewModel extends AndroidViewModel {
        private ProjectDao dataSource;
        private final String TAG = "PVM";
        private ProjectDatabase db;


    public ProjectFormViewModel(Application application) {
        super(application);
        db = ProjectDatabase.getInstance(application);
        dataSource = db.projectDao();
    }
    public Boolean insert(Project project) {
            try {
                db.databaseWriteExecutor.execute(()->
            dataSource.insertProject(project));
            } catch (Exception e) {
                Log.d(TAG,e.toString());
                return false;
            }
            return true;
    }
}
