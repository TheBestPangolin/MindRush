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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class RepositoryImpl implements Repository {
    private final MainDao dao;
    private final ArrayList<Question> questionsList;
    private final ArrayList<ArrayList<Question>> difficultyOnlyQ;
//    private final MutableLiveData<List<Boolean>> buffer;


    public RepositoryImpl(MainDao dao) {
        this.dao = dao;
        questionsList = new ArrayList<>();
        difficultyOnlyQ=new ArrayList<>();
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

//    @Override
//    public void addBufferEntity(BufferEntity be) {
//        AsyncTask.execute(() -> {
//            synchronized (dao) {
//                dao.addBufferEntity(be);
//            }
//        });
//        updateBuffer();
//    }
//
//    @Override
//    public void deleteBufferEntity(Boolean b) {
//        AsyncTask.execute(() -> {
//            synchronized (dao) {
//                dao.deleteBufferEntity(b);
//            }
//        });
//        updateBuffer();
//    }
//
//    @Override
//    public MutableLiveData<List<Boolean>> getIs_empty() {
//        return buffer;
//    }
//
//    @Override
//    public MutableLiveData<Integer> countBE() {
//        MutableLiveData<Integer> i = new MutableLiveData<>();
//        AsyncTask.execute(() -> {
//            synchronized (dao) {
//                i.postValue(dao.countBE());
//            }
//        });
//        return i;
//    }

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

    }

//    private void updateBuffer() {
//        AsyncTask.execute(() -> {
//            synchronized (dao){
//                List<BufferEntity> temp=dao.getIs_empty();
//                List<Boolean> b=new ArrayList<>();
//                for (int i = 0; i < temp.size(); i++) {
//                    b.add(temp.get(i).getIs_empty());
//                }
//                buffer.postValue(b);
//
//            }
//        });
//    }
}
