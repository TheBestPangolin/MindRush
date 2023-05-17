package com.example.myapplication.View;

import static com.example.myapplication.View.MainActivity.answeredFragment;
import static com.example.myapplication.View.MainActivity.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewbinding.ViewBinding;

import com.example.myapplication.R;
import com.example.myapplication.databinding.CorrectAnswerBinding;
import com.example.myapplication.databinding.ReadyBinding;
import com.example.myapplication.databinding.WrongAnswerBinding;

public class AnsweredFragment extends Fragment {
    boolean isAnswerTrue=false;
    boolean isReady;
    int playerNumb;
    private Button bt;
    private TextView tv;
    boolean isLaunched=false;
    Integer time=0;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewBinding binding;
        if(!isReady) {
            binding = (isAnswerTrue ? (CorrectAnswerBinding.inflate(inflater, container, false))
                    : (WrongAnswerBinding.inflate(inflater, container, false)));
        }
        else {
            binding = ReadyBinding.inflate(inflater,container,false);
        }

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isLaunched=true;
        if(!isReady) {
            init_and_setup_View();
        }
        else init_and_setup_ready();
    }
    void init_and_setup_View(){
        if(isAnswerTrue){
            bt=getView().findViewById(R.id.next_true);
        }
        else {
            bt=getView().findViewById(R.id.next_wrong);
        }
        bt.setOnClickListener(view -> {
            fragment.myTimer.startWork(time);
            getParentFragmentManager().beginTransaction()
                    .detach(answeredFragment)
                    .show(fragment)
                    .commitNow();
        });
    }
    void init_and_setup_ready(){
        bt=getView().findViewById(R.id.button2);
        tv=getView().findViewById(R.id.textView5);
        tv.setText("Игрок номер "+playerNumb+", приготовься! \nТвоя очередь отвечать!");
        bt.setOnClickListener(v -> {
            isReady=false;
            fragment.myTimer.startWork(90);
            getParentFragmentManager().beginTransaction()
                    .detach(answeredFragment)
                    .show(fragment)
                    .commitNow();
        });
    }

    public void showIsAnswerCorrect(boolean isTrue){
        if(fragment.myTimer.running){
            time=fragment.myTimer.getAllTime();
            fragment.myTimer.stopWork();
        }
        isAnswerTrue=isTrue;
        getParentFragmentManager().beginTransaction()
                .attach(answeredFragment)
                .hide(fragment)
                .commitNow();
    }
    public void showReadyScreen(int player){
        playerNumb=player;
        isReady=true;
        getParentFragmentManager().beginTransaction()
                .attach(answeredFragment)
                .hide(fragment)
                .commitNow();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        isLaunched=false;
    }
}
