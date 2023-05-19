package com.example.myapplication.database.Repostitory.Usecases;

import com.example.myapplication.database.Repostitory.Repository;
import com.example.myapplication.questions.Question;

public class GetByID {
    private final Repository rep;

    public GetByID(Repository rep) {
        this.rep = rep;
    }

    public Question getByID(Integer index){
        return rep.getByID(index);
    }
}
