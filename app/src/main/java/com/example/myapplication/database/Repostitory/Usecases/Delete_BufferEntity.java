package com.example.myapplication.database.Repostitory.Usecases;

import com.example.myapplication.database.Repostitory.Repository;

public class Delete_BufferEntity {
    private final Repository rep;

    public Delete_BufferEntity(Repository rep) {
        this.rep = rep;
    }
    public void deleteBufferEntity(Boolean b){
        rep.deleteBufferEntity(b);
    }
}
