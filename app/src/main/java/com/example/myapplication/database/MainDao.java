package com.example.myapplication.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.myapplication.database.Entities.BufferEntity;
import com.example.myapplication.database.Entities.QuestionEntity;

import java.util.List;

@Dao
public interface MainDao {
    @Query("SELECT * FROM Questions order by Difficulty, Question ASC")
    List<QuestionEntity> getAllQuestions();

    @Query("Select * from Questions where Difficulty= :difficulty")
    List<QuestionEntity> getAllQuestionsOfDiffX(Integer difficulty);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addNewQuestions(QuestionEntity question);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addBufferEntity(BufferEntity be);

    @Query("Delete from Buffer where Is_empty =:b")
    void deleteBufferEntity(Boolean b);

    @Query("select Is_empty from Buffer")
    Boolean getIs_empty();

    @Query("select count(Is_empty) from Buffer")
    Integer countBE();

}
