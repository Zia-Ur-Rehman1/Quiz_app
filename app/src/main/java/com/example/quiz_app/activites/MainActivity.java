package com.example.quiz_app.activites;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.quiz_app.R;
import com.example.quiz_app.adapters.QuizAdapter;
import com.example.quiz_app.models.Question;
import com.example.quiz_app.models.Quiz;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ActionBarDrawerToggle drawer;
    FloatingActionButton datepicker;
    QuizAdapter adapter;
    FirebaseFirestore db;
    ArrayList<Quiz> data = new ArrayList<>();
    RecyclerView rc_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rc_view = findViewById(R.id.recyle_view);
        datepicker=findViewById(R.id.btnDatePicker);
        setup_Views();

    }

    void setup_Views() {
        setUpFirestore();
        setUpDrawlayout();
        setUpRecyclerView();
        setUpDatePicker();
    }
    private void  setUpDatePicker(){
        datepicker.setOnClickListener(v -> {
            MaterialDatePicker<Long> materialDateBuilder = MaterialDatePicker.Builder.datePicker().build();
            materialDateBuilder.show(getSupportFragmentManager(),"DatePicker");
            materialDateBuilder.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
                @Override
                public void onPositiveButtonClick(Long selection) {

                    Log.d("DatePicker", materialDateBuilder.getHeaderText());
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
                    String date = dateFormat.format(new Date(selection));
                    Intent intent = new Intent(MainActivity.this, QuestionActivity.class);
                    intent.putExtra("DATE", date);
                    MainActivity.this.startActivity(intent);
                }
            });
            materialDateBuilder.addOnNegativeButtonClickListener(selection -> {
                Log.d("DatePicker", materialDateBuilder.getHeaderText());
            });
            materialDateBuilder.addOnCancelListener(selection -> {
                Log.d("DatePicker", "Date Picker Cancelled");
            });
        });
    }
    private  void setUpFirestore(){
        db=FirebaseFirestore.getInstance();
        CollectionReference collectionReference = db.collection("quizzes");
        collectionReference.addSnapshotListener((value, error) -> {
           if(value == null || error!=null){
               Log.d("ZIA", error.getMessage());
               return;
           }
           data.clear();
           data.addAll(value.toObjects(Quiz.class));
           adapter.notifyDataSetChanged();
        });
    }


    private void setUpRecyclerView() {
        adapter = new QuizAdapter(this, data);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        rc_view.setAdapter(adapter);
        rc_view.setLayoutManager(gridLayoutManager);
    }

    public void setUpDrawlayout() {
        setSupportActionBar(findViewById(R.id.appBar));
        drawer = new ActionBarDrawerToggle(MainActivity.this, findViewById(R.id.mainDrawer), R.string.app_name, R.string.app_name);
        drawer.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawer.onOptionsItemSelected(item)) {
            return (true);
        }
        return super.onOptionsItemSelected(item);
    }
}