package com.example.mytodo;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NotesRepo  {

    private  NoteDao noteDao ;
    private LiveData<List<Notes>> noteslist;

    public NotesRepo(Application application) {

        NotesDatabase notesDatabase = NotesDatabase.getInstance(application);
        noteDao= notesDatabase.noteDao();
        noteslist=noteDao.getData();



    }

    public  void  insertData(Notes notes){
        new InsertTask(noteDao).execute(notes);


    }

    public  void  updateData(Notes notes){
        new UpdateTask(noteDao).execute(notes);

    }

    public  void  deleteData(Notes notes){
        new DeleteTask(noteDao).execute(notes);
    }

    public LiveData<List<Notes>> getData(){

        return  noteslist;
    }

    public  static class InsertTask extends AsyncTask<Notes ,Void , Void>{

        public InsertTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        private   NoteDao noteDao ;

        @Override
        protected Void doInBackground(Notes... notes) {
            noteDao.insert(notes[0]);

            return null;
        }
    } public  static class UpdateTask extends AsyncTask<Notes ,Void , Void>{

        public UpdateTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        private   NoteDao noteDao ;

        @Override
        protected Void doInBackground(Notes... notes) {
            noteDao.update(notes[0]);

            return null;
        }
    } public  static class DeleteTask extends AsyncTask<Notes ,Void , Void>{

        public DeleteTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        private   NoteDao noteDao ;

        @Override
        protected Void doInBackground(Notes... notes) {
            noteDao.delete(notes[0]);

            return null;
        }
    }



}
