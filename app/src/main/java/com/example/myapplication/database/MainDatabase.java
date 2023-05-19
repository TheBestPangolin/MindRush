package com.example.myapplication.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.AutoMigration;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.myapplication.database.Entities.BufferEntity;
import com.example.myapplication.database.Entities.QuestionEntity;
@Database(
        entities = {QuestionEntity.class}, version = 3
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
                ).addMigrations(MIGRATION_1_2,MIGRATION_2_3)
                .build();
        instance = tempInstance;
        return tempInstance;
    }
    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
        }
    };
    static final Migration MIGRATION_2_3 = new Migration(2,3) {
        @Override
        public void migrate( SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE 'Questions' ADD COLUMN 'True_answer' TEXT");
        }
    };
}
