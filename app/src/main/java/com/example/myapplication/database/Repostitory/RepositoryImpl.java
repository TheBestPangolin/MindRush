package com.example.myapplication.database.Repostitory;

import android.os.AsyncTask;

import androidx.lifecycle.MutableLiveData;

import com.example.myapplication.questions.Question;
import com.example.myapplication.database.Entities.BufferEntity;
import com.example.myapplication.database.EntityMapper;
import com.example.myapplication.database.MainDao;
import com.example.myapplication.database.Entities.QuestionEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class RepositoryImpl implements Repository {
    private final MainDao dao;
    private final MutableLiveData<ArrayList<Question>> questionsList;


    public RepositoryImpl(MainDao dao) {
        this.dao = dao;
        questionsList = new MutableLiveData<>();

        updateQuestionList();
    }

    @Override
    public MutableLiveData<ArrayList<Question>> getAllQuestions() {
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
    public MutableLiveData<ArrayList<Question>> getAllQuestionsByDiffX(Integer diff) {
        MutableLiveData<ArrayList<Question>> temporary = new MutableLiveData<>();
        AsyncTask.execute(() -> {
            synchronized (dao) {
                List<QuestionEntity> qe_data = dao.getAllQuestionsOfDiffX(diff);
                ArrayList<Question> temp = new ArrayList<>();
                for (QuestionEntity qe : qe_data)
                    temp.add(EntityMapper.toQuestion(qe));
                temporary.postValue(temp);
            }
        });
        return temporary;
    }

    @Override
    public void addBufferEntity(BufferEntity be) {
        AsyncTask.execute(() -> {
            synchronized (dao){
                dao.addBufferEntity(be);
            }
        });
    }

    @Override
    public void deleteBufferEntity(Boolean b) {
        AsyncTask.execute(() -> {
            synchronized (dao){
                dao.deleteBufferEntity(b);
            }
        });
    }

    @Override
    public MutableLiveData<Boolean> getIs_empty() {
        MutableLiveData<Boolean> b=new MutableLiveData<>();
        AsyncTask.execute(() -> {
            synchronized (dao){
                b.postValue(dao.getIs_empty());
            }
        });
        return b;
    }

    @Override
    public MutableLiveData<Integer> countBE() {
        MutableLiveData<Integer> i=new MutableLiveData<>();
        AsyncTask.execute(() -> {
            synchronized (dao){
                i.postValue(dao.countBE());
            }
        });
        return i;
    }

    private void updateQuestionList() {
        AsyncTask.execute(() -> {
            synchronized (dao) {
                List<QuestionEntity> qe_data = dao.getAllQuestions();
                ArrayList<Question> data = new ArrayList<>();
                for (QuestionEntity qe : qe_data)
                    data.add(EntityMapper.toQuestion(qe));
                questionsList.postValue(new ArrayList<>(data));
            }
        });
    }
}
