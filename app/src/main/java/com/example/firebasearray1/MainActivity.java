 package com.example.firebasearray1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

 public class MainActivity extends AppCompatActivity {

     List<String> category;
     List<String> displayList;

     DatabaseReference databaseReference;

     Button done, display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        done = findViewById(R.id.button);
        display = findViewById(R.id.button2);

        category = new ArrayList<>();
        displayList = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference("Users").child("I2XsrxUR70N2PR8nC7qf0KSvLhW2").child("preferences");

        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists())
                        {
                            displayList.clear();
                            for(DataSnapshot des:snapshot.getChildren())
                            {
                                String timeName = des.getValue(String.class);
                                displayList.add(timeName);
                            }

                            StringBuilder stringBuilder = new StringBuilder();

                            for (int i=0; i<displayList.size(); i++)
                            {
                                stringBuilder.append(displayList.get(i) + ", ");
                            }

                            Toast.makeText(MainActivity.this, stringBuilder.toString(), Toast.LENGTH_LONG).show();
                            Log.d("TAG", stringBuilder.toString());
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        //        done.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                category.add("12 pm");
//                category.add("01 pm");
//                category.add("02 pm");
//                category.add("03 pm");
//                category.add("04 pm");
//                category.add("05 pm");
//                category.add("06 pm");
//
//                databaseReference.setValue(category).addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        Toast.makeText(getApplicationContext(), "List is Uploaded Successfully", Toast.LENGTH_LONG).show();
//                    }
//                });
//            }
//        });
    }

}