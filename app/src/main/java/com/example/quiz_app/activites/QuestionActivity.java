package com.example.quiz_app.activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.quiz_app.R;
import com.example.quiz_app.adapters.OptionAdapter;
import com.example.quiz_app.models.Question;
import com.example.quiz_app.models.Quiz;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.protobuf.StringValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestionActivity extends AppCompatActivity {
    Question question;
    Button next,prev,submit;
    FirebaseFirestore db;
    OptionAdapter optionAdapter;
    RecyclerView recyclerViewl;
    List<Quiz> quiz;
    Map<String,Question> questionMap = new HashMap<>();
    int index=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        recyclerViewl=findViewById(R.id.optionList);
        setUpFireStore();
    }
    private void setUpFireStore(){
        db= FirebaseFirestore.getInstance();
        String date= getIntent().getStringExtra("DATE");

        if(date!=null){
            db.collection("quizzes").whereEqualTo("title",date)
                    .get()
                    .addOnSuccessListener(queryDocumentSnapshots -> {
                     if(queryDocumentSnapshots!=null && !queryDocumentSnapshots.isEmpty()){
                        quiz=queryDocumentSnapshots.toObjects(Quiz.class);

                     }
                    });
        }

        
    }

    private void bindViews(){
        next=findViewById(R.id.btnNext);
        prev=findViewById(R.id.btnPrevious);
        submit=findViewById(R.id.btnSubmit);
        next.setVisibility(View.GONE);
        prev.setVisibility(View.GONE);
        submit.setVisibility(View.GONE);
        if(index==1){
            next.setVisibility(View.VISIBLE);
        }
        else if(index==questionMap.size()) {
            submit.setVisibility(View.VISIBLE);
            prev.setVisibility(View.VISIBLE);
        }
        else{
            next.setVisibility(View.VISIBLE);
            prev.setVisibility(View.VISIBLE);
        }
        Log.i("size", String.valueOf(questionMap.size()));
        //        TextView discp=findViewById(R.id.description);
//        discp.setText(question.description);
//        Log.d("ZIA", question.description);

        optionAdapter= new OptionAdapter(this,question);
        RecyclerView.LayoutManager layoutManager =new LinearLayoutManager(this);
        recyclerViewl.setLayoutManager(layoutManager);
        recyclerViewl.setAdapter(optionAdapter);
        recyclerViewl.setHasFixedSize(true);

    }



}