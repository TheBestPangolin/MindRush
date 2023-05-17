package com.example.myapplication;

import android.app.Application;

import com.example.myapplication.database.MainDatabase;
import com.example.myapplication.database.Repostitory.RepositoryImpl;
import com.example.myapplication.database.Repostitory.Usecases.*;

public class AppStart extends Application {

    private GetAllQ getAllQ;
    private AddNewQ addNewQ;
    private Add_BufferEntity add_BE;
    private Delete_BufferEntity delete_BE;
    private GetIs_Empty getIs_empty;
    private CountBE countBE;

    public GetAllQ getGetAllQ() {
        return getAllQ;
    }

    public CountBE getCountBE() {
        return countBE;
    }

    public AddNewQ getAddNewQ() {
        return addNewQ;
    }

    public Add_BufferEntity getAdd_BE() {
        return add_BE;
    }

    public Delete_BufferEntity getDelete_BE() {
        return delete_BE;
    }

    public GetIs_Empty getGetIs_empty() {
        return getIs_empty;
    }

    private static AppStart instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        MainDatabase db = MainDatabase.getInstance(this);
        RepositoryImpl rep = new RepositoryImpl(db.MainDao());
//        getIs_empty = new GetIs_Empty(rep);
//        countBE=new CountBE(rep);
        getAllQ = new GetAllQ(rep);
        addNewQ = new AddNewQ(rep);
//        add_BE = new Add_BufferEntity(rep);
//        delete_BE = new Delete_BufferEntity(rep);
    }

    public static AppStart getInstance() {
        return instance;
    }
}
