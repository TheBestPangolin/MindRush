package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button singleplayerBt;
    private Button multiplayerBt;
    private Button infoBt;
    private static final String SINGLEPLAYER="SINGLEPLAYER";
    private static final String MULTIPLAYER="MULTIPLAYER";
    private static final String INFO="INFO";

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.main_menu);
        initView();
        setupView();
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
    private void initView() {
        singleplayerBt=findViewById(R.id.single_player_button);
        multiplayerBt=findViewById(R.id.multiplayer_button);
        infoBt=findViewById(R.id.settings_and_info_button);
    }

    private void setupView() {
        singleplayerBt.setOnClickListener(view -> {
            Intent intent=SecondActivity.launchTest(MainActivity.this,SINGLEPLAYER);
            startActivity(intent);
        });
        multiplayerBt.setOnClickListener(view -> {
            Intent intent=SecondActivity.launchTest(MainActivity.this,MULTIPLAYER);
            startActivity(intent);
        });
        infoBt.setOnClickListener(view -> {
            Intent intent=SecondActivity.launchTest(MainActivity.this,INFO);
            startActivity(intent);
        });
    }
    public static Intent launchTest(Context context){
        return new Intent(context,MainActivity.class);
    }
}