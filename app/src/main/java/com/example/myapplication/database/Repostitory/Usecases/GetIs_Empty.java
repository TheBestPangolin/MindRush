package com.example.myapplication.database.Repostitory.Usecases;

import com.example.myapplication.database.Repostitory.Repository;

public class GetIs_Empty {
    private final Repository rep;

    public GetIs_Empty(Repository rep) {
        this.rep = rep;
    }
    public Boolean getIs_empty(){
        return rep.getIs_empty();
    }
}
