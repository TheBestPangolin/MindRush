package com.example.myapplication.database.Repostitory;

import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.myapplication.BufferClass;
import com.example.myapplication.questions.Question;
import com.example.myapplication.database.Entities.BufferEntity;
import com.example.myapplication.database.EntityMapper;
import com.example.myapplication.database.MainDao;
import com.example.myapplication.database.Entities.QuestionEntity;

import java.nio.channels.AsynchronousByteChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class RepositoryImpl implements Repository {
    private final MainDao dao;
    private final ArrayList<Question> questionsList;
    private final ArrayList<ArrayList<Question>> difficultyOnlyQ;
    private Question question;
//    private final MutableLiveData<List<Boolean>> buffer;


    public RepositoryImpl(MainDao dao) {
        this.dao = dao;
        questionsList = new ArrayList<>();
        difficultyOnlyQ = new ArrayList<>();
//        buffer = new MutableLiveData<>();
        updateQuestionList();
    }

    @Override
    public ArrayList<Question> getAllQuestions() {
        return questionsList;
    }

    @Override
    public void addNewQuestion(Question q) {
        AsyncTask.execute(() -> {
            QuestionEntity qe = EntityMapper.toQuestionEntity(q);
            synchronized (dao) {
                dao.addNewQuestions(qe);
            }
        });
        updateQuestionList();
    }

    @Override
    public ArrayList<Question> getAllQuestionsByDiffX(Integer diff) {
        return difficultyOnlyQ.get(diff);
    }

    public void findByID(Integer ID) {
        AsyncTask.execute(() -> {
            synchronized (dao) {
                question = EntityMapper.toQuestion(dao.getByIndex(ID));
            }
        });
    }

    @Override
    public Question getByID(Integer index) {
        findByID(index);
        while (question == null) {

        }
        return question;
    }


    private void updateQuestionList() {
        AsyncTask.execute(() -> {
            synchronized (dao) {
                List<QuestionEntity> qe_data = dao.getAllQuestions();
                for (QuestionEntity qe : qe_data)
                    questionsList.add(EntityMapper.toQuestion(qe));
                for (int i = 0; i < BufferClass.getDifficultyCount(); i++) {
                    ArrayList<Question> temp=new ArrayList<>();
                    qe_data=dao.getAllQuestionsOfDiffX(i);
                    for (QuestionEntity qe:qe_data) {
                        temp.add(EntityMapper.toQuestion(qe));
                    }
                    difficultyOnlyQ.add(temp);
                }
            }
        });
//        int temp = 0;
//        for (int i = 0; i < BufferClass.getDifficultyCount(); i++) {
//            ArrayList<Question> tempList = new ArrayList<>();
//            for (int j = temp; j < questionsList.size(); j++) {
//                if (questionsList.get(j).getDifficulty()==i){
//                    tempList.add(questionsList.get(j));
//                }
//                else {
//                    temp=j;
//                    break;
//                }
//            }
//            difficultyOnlyQ.add(tempList);
//        }
    }


}
