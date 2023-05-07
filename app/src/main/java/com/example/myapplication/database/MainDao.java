package com.example.myapplication.database;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MainDao {
    @Query("SELECT * FROM Questions order by Difficulty, Question ASC")
    List<QuestionEntity> getAllQuestions();

    @Query("Select * from Questions where Difficulty= :difficulty")
    List<QuestionEntity> getAllQuestionsOfDiffX(Integer difficulty);
}
