package com.example.myapplication.View;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.example.myapplication.AppStart;
import com.example.myapplication.BufferClass;
import com.example.myapplication.R;
import com.example.myapplication.database.Repostitory.Usecases.CountBE;
import com.example.myapplication.database.Repostitory.Usecases.GetAllQ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {


    @SuppressLint("StaticFieldLeak")
    public static MyFragment fragment=new MyFragment();
    @SuppressLint("StaticFieldLeak")
    public static Main_Menu_Fragment main_menu_fragment=new Main_Menu_Fragment();
    @SuppressLint("StaticFieldLeak")
    public static AnsweredFragment answeredFragment=new AnsweredFragment();
    @SuppressLint("StaticFieldLeak")
    public static BufferFragment bufferFragment=new BufferFragment();
//    public static LoadingFragment loadingFragment=new LoadingFragment();
    public static boolean isLaunched=false;
//    CountBE countBE=AppStart.getInstance().getCountBE();
    GetAllQ getAllQ=AppStart.getInstance().getGetAllQ();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Database_tag", String.valueOf(getAllQ.getAll()));
        if (getAllQ.getAll().size() == 0) {
            fileOpener();
        }
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.main, main_menu_fragment)
                    .commitNow();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.main, answeredFragment)
                    .detach(answeredFragment)
                    .add(R.id.main, bufferFragment)
                    .detach(bufferFragment)
                    .commitNow();
        }

    }

    @Override
    public void onBackPressed() {
        if(fragment.isLaunched){
            fragment.launchMainMenu();
        }
        else {
            getSupportFragmentManager().beginTransaction()
                    .detach(bufferFragment)
                    .attach(main_menu_fragment)
                    .commitNow();
        }
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    public  void fileOpener() {
//        if(countBE.countBE().getValue()<1
//                ||Boolean.TRUE.equals(AppStart.getInstance().getGetIs_empty().getIs_empty().getValue()))
//        {
            BufferedReader br;
            try {
                br = new BufferedReader(new InputStreamReader(getAssets().open("questions.txt")));
                String readline;
                while ((readline = br.readLine()) != null) {
                    BufferClass.addString(readline);
                }
                br.close();
                BufferClass.fileIsOpened();
            } catch (IOException e) {
                Log.d("da b", "why");
            }

    }




}