package earth.sochi.quwi.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;

@Dao
public interface ProjectDao {
    @Query("SELECT * FROM projects")
    Maybe<List<Project>> getAllProjects();

    @Query("SELECT * FROM projects WHERE id IN (:userIds)")
    List<Project> loadAllByIds(int[] userIds);

    @Insert(onConflict = OnConflictStrategy.REPLACE) //suspend
    public void insertProject(Project project);

    @Query("SELECT * from projects WHERE id = :key")
    LiveData<Project> getProjectId(Long key);

    @Delete
    void deleteProject(Project project);
}

