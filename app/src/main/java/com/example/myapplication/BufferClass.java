package com.example.myapplication;

import android.util.Log;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BufferClass {
    static Integer difficultyCount = 2;
    static ArrayList<Question> listOfQuestions = new ArrayList<>();
    static Integer[] numberOfQuestionsOfDifficultyX = new Integer[difficultyCount];
    static ArrayList<String[]> optionList = new ArrayList<>();
    static ArrayList<String> questionList = new ArrayList<>();
    static ArrayList<Integer> difficultyList = new ArrayList<>();
    static String allQuestions = "";
    static int[] x=new int[difficultyCount];

    public static void addString(String add) {
        allQuestions += add;
    }

    public static Boolean isFileOpened=false;
    public static void fileIsOpened(){
        isFileOpened=true;
    }

    public static Boolean isIsFileOpened() {
        return isFileOpened;
    }
    public static void clearAll(){
        x=new int[difficultyCount];
        listOfQuestions = new ArrayList<>();
        optionList=new ArrayList<>();
    }

    public static void setQuestions() {
        Object config = Configuration.defaultConfiguration().jsonProvider().parse(allQuestions);
        if(!isFileOpened) {
            getQuestion(config);

        }
        getOptions(config);
        for (int i = 0; i < questionList.size(); i++) {
            Question q = new Question(optionList.get(i), questionList.get(i), difficultyList.get(i),i);
            q.mixOptions();
            listOfQuestions.add(q);
        }
        Log.d("difficulty", "listofQ" + String.valueOf(listOfQuestions));
        for (int i = 0; i < difficultyCount; i++) {
            numberOfQuestionsOfDifficultyX[i] = Collections.frequency(difficultyList, i);
            x[i]=random(1, numberOfQuestionsOfDifficultyX[i]);
        }
        Log.d("difficulty", "numberofQofDiffX" + Arrays.toString(numberOfQuestionsOfDifficultyX));

    }

    private static void getOptions(Object config) {
        List<String> trueOptions = JsonPath.read(config, "$..true_answer");
        List<String> options1 = JsonPath.read(config, "$..answer1");
        List<String> options2 = JsonPath.read(config, "$.questions.normal_questions[*].answer2");
        List<String> options3 = JsonPath.read(config, "$.questions.normal_questions[*].answer3");
        Log.d("JSON", trueOptions + "\n" + options1 + "\n" + options2 + "\n" + options3);
        for (int i = 0; i < trueOptions.size(); i++) {
            if ((trueOptions.get(i).equals("Правда") && options1.get(i).equals("Ложь")) ? false
                    : ((trueOptions.get(i).equals("Ложь") && options1.get(i).equals("Правда")) ? false : true)) {
                optionList.add(new String[]{trueOptions.get(i), options1.get(i), options2.get(i), options3.get(i)});
            } else {
                optionList.add(new String[]{trueOptions.get(i), options1.get(i)});
            }
        }
    }

    private static void getQuestion(Object config) {
        questionList = JsonPath.read(config, "$..question_title");
        difficultyList = JsonPath.read(config, "$..difficulty");

        Log.d("difficulty", String.valueOf(difficultyList));
    }

    public static ArrayList<Question> getListOfQuestions() {
        return listOfQuestions;
    }

    public static Integer getX(int index) {
        return x[index];
    }

    public static Integer getDifficultyCount() {
        return difficultyCount;
    }
    public static Integer random(int min, int max) {
        Integer i=(int) ((Math.random() * ((max + 1) - min)) + min);
        return (Integer) i;
    }
    public static void setOptionsAsTheyWere(String[] temp, int index){
        optionList.set(index,temp);
    }

}
