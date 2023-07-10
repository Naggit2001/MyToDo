package com.example.mytodo;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NoteDao {
    @Insert
    public  void insert(Notes notes);
    @Update
    public  void update(Notes notes);
    @Delete
    public  void delete(Notes notes);
    @Query("SELECT * FROM mydata")
    public LiveData<List<Notes>> getData();
}
