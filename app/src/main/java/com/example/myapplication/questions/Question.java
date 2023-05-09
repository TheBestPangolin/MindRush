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
                "options=" + String.valueOf(options) +
                ", question='" + question + '\'' +
                ", difficulty=" + difficulty +
                ", trueAnswer=" + trueAnswer +
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
//            String why = this.options[0];
//            this.options[0] = this.options[temporary];
//            this.options[temporary] = why;
//            for (int i = 0; i < BufferClass.random(0, 10); i++) {
//
//                int tepr;
//                do {
//                    tepr = BufferClass.random(0, 3);
//                    why = this.options[tepr];
//                } while (tepr == this.trueAnswer-1);
//                boolean wtf=false;
//                String why1=null;
//                int tepr1;
//                while(true) {
//                    tepr1 = BufferClass.random(0, 3);
//                    if (tepr1 != this.trueAnswer-1) {
//                        wtf=true;
//                        why1=this.options[tepr1];
//                        break;
//                    }
//                }
//                if(wtf){
//                    this.options[tepr1]=why;
//                    this.options[tepr]=why1;
//                }
////                BufferClass.setOptionsAsTheyWere(temp,index);
//            }
        }
    }


}
