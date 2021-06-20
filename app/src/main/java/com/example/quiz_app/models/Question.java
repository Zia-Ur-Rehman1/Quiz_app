package com.example.quiz_app.models;



public class Question {
    public String description = "";
    public String option1 = "";
    public String option2 = "";
    public String option3 = "";
    public String option4 = "";
    public String answer = "";
    public String user_answer = "";
    public Question(){};
    public Question(String description, String option1, String option2, String option3, String option4, String answer) {
        this.description = description;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.answer = answer;
    }

    public String getDescription() {
        return description;
    }

    public String getOption1() {
        return option1;
    }

    public String getOption2() {
        return option2;
    }

    public String getOption3() {
        return option3;
    }

    public String getOption4() {
        return option4;
    }

    public String getAnswer() {
        return answer;
    }

    public String getUser_answer() {
        return user_answer;
    }

}
