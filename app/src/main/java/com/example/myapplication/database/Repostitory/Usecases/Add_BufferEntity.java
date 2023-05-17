package com.example.myapplication.database.Repostitory.Usecases;

import com.example.myapplication.database.Entities.BufferEntity;
import com.example.myapplication.database.Repostitory.Repository;

public class Add_BufferEntity {
    private final Repository rep;

    public Add_BufferEntity(Repository rep) {
        this.rep = rep;
    }
//    public void addBufferEntity(BufferEntity be){
//        rep.addBufferEntity(be);
//    }
}
