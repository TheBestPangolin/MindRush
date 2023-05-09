package com.example.myapplication.View;

import static com.example.myapplication.View.MainActivity.bufferFragment;
import static com.example.myapplication.View.MainActivity.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.example.myapplication.R;
import com.example.myapplication.databinding.HowManyPlayersBinding;

public class BufferFragment extends Fragment {
    HowManyPlayersBinding bind;
    EditText et;
    boolean created=false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        bind=HowManyPlayersBinding.inflate(inflater,container,false);
        return bind.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        created=true;
        Button bt=getView().findViewById(R.id.button4);
        et=getView().findViewById(R.id.editTextText);
        bt.setOnClickListener(v -> {
            if(!String.valueOf(et.getText()).equals("")
                    &&(Integer.parseInt(String.valueOf(et.getText()))!=0)) {
                fragment.setNumberOfPlayers(Integer.parseInt(String.valueOf(et.getText())));
                if (MainActivity.isLaunched) {
                    getParentFragmentManager().beginTransaction()
                            .hide(bufferFragment)
                            .attach(fragment)
                            .commitNow();
                } else {
                    getParentFragmentManager().beginTransaction()
                            .hide(bufferFragment)
                            .add(R.id.main, fragment)
                            .commitNow();
                    MainActivity.isLaunched = true;
                }
            }
            else {
                Toast.makeText(getContext(), "Введите количество игроков!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(created) {
            et.getText().clear();
        }
    }
}
