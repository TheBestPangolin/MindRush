package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.Objects;

public class MyFragment extends Fragment {
//    public MyFragment() {
//
//    }
//    View view;
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//    }
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        view = inflater.inflate(R.layout.main_menu,container,false);
//        return view;
//    }
//
//
//
//    @Override
//    public void onViewCreated(@NonNull View view1, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        initLayout();
//    }
//
//    private void initLayout() {
//        Button singleplayerButton = view.findViewById(R.id.single_player_button);
//        Button multiplayerButton = view.findViewById(R.id.multiplayer_button);
//        Button infoAndeSettingsButton = view.findViewById(R.id.settings_and_info_button);
//        singleplayerButton.setOnClickListener(view1 -> {
//            Toast.makeText(getContext(), "single button works", Toast.LENGTH_SHORT).show();
//            LayoutInflater inflater = getLayoutInflater();
//            int a = R.layout.singleplayer_game;
//            ViewGroup b = (ViewGroup) getView();
//            inflateView(inflater,a,b);
//        });
//        multiplayerButton.setOnClickListener(view1 -> {
//            Toast.makeText(getContext(), "multiplayer button works", Toast.LENGTH_SHORT).show();
//        });
//        infoAndeSettingsButton.setOnClickListener(view1 -> {
//            Toast.makeText(getContext(), "info button works", Toast.LENGTH_SHORT).show();
//        });
//
//    }
//    private void inflateView(LayoutInflater inflater, int a, ViewGroup b){
//        view = inflater.inflate(a,b,true);
//    }
//    private void inflate(View view){
//        LayoutInflater inflater= getLayoutInflater();
//        ViewGroup v= new ViewGroup(getContext()) {
//            @Override
//            protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
//
//            }
//        };
//        view = inflater.inflate(R.layout.singleplayer_game,)
//    }
}