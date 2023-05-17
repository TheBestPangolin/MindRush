package com.example.myapplication.database.Repostitory;

import androidx.lifecycle.MutableLiveData;

import com.example.myapplication.questions.Question;
import com.example.myapplication.database.Entities.BufferEntity;

import java.util.ArrayList;
import java.util.List;

public interface Repository {

    ArrayList<Question> getAllQuestions();

    void addNewQuestion(Question q);

    ArrayList<Question> getAllQuestionsByDiffX(Integer diff);

//    void addBufferEntity(BufferEntity be);
//
//    void deleteBufferEntity(Boolean b);
//
//    MutableLiveData<List<Boolean>> getIs_empty();
//
//    MutableLiveData<Integer> countBE();
}
