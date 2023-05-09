package com.example.myapplication.database.Entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity(
        tableName = QuestionEntity.NAME,
        indices = {@Index(QuestionEntity.ID)}
)
public class QuestionEntity {
    public static final String NAME="Questions";

    public static final String QUESTION="Question";

    public static final String ID="ID";

    public static final String DIFFICULTY="Difficulty";

    public static final String LIST_OF_OPTIONS="Options";

//    private static final String IS_TORF="Is_torf";

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = ID)
    private final Integer id;

    @ColumnInfo(name = QUESTION)
    private final String question;

    @ColumnInfo(name = DIFFICULTY)
    private final Integer difficulty;

    @ColumnInfo(name = LIST_OF_OPTIONS)
    private final ArrayList<String> options;

    public QuestionEntity(@NonNull Integer id, String question, Integer difficulty, ArrayList<String> options) {
        this.id = id;
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

    public ArrayList<String> getOptions() {
        return options;
    }

    @NonNull
    public Integer getId() {
        return id;
    }
}
