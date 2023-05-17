package com.example.myapplication.questions;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;

public class Question {

    public ArrayList<String> options;
    public String question;
    public Integer difficulty;
    public int trueAnswer=1;
    public int index;


    public Question(ArrayList<String> options, String question, Integer difficulty,Integer index) {
        this.index=index;
        this.options = options;
        this.question = question;
        this.difficulty = difficulty;
        Log.d("listofQ", String.valueOf(trueAnswer));
    }

    public Integer getDifficulty() {
        return difficulty;
    }

    public int getTrueAnswer() {
        return trueAnswer;
    }

    public int getIndex() {
        return index;
    }

    public ArrayList<String> getOptions() {
        return options;
    }

    @Override
    public String toString() {
        return "Question{" +
                "options=" + options +
                ", question='" + question + '\'' +
                ", difficulty=" + difficulty +
                ", index=" + index +
                '}';
    }

    public boolean isQuestion_torf() {
        return (options.size() == 2);
    }

    public String getQuestion() {
        return question;
    }
    public void mixOptions(){
        Log.d("Why?",(options)+"\n"+trueAnswer);
        this.trueAnswer=1;
        ArrayList<String> temp = options;
        if (options.size() == 2) {
            this.options = new ArrayList<>();
            options.add("Правда");
            options.add("Ложь");
            if (temp.get(0).equals("Ложь")) {
                this.trueAnswer = 2;
            } else {
                this.trueAnswer = 1;
            }
        }
        else {
            String tempTrue=options.get(0);
            Collections.shuffle(options);
            for (int i = 0; i < options.size(); i++) {
                if(options.get(i).equals(tempTrue)){
                    trueAnswer=i+1;
                    break;
                }
            }
        }
    }


}
