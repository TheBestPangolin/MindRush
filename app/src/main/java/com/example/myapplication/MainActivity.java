package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
//    private Button singleplayerBt;
//    private Button multiplayerBt;
//    private Button infoBt;
//    private static final String SINGLEPLAYER="SINGLEPLAYER";
//    private static final String MULTIPLAYER="MULTIPLAYER";
//    private static final String INFO="INFO";
    @SuppressLint("StaticFieldLeak")
    public static MyFragment fragment=new MyFragment();
    @SuppressLint("StaticFieldLeak")
    public static Main_Menu_Fragment main_menu_fragment=new Main_Menu_Fragment();
    public static AnsweredFragment answeredFragment=new AnsweredFragment();
    public static boolean isLaunched=false;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        fileOpener();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.main,main_menu_fragment)
                    .commitNow();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.main,answeredFragment)
                    .detach(answeredFragment)
                    .commitNow();
        }

    }

    @Override
    public void onBackPressed() {
        if(fragment.isLaunched){
            fragment.launchMainMenu();
        }
        else {
            super.onBackPressed();
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
//    private void initView() {
//        singleplayerBt=findViewById(R.id.single_player_button);
//        multiplayerBt=findViewById(R.id.multiplayer_button);
//        infoBt=findViewById(R.id.settings_and_info_button);
//    }
//
//    private void setupView() {
//        singleplayerBt.setOnClickListener(view -> {
//            launchGame();
//            fragment.launchTest(SINGLEPLAYER);
//        });
//        multiplayerBt.setOnClickListener(view -> {
//            launchGame();
//            fragment.launchTest(MULTIPLAYER);
//        });
//        infoBt.setOnClickListener(view -> {
//            launchGame();
//            fragment.launchTest(INFO);
//        });
//    }
//
    public  void fileOpener() {
        BufferedReader br;
        try {
            br = new BufferedReader(new InputStreamReader(getAssets().open("questions.txt")));
            String readline;
            while ((readline = br.readLine()) != null) {
                BufferClass.addString(readline);
            }
            br.close();
        } catch (IOException e) {
            Log.d("da b", "why");
        }
    }

//    private void launchGame(){
//        getSupportFragmentManager().beginTransaction()
//                .detach(main_menu_fragment)
//                .commitNow();
//        getSupportFragmentManager().beginTransaction()
//                .attach(fragment)
//                .commitNow();
//    }

}