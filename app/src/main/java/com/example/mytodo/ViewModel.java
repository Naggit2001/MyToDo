package com.example.mytodo;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ViewModel extends AndroidViewModel {

    private  NotesRepo notesRepo ;
    private LiveData<List<Notes>> notesList;



    public ViewModel(@NonNull Application application) {
        super(application);
        notesRepo =new NotesRepo( application);
        notesList = notesRepo.getData();


    }

    public  void  insert(Notes notes ){
        notesRepo.insertData(notes);

    }
    public  void  update(Notes notes ){
        notesRepo.updateData(notes);

    }
    public  void  delete(Notes notes ){
        notesRepo.deleteData(notes);

    }
    public  LiveData<List<Notes>> getAllNotes(){

        return  notesList;

    }


}
