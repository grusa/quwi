package earth.sochi.quwi.data;

import androidx.lifecycle.ViewModel;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "projects")
public  class Project {
        @PrimaryKey(autoGenerate = true)
        public int id;

        @ColumnInfo(name = "name")
        public String name;

        @ColumnInfo(name = "description")
        public String description;
}


