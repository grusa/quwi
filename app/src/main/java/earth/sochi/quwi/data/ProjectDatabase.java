
package  earth.sochi.quwi.data;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Project.class},version = 1,exportSchema = false)
public abstract class ProjectDatabase extends RoomDatabase {
public abstract ProjectDao projectDao ();
private static String TAG="PD";

    private static  ProjectDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);
public static ProjectDatabase getInstance(final Context context) {
    if (INSTANCE==null) {
        synchronized (ProjectDatabase.class) {
            if (INSTANCE==null) {
                INSTANCE =
                        Room.databaseBuilder(context.getApplicationContext(),
                                ProjectDatabase.class,"project_database")
                                .allowMainThreadQueries()
                                .addCallback(sProjectDatabase)
                                .build();
            }
        }
    }
    return INSTANCE;
}
    private static ProjectDatabase.Callback sProjectDatabase =
            new ProjectDatabase.Callback()
            {
                @Override
                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                    super.onOpen(db);
                    Log.d(TAG,"processing db");
                }
            };
}
