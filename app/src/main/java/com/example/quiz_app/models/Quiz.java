package com.example.quiz_app.models;

import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class Quiz {
    String id = "";
    String title = "";

      Map<String, Question> map = new HashMap<>();
    public  Quiz(){}
      public Quiz(String id, String title) {
        this.id = id;
        this.title = title;
    }
  public Map<String, Question> getMap() {
    return map;
  }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

}

