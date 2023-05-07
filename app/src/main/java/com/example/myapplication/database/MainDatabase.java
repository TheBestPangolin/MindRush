package com.example.myapplication.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {QuestionEntity.class}, version = 1)
public abstract class MainDatabase extends RoomDatabase {
    private static MainDatabase instance;

    public abstract MainDao MainDao();

    public static MainDatabase getInstance(Context context) {
        MainDatabase tempInstance = instance;
        if (tempInstance != null)
            return tempInstance;
        else tempInstance = Room
                .databaseBuilder(
                        context,
                        MainDatabase.class,
                        MainDatabase.class.getSimpleName()
                )
                .build();
        instance = tempInstance;
        return tempInstance;
    }
}
