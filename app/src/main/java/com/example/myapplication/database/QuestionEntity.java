package com.example.myapplication.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import java.util.ArrayList;
import java.util.List;

@Entity(
        tableName = QuestionEntity.NAME,
        indices = {@Index(QuestionEntity.QUESTION)}
)
public class QuestionEntity {
    public static final String NAME="Questions";

    public static final String QUESTION="Question";

    public static final String DIFFICULTY="Difficulty";

    public static final String LIST_OF_OPTIONS="Options";

//    private static final String IS_TORF="Is_torf";
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = QUESTION)
    private final String question;

    @ColumnInfo(name = DIFFICULTY)
    private final Integer difficulty;


    @ColumnInfo(name = LIST_OF_OPTIONS)
    private final String options;

    public QuestionEntity(String question, Integer difficulty, String options) {
        this.question = question;
        this.difficulty = difficulty;
        this.options = options;
    }

    public String getQuestion() {
        return question;
    }

    public Integer getDifficulty() {
        return difficulty;
    }

    public String getOptions() {
        return options;
    }
}
