package com.example.myapplication.database.Repostitory.Usecases;

import androidx.lifecycle.MutableLiveData;

import com.example.myapplication.database.Repostitory.Repository;

public class CountBE {
    private final Repository rep;

    public CountBE(Repository rep) {
        this.rep = rep;
    }

//    public MutableLiveData<Integer> countBE(){
//        return rep.countBE();
//    }
}
