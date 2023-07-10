package com.example.mytodo;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "MyData")

public class Notes {
    public Notes(String title, String dis) {
        Title = title;
        this.dis = dis;

    }

    public Notes() {
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDis() {
        return dis;
    }

    public void setDis(String dis) {
        this.dis = dis;
    }

    private  String Title;
    private  String dis;


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    @PrimaryKey(autoGenerate = true)
    private  int id;

}
