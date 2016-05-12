package com.example.matt.zulugame;

/**
 * Created by matt on 2016/05/12.
 */
public class DisplayAnswer {
    private String answer;
    private Boolean correct;
    public DisplayAnswer(){
        answer="";
        correct=false;
    }

    public DisplayAnswer(String pAnswer, Boolean pCorrect){
        answer=pAnswer;
        correct=pCorrect;
    }
    public void setAnswer(String pAnswer){
        answer=pAnswer;
    }
    public void setCorrect(Boolean pCorrect){
        correct=pCorrect;
    }
    public String getAnswer(){
        return answer;
    }
    public Boolean getCorrect(){
        return correct;
    }
}
