package com.example.myapplication.questions;

import android.util.Log;

import com.example.myapplication.BufferClass;

import java.util.ArrayList;

public class QuestionInserter {
    public int difficultyCount = BufferClass.getDifficultyCount();
//    static int[] x=new int[difficultyCount];
    public Integer lives=3;
    ArrayList<Question> bufferList;
    public int numbOfQuestion;
    public int trueAnswers=0;
    public int wrongAnswers=0;
    public int overallScore=0;
    public int combo=0;
    public int realNumber;
    public boolean lost;
    final int difficultyModifier=20;
    final int winPoints=5;
//    static int[] numberOfQuestionsOfDifficultyX = new int[difficultyCount];
    public ArrayList<Question> actualQuestions;
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