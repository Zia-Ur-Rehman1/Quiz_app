package com.example.quiz_app.activites;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.quiz_app.R;
import com.example.quiz_app.adapters.OptionAdapter;
import com.example.quiz_app.models.Question;
import com.example.quiz_app.models.Quiz;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RequiresApi(api = Build.VERSION_CODES.R)
public class QuestionActivity extends AppCompatActivity {
    Question question;
    Button next,prev,submit;
    List<Quiz> quiz=new ArrayList<>();
    FirebaseFirestore db;
    Map<String, Question> questions=Map.of();
    OptionAdapter optionAdapter;
    RecyclerView recyclerViewl;
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
            db.collection("quizzes")
                    .whereEqualTo("title", date)
                    .get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            if(queryDocumentSnapshots!=null && !queryDocumentSnapshots.isEmpty()){
                                Log.d("ZIA",queryDocumentSnapshots.toObjects(Quiz.class).get(0).questions.toString());
                                quiz.addAll(queryDocumentSnapshots.toObjects(Quiz.class));
                                questions = quiz.get(0).questions;
                                bindViews();

                            }
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
        else if(index==questions.size()) {
            submit.setVisibility(View.VISIBLE);
            prev.setVisibility(View.VISIBLE);
        }
        else{
            next.setVisibility(View.VISIBLE);
            prev.setVisibility(View.VISIBLE);
        }
        Log.d("TAG", String.valueOf(questions.size()));
        Question question = questions.get("question"+index);
        TextView discp=findViewById(R.id.description);
        discp.setText(question.description);
        Log.d("ZIA", question.description);
        optionAdapter= new OptionAdapter(this,question);
        RecyclerView.LayoutManager layoutManager =new LinearLayoutManager(this);
        recyclerViewl.setLayoutManager(layoutManager);
        recyclerViewl.setAdapter(optionAdapter);
        recyclerViewl.setHasFixedSize(true);

    }



}