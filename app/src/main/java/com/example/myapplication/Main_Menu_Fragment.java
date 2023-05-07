package com.example.myapplication;

import static com.example.myapplication.MainActivity.fragment;
import static com.example.myapplication.MainActivity.main_menu_fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.databinding.MainMenuBinding;
public class Main_Menu_Fragment extends Fragment {
    MainMenuBinding binding;
    private static final String SINGLEPLAYER="SINGLEPLAYER";
    private static final String MULTIPLAYER="MULTIPLAYER";
    private static final String INFO="INFO";
    public Button singleplayerBt;
    public Button multiplayerBt;
    public Button infoBt;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = MainMenuBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        setupView();
    }
    private void setupView() {
        singleplayerBt.setOnClickListener(view -> {
            fragment.launchTest(SINGLEPLAYER);
            launchGame();

        });
        multiplayerBt.setOnClickListener(view -> {
            fragment.launchTest(MULTIPLAYER);
            launchGame();

        });
        infoBt.setOnClickListener(view -> {
            fragment.launchTest(INFO);
            launchGame();

        });
    }
    private void initView() {

        singleplayerBt=getView().findViewById(R.id.single_player_button);
        multiplayerBt=getView().findViewById(R.id.multiplayer_button);
        infoBt=getView().findViewById(R.id.settings_and_info_button);
    }
    private void launchGame(){
        if(!MainActivity.isLaunched) {
            getParentFragmentManager().beginTransaction()
                    .hide(main_menu_fragment)
                    .add(R.id.main,fragment)
                    .commitNow();
            MainActivity.isLaunched=true;
        }
        else {
            getParentFragmentManager().beginTransaction()
                    .hide(main_menu_fragment)
                    .attach(fragment)
                    .commitNow();

        }
    }
}
