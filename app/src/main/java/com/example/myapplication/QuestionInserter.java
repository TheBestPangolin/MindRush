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

//    public static void setQuestions() {
//        Object config = Configuration.defaultConfiguration().jsonProvider().parse(allQuestions);
//        getQuestion(config);
//        getOptions(config);
//        for (int i = 0; i < questionList.size(); i++) {
//            Question q = new Question(optionList.get(i), questionList.get(i), difficultyList.get(i));
//            q.mixOptions();
//            listOfQuestions.add(q);
//        }
//        Log.d("difficulty", "listofQ" + String.valueOf(listOfQuestions));
//        for (int i = 0; i < difficultyCount; i++) {
//            numberOfQuestionsOfDifficultyX[i] = Collections.frequency(difficultyList, i);
//            x[i]=random(1, numberOfQuestionsOfDifficultyX[i]);
//        }
//        Log.d("difficulty", "numberofQofDiffX" + Arrays.toString(numberOfQuestionsOfDifficultyX));
//
//    }

//    private static void getOptions(Object config) {
//        List<String> trueOptions = JsonPath.read(config, "$..true_answer");
//        List<String> options1 = JsonPath.read(config, "$..answer1");
//        List<String> options2 = JsonPath.read(config, "$.questions.normal_questions[*].answer2");
//        List<String> options3 = JsonPath.read(config, "$.questions.normal_questions[*].answer3");
//        Log.d("JSON", trueOptions + "\n" + options1 + "\n" + options2 + "\n" + options3);
//        for (int i = 0; i < trueOptions.size(); i++) {
//            if ((trueOptions.get(i).equals("Правда") && options1.get(i).equals("Ложь")) ? false
//                    : ((trueOptions.get(i).equals("Ложь") && options1.get(i).equals("Правда")) ? false : true)) {
//                optionList.add(new String[]{trueOptions.get(i), options1.get(i), options2.get(i), options3.get(i)});
//            } else {
//                optionList.add(new String[]{trueOptions.get(i), options1.get(i)});
//            }
//        }
//    }
//
//    private static void getQuestion(Object config) {
//        questionList = JsonPath.read(config, "$..question_title");
//        difficultyList = JsonPath.read(config, "$..difficulty");
//
//        Log.d("difficulty", String.valueOf(difficultyList));
//    }



    private void what(int diff) {
        if (bufferList.size() != 0) {
            bufferList.clear();
        }
        for (int i = 0; i < BufferClass.getListOfQuestions().size(); i++) {
            if (BufferClass.getListOfQuestions().get(i).getDifficulty().equals(diff)) {
              Question q=BufferClass.getListOfQuestions().get(i);
              q.mixOptions();

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

    public void setLives(Integer lives) {
        this.lives = lives;
    }
    public void addNumbOfQuestion(){
        numbOfQuestion++;
    }

    public int getNumbOfQuestion() {
        return numbOfQuestion;
    }
    public void answeredWrong(){
        wrongAnswers++;
        combo=0;
    }
    public void answeredTrue(){
        combo++;
        overallScore+=winPoints+(difficultyModifier*(actualQuestions.get(getNumbOfQuestion()).getDifficulty()))+(combo*2);
    }

    public int getRealNumber() {
        return realNumber;
    }



    public void playerLost() {
        lost=true;
    }


}