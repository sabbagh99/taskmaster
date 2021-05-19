package com.example.taskmaster;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TaskModelReposotery {


    @Insert
    void insertAll(TaskModel taskModel);


    @Query("SELECT * FROM task_model")
    List<TaskModel> getAll();

}
