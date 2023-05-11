package com.example.myapplication.View;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;


public class OptionButton  {
    TextView tv;
    Button bt;
    public void setText(CharSequence text) {
        tv.setText(text);
    }

    public void setTv(TextView tv) {
        this.tv = tv;
    }

    public void setBt(Button bt) {
        this.bt = bt;
    }

    public void setOnClickListener(View.OnClickListener listener){
        bt.setOnClickListener(listener);
    }
    public void setVisibility(int visibility){
        bt.setVisibility(visibility);
    }
}
