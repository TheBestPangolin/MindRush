package com.example.myapplication.database.Repostitory.Usecases;

import com.example.myapplication.database.Repostitory.Repository;
import com.example.myapplication.questions.Question;

public class AddNewQ {
    private final Repository rep;

    public AddNewQ(Repository rep) {
        this.rep = rep;
    }
    public void addNewQuestion(Question q){
        rep.addNewQuestion(q);
    };
}
