package com.example.mytodo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DatainsertActivity extends AppCompatActivity {

    TextView title,description;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_datainsert);


        title = findViewById(R.id.title);
        description = findViewById(R.id.des);
        button = findViewById(R.id.button);

        // hide the bar
        getSupportActionBar().hide();



        String type = getIntent().getStringExtra("type");

        if (type.equals("update")){

            setTitle("update");

            title.setText(getIntent().getStringExtra("title"));
            description.setText(getIntent().getStringExtra("description"));
            int id = getIntent().getIntExtra("id",0);

            button.setText("Update");

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    Intent intent = new Intent();
                    intent.putExtra("title", title.getText().toString());
                    intent.putExtra("description", description.getText().toString());
                    intent.putExtra("id",id);


                    setResult(RESULT_OK, intent);

                    finish();

                }
            });





        }else {


            setTitle("addMode");

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.putExtra("title", title.getText().toString());
                    intent.putExtra("description", description.getText().toString());
                    setResult(RESULT_OK, intent);

                    finish();
                }
            });

        }



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(DatainsertActivity.this,MainActivity.class));
    }
}