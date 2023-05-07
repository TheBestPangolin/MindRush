package com.example.myapplication;

import android.util.Log;

import androidx.annotation.Nullable;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class QuestionInserter {
     int difficultyCount = BufferClass.getDifficultyCount();
//    static int[] x=new int[difficultyCount];
    Integer lives=3;
    ArrayList<Question> bufferList;
    int numbOfQuestion;
    int trueAnswers=0;
    int wrongAnswers;
    int overallScore=0;
    int combo=0;
    int realNumber;

    boolean lost;
    final int difficultyModifier=20;
    final int winPoints=5;
//    static int[] numberOfQuestionsOfDifficultyX = new int[difficultyCount];
    ArrayList<Question> actualQuestions;
//    static ArrayList<Question> listOfQuestions = new ArrayList<>();
//
//    static ArrayList<String[]> optionList = new ArrayList<>();
//    static ArrayList<String> questionList = new ArrayList<>();
//    static ArrayList<Integer> difficultyList = new ArrayList<>();
//    static String allQuestions = "";
//
//    public static void addString(String add) {
//        allQuestions += add;
//    }

//    public static void clearAll() {
//        numberOfQuestionsOfDifficultyX = new int[difficultyCount];
//        x=new int[difficultyCount];
//        listOfQuestions=new ArrayList<>();
//        optionList=new ArrayList<>();
//        questionList=new ArrayList<>();
//        difficultyList=new ArrayList<>();
//    }

    public QuestionInserter(int realNumber) {
        this.realNumber=realNumber;
        bufferList = new ArrayList<>();
        actualQuestions = new ArrayList<>();
        what2(0);
    }

    public QuestionInserter() {

    }
    private void what(int diff) {
        if (bufferList.size() != 0) {
            bufferList.clear();
        }
        for (int i = 0; i < BufferClass.getListOfQuestions().size(); i++) {
            if (BufferClass.getListOfQuestions().get(i).getDifficulty().equals(diff)) {
              Question q=BufferClass.getListOfQuestions().get(i);


                bufferList.add(q);
                Log.d("WTF-temp", String.valueOf(bufferList));
            }
        }
        Log.d("WTF", String.valueOf(bufferList));
    }

    private void what2(int diff) {
        what(diff);
            for (int i = 0; i < BufferClass.getX(diff); i++) {
                int y = BufferClass.random(0, bufferList.size() - 1);
                while (actualQuestions.contains(bufferList.get(y))) {
                    y = BufferClass.random(0, bufferList.size() - 1);
                }
                actualQuestions.add(bufferList.get(y));
            }
            Log.d("diff", String.valueOf(actualQuestions));
        if (diff < difficultyCount - 1) {
            what2(diff + 1);
        }
    }

    public Integer getLives() {
        return lives;
    }


    public void addNumbOfQuestion(){
        numbOfQuestion++;
    }

    public int getNumbOfQuestion() {
        return numbOfQuestion;
    }
    public void answeredWrong(){
        this.wrongAnswers++;
        this.combo=0;
        this.lives--;
    }
    public void answeredTrue(){
        trueAnswers++;
        overallScore+=winPoints+(difficultyModifier*(actualQuestions.get(getNumbOfQuestion()).getDifficulty()))+(combo*2);
        combo++;
        this.numbOfQuestion++;
    }

    public int getRealNumber() {
        return realNumber;
    }



    public void playerLost() {
        lost=true;
    }
    public void check(){

    }

}