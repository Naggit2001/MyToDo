package com.example.mytodo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Insert;
import androidx.room.PrimaryKey;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView ;
    FloatingActionButton floatingButton;

    private  ViewModel viewModel ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.mainrv);
        floatingButton = findViewById(R.id.floatingActionButton);

        viewModel = new ViewModelProvider(MainActivity.this,ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication()))
                .get(ViewModel.class);

        // hide  the  acctION BAR
       getSupportActionBar().hide();



        floatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,DatainsertActivity.class);

                intent.putExtra("type","addMode");

                startActivityForResult( intent ,1);



            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        RvAdapter adapter = new RvAdapter();

        recyclerView.setAdapter(adapter);

        viewModel.getAllNotes().observe(this, new Observer<List<Notes>>() {
            @Override
            public void onChanged(List<Notes> notes) {
                adapter.submitList(notes);
            }
        });


        //  to  delete  the  dat  to  right  swipe  and  left  swipe

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {



            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                if (direction==ItemTouchHelper.LEFT) {
                    viewModel.delete(adapter.getNotes(viewHolder.getPosition()));
                    Toast.makeText(MainActivity.this, "Item Delete", Toast.LENGTH_SHORT).show();



                }else {

//                    viewModel.delete(adapter.getNotes(viewHolder.getPosition()));
//                    Toast.makeText(MainActivity.this, "Item delete", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(MainActivity.this,DatainsertActivity.class);
                    intent.putExtra("type","update");

                    intent.putExtra("title",adapter.getNotes(viewHolder.getAdapterPosition()).getTitle());
                    intent.putExtra("description",adapter.getNotes(viewHolder.getAdapterPosition()).getDis());
                    intent.putExtra("id",adapter.getNotes(viewHolder.getAdapterPosition()).getId());
                    Toast.makeText(MainActivity.this, "Go To Update ", Toast.LENGTH_SHORT).show();

                    startActivityForResult(intent,2);



                }
            }
        }).attachToRecyclerView(recyclerView);





    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1){

             String title = data.getStringExtra("title");
             String description = data.getStringExtra("description");

             Notes notes = new Notes(title,description);

             viewModel.insert(notes);
            Toast.makeText(this, "added", Toast.LENGTH_SHORT).show();


        }

        else if(requestCode==2){


            String title = data.getStringExtra("title");
            String description = data.getStringExtra("description");

            Notes notes = new Notes(title,description);
            notes.setId(data.getIntExtra("id",0));
            viewModel.update(notes);
            Toast.makeText(this, "update", Toast.LENGTH_SHORT).show();



        }


    }
}