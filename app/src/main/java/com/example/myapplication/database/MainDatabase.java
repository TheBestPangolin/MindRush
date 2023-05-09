package com.example.myapplication.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.myapplication.database.Entities.BufferEntity;
import com.example.myapplication.database.Entities.QuestionEntity;

@Database(
        entities = {QuestionEntity.class, BufferEntity.class} , version = 1
)
@TypeConverters({Converter.class})
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
