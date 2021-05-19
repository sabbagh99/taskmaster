package com.example.taskmaster;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database (entities = {TaskModel.class}, version = 1,exportSchema = false)

public abstract class AppDataBase  extends RoomDatabase {
    public abstract TaskModelReposotery taskDao();

}
