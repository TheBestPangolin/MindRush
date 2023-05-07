package com.example.myapplication;

import java.util.ArrayList;

public class StringToListConverter {
    public static ArrayList<String> toList(String list){
        ArrayList<String> tempList=new ArrayList<>();
        helper(tempList,list);
        return tempList;
    }
    private static void helper(ArrayList<String> list, String original){
        if(!original.equals("")) {
            char[] temp = new char[original.indexOf(",") - 1];
            original.getChars(0, original.indexOf(",") - 1, temp, 0);
            String option = String.valueOf(temp);
            option = option.replaceAll(",", "");
            list.add(option);
            original = original.substring(original.indexOf(","));
            helper(list, original);
        }
    }
}
