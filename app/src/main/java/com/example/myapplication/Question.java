package com.example.myapplication;

import android.util.Log;

import java.util.Arrays;

public class Question {

    String[] options;
    String question;
    Integer difficulty;
    int trueAnswer=1;
    int index;

    public Question(String[] options, String question, Integer difficulty, int index) {
        this.index=index;
        this.options = options;
        Log.d("listofQ",Arrays.toString(options));
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

    @Override
    public String toString() {
        return "Question{" +
                "options=" + Arrays.toString(options) +
                ", question='" + question + '\'' +
                ", difficulty=" + difficulty +
                ", trueAnswer=" + trueAnswer +
                '}';
    }

    public boolean isQuestion_torf() {
        return (options.length == 2);
    }

    public String getQuestion() {
        return question;
    }
    public void mixOptions(){
        Log.d("Why?",Arrays.toString(options)+"\n"+trueAnswer);
        this.trueAnswer=1;
        String[] temp = options;
        if (options.length == 2) {
            this.options = new String[]{"Правда", "Ложь"};
            if (temp[0].equals("Ложь")) {
                this.trueAnswer = 2;
            } else {
                this.trueAnswer = 1;
            }
        }
        else {
            int temporary = BufferClass.random(0, 3);
            this.trueAnswer = temporary + 1;
            String why = this.options[0];
            this.options[0] = this.options[temporary];
            this.options[temporary] = why;
            for (int i = 0; i < BufferClass.random(0, 10); i++) {

                int tepr;
                do {
                    tepr = BufferClass.random(0, 3);
                    why = this.options[tepr];
                } while (tepr == this.trueAnswer-1);
                boolean wtf=false;
                String why1=null;
                int tepr1;
                while(true) {
                    tepr1 = BufferClass.random(0, 3);
                    if (tepr1 != this.trueAnswer-1) {
                        wtf=true;
                        why1=this.options[tepr1];
                        break;
                    }
                }
                if(wtf){
                    this.options[tepr1]=why;
                    this.options[tepr]=why1;
                }
//                BufferClass.setOptionsAsTheyWere(temp,index);
            }
        }
    }


}
