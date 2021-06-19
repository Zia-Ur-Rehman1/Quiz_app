package com.example.quiz_app.activites;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.quiz_app.R;
import com.example.quiz_app.adapters.QuizAdapter;
import com.example.quiz_app.models.Quiz;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ActionBarDrawerToggle drawer;
    QuizAdapter adapter;
    FirebaseFirestore db;
    ArrayList<Quiz> data = new ArrayList<>();
    RecyclerView rc_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rc_view = findViewById(R.id.recyle_view);
        setup_Views();

    }

    void setup_Views() {
//        setUpFirestore();
        setUpDrawlayout();
        populateDummyData();
        setUpRecyclerView();
    }
    private  void setUpFirestore(){
        db=FirebaseFirestore.getInstance();
        CollectionReference collectionReference = db.collection("quizzes");
                collectionReference.addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(QuerySnapshot value, FirebaseFirestoreException error) {
                        if(value== null || error !=null)
                        {
                            Log.i("ZIA", "onEvent: ");
                            return;
                        }
                        data.clear();
                        data.addAll(value.toObjects(Quiz.class));
                        adapter.notifyDataSetChanged();
                    }
                });

    }

    private void populateDummyData() {
        data.add(new Quiz("1", "2"));
        data.add(new Quiz("1", "2"));
        data.add(new Quiz("1", "2"));
        data.add(new Quiz("1", "2"));
        data.add(new Quiz("1", "2"));
        data.add(new Quiz("1", "2"));
        data.add(new Quiz("1", "2"));
        data.add(new Quiz("1", "2"));
        data.add(new Quiz("1", "2"));
        data.add(new Quiz("1", "2"));
        data.add(new Quiz("1", "2"));
        data.add(new Quiz("1", "2"));
        data.add(new Quiz("1", "2"));

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