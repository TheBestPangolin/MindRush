package com.example.myapplication.questions;

import android.os.AsyncTask;
import android.util.Log;

import com.example.myapplication.AppStart;
import com.example.myapplication.BufferClass;
import com.example.myapplication.database.Repostitory.Usecases.GetAllQ;

import java.util.ArrayList;
import java.util.Collections;

public class QuestionInserter {
    public final int difficultyCount = BufferClass.getDifficultyCount();
    //    static int[] x=new int[difficultyCount];
    public Integer lives = 3;
    private ArrayList<Question> bufferList;
    public int numbOfQuestion;
    public int trueAnswers = 0;
    public int wrongAnswers = 0;
    public int overallScore = 0;
    public int combo = 0;
    public int realNumber;
    public boolean lost;
    final int difficultyModifier = 20;
    final int winPoints = 5;
    public ArrayList<Question> actualQuestions;
    public GetAllQ getAllQ = AppStart.getInstance().getGetAllQ();


    public QuestionInserter(int realNumber) {
        this.realNumber = realNumber;
        bufferList = new ArrayList<>();
        actualQuestions = new ArrayList<>();
        what2(0);
    }

    public QuestionInserter() {

    }

    private void what(int diff) {
        if (bufferList.size() != 0) {
            bufferList=new ArrayList<>();
        }
//        bufferList.addAll(temp);
        Log.d("WTF-code", String.valueOf(getAllQ.getAllByDiffX(diff)));
            bufferList.addAll(getAllQ.getAllByDiffX(diff));

        Log.d("WTF-temp", String.valueOf(bufferList));
    }

    private void what2(int diff) {
        what(diff);
        try{
            Question q=bufferList.get(0);
        }
        catch (IndexOutOfBoundsException e){
            what(diff);
        }
        for (int i = 0; i < BufferClass.getX(diff); i++) {
            int y = BufferClass.random(0, bufferList.size() - 1);
            while (actualQuestions.contains(bufferList.get(y))) {
                y = BufferClass.random(0, bufferList.size() - 1);
            }
            actualQuestions.add(bufferList.get(y));
        }
        for (Question q : actualQuestions) {
            q.mixOptions();
        }
        Log.d("diff", String.valueOf(actualQuestions));
        if (diff < difficultyCount - 1) {
            what2(diff + 1);
        }

    }

    public Integer getLives() {
        return lives;
    }


    public void addNumbOfQuestion() {
        numbOfQuestion++;
    }

    public int getNumbOfQuestion() {
        return numbOfQuestion;
    }

    public void answeredWrong() {
        this.wrongAnswers++;
        this.combo = 0;
        this.lives--;
    }

    public void answeredTrue() {
        trueAnswers++;
        overallScore += winPoints + (difficultyModifier * (actualQuestions.get(getNumbOfQuestion()).getDifficulty())) + (combo * 2);
        combo++;
        this.numbOfQuestion++;
    }

    public int getRealNumber() {
        return realNumber;
    }


    public void playerLost() {
        lost = true;
    }

    public void check() {

    }

}