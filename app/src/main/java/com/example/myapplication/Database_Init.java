package com.example.myapplication;

import android.util.Log;

import com.example.myapplication.View.MainActivity;
import com.example.myapplication.database.Entities.BufferEntity;
import com.example.myapplication.database.Repostitory.Usecases.AddNewQ;
import com.example.myapplication.database.Repostitory.Usecases.Add_BufferEntity;
import com.example.myapplication.database.Repostitory.Usecases.CountBE;
import com.example.myapplication.database.Repostitory.Usecases.Delete_BufferEntity;
import com.example.myapplication.database.Repostitory.Usecases.GetAllQ;
import com.example.myapplication.database.Repostitory.Usecases.GetIs_Empty;
import com.example.myapplication.questions.Question;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Database_Init {
    static Object config = Configuration.defaultConfiguration().jsonProvider().parse(BufferClass.jsonString);
//    static GetIs_Empty getIs_empty = AppStart.getInstance().getGetIs_empty();
    static GetAllQ getAllQ = AppStart.getInstance().getGetAllQ();
//    static Add_BufferEntity addBE = AppStart.getInstance().getAdd_BE();
//    static Delete_BufferEntity deleteBE = AppStart.getInstance().getDelete_BE();
//    static CountBE countBE = AppStart.getInstance().getCountBE();
    static AddNewQ addNewQ = AppStart.getInstance().getAddNewQ();

    public static void Init_Database() {
        if(getAllQ.getAll().size()==0) {
            Log.d("Database_tag", "Beginning to fill");
            ArrayList<String> temp = new ArrayList<>();
            ArrayList<String> questions_temp = JsonPath.read(config, "$..question_title");
            ArrayList<Integer> diff_temp = JsonPath.read(config, "$..difficulty");
            ArrayList<ArrayList<String>> options = new ArrayList<>();
            ArrayList<String> true_options = JsonPath.read(config, "$..true_answer");
            ArrayList<String> options1 = JsonPath.read(config, "$..answer1");
            ArrayList<String> options2 = JsonPath.read(config, "$..answer2");
            ArrayList<String> options3 = JsonPath.read(config, "$..answer3");
            for (int i = 0; i < questions_temp.size(); i++) {
                temp.add(true_options.get(i));
                temp.add(options1.get(i));
                try {
                    temp.add(options2.get(i));
                    temp.add(options3.get(i));
                } catch (IndexOutOfBoundsException ignored) {
                }
                options.add(temp);
                Log.d("wait", String.valueOf(temp));
                temp=new ArrayList<>();
            }
            for (int i = 0; i < questions_temp.size(); i++) {
                addNewQ.addNewQuestion(new Question(
                        options.get(i),
                        questions_temp.get(i),
                        diff_temp.get(i),
                        i,
                        true_options.get(i)
                ));

            }

        }
        else {
            Log.d("Database_tag","Everything is fine");
        }
//                deleteBE.deleteBufferEntity(true);
//                addBE.addBufferEntity(new BufferEntity(0, false));
//            }
//            else {
//                Log.d("Database_tag","Everything is fine");
//            }
//        }

    }
}
