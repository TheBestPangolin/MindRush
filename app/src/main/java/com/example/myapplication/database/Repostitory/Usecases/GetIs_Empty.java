package com.example.myapplication.database.Repostitory.Usecases;

import androidx.lifecycle.MutableLiveData;

import com.example.myapplication.database.Repostitory.Repository;

import java.util.List;

public class GetIs_Empty {
    private final Repository rep;

    public GetIs_Empty(Repository rep) {
        this.rep = rep;
    }
//    public MutableLiveData<List<Boolean>> getIs_empty(){
//        return rep.getIs_empty();
//    }
}
