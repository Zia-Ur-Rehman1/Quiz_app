package com.example.quiz_app.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.quiz_app.R;
import com.example.quiz_app.adapters.OptionAdapter;
import com.example.quiz_app.models.Question;

import java.util.ArrayList;

public class QuestionActivity extends AppCompatActivity {
    Question question;
    OptionAdapter optionAdapter;
    RecyclerView recyclerViewl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        recyclerViewl=findViewById(R.id.optionList);
        bindViews();
    }
    private void bindViews(){
        question=new Question(
                "This is discrittion",
                "option 1",
                "option 2",
                "Option 3",
                "option 4",
                "Salas bs");
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