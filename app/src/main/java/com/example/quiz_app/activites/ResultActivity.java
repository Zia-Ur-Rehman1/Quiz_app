package com.example.quiz_app.activites;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.widget.TextView;

import com.example.quiz_app.R;
import com.example.quiz_app.models.Question;
import com.example.quiz_app.models.Quiz;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@RequiresApi(api = Build.VERSION_CODES.R)

public class ResultActivity extends AppCompatActivity {
    Quiz quiz;
    Map<String, Question> questions=Map.of();
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        setUpViews();
    }

    private void setUpViews() {
        String quizData= getIntent().getStringExtra("QUIZ");
        Gson gson = new Gson();
//      quiz=gson.fromJson(quizData, Quiz.class);
//       calculateScore();
//      setAnwserView();
    }

    private void setAnwserView() {
    StringBuilder stringBuilder = new StringBuilder();
    for(Map.Entry<String,Question> entry:quiz.questions.entrySet()){
        Question question=entry.getValue();
        stringBuilder.append("<font color '#18206f'><b>Question"+question.description+"</b></font><br/><br/>");
        stringBuilder.append("<font color '#18206f'><b>Question"+question.answer+"</b></font><br/><br/>");
    }
    if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.N){
        Spanned text = Html.fromHtml(stringBuilder.toString(), Html.FROM_HTML_MODE_COMPACT);
    }
    else{
        Spanned text = Html.fromHtml(stringBuilder.toString());
    }

    }

    private void calculateScore() {
        int score=0;

        for(int i=0; i<quiz.questions.size(); i++){
         Question  question= quiz.questions.get("question"+i);
         if(question.answer== question.user_answer);{
                score+=10;
            }
        }
        textView.setText("Your Score"+score);
    }
}