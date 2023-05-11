package com.example.myapplication;

import com.example.myapplication.database.Repostitory.Usecases.GetAllQ;

import java.util.ArrayList;

public class BufferClass {
    static Integer difficultyCount = 2;

    static ArrayList<Integer> difficultyList = new ArrayList<>();
    static StringBuilder json = new StringBuilder();
    static String jsonString;
    static int[] x=new int[difficultyCount];

    public static void addString(String add) {
        json.append(add);
    }

    public static Boolean isFileOpened=false;
    public static void fileIsOpened(){
        isFileOpened=true;
        jsonString=json.toString();
        Database_Init.Init_Database();
    }

    public static Boolean isIsFileOpened() {
        return isFileOpened;
    }

    public static void setQuestions() {
        GetAllQ getAllQ=AppStart.getInstance().getGetAllQ();
        for (int i = 0; i < difficultyCount; i++) {
            difficultyList.add(getAllQ.getAllByDiffX(i).getValue().size());
        }
        for (int i = 0; i < difficultyCount; i++) {
            x[i]=random(1, difficultyList.get(i));
        }
    }



    public static Integer getX(int index) {
        return x[index];
    }

    public static Integer getDifficultyCount() {
        return difficultyCount;
    }
    public static Integer random(int min, int max) {
        Integer i=(int) ((Math.random() * ((max + 1) - min)) + min);
        return i;
    }


}
