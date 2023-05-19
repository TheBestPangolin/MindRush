package com.example.myapplication.questions;

import android.util.Log;

import com.example.myapplication.AppStart;
import com.example.myapplication.database.Repostitory.Usecases.GetAllQ;
import com.example.myapplication.database.Repostitory.Usecases.GetByID;

import java.util.ArrayList;
import java.util.Collections;

public class Question {

    public ArrayList<String> options;
    public String question;
    public Integer difficulty;
    public String true_string;
    public int trueAnswer=1;
    public int index;


    public Question(ArrayList<String> options, String question, Integer difficulty,Integer index, String true_answer) {
        true_string=true_answer;
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
        int temp=0;
        for (int i = 0; i < options.size(); i++) {
            if(options.get(i).equals(true_string)){
                temp=i+1;
                break;
            }
        }
        return temp;
    }

    public int getIndex() {
        return index;
    }

    public ArrayList<String> getOptions() {
        return options;
    }

    public String getTrue_string() {
        return true_string;
    }

    @Override
    public String toString() {
        return "Question{" +
                "options=" + options +
                ", question='" + question + '\'' +
                ", difficulty=" + difficulty +
                ", trueAnswer=" + trueAnswer +
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
            Collections.shuffle(options);

        }
    }
    //TODO Вернуть буффер Энтити, в ней хранить информацию о версиях. Если версия не совпадает, значит пересоздаём таблицу.

}
