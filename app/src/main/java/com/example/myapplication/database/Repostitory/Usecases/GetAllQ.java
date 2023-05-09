package com.example.myapplication.database.Repostitory.Usecases;

import androidx.lifecycle.MutableLiveData;

import com.example.myapplication.database.Repostitory.Repository;
import com.example.myapplication.questions.Question;

import java.util.ArrayList;

public class GetAllQ {
    private final Repository rep;

    public GetAllQ(Repository rep) {
        this.rep = rep;
    }

    public MutableLiveData<ArrayList<Question>> getAll(){
        return rep.getAllQuestions();
    }

    public MutableLiveData<ArrayList<Question>> getAllByDiffX(int diff){
        return rep.getAllQuestionsByDiffX(diff);
    }
}
