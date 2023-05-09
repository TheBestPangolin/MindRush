package com.example.myapplication.database;

import com.example.myapplication.database.Entities.QuestionEntity;
import com.example.myapplication.questions.Question;

public class EntityMapper {
    public static QuestionEntity toQuestionEntity(Question q){
        return new QuestionEntity(
                q.getIndex(),
                q.getQuestion(),
                q.getDifficulty(),
                q.getOptions()
        );
    }
    public static Question toQuestion(QuestionEntity qe){
        return new Question(
                qe.getOptions(),
                qe.getQuestion(),
                qe.getDifficulty(),
                qe.getId()
        );
    }
}
