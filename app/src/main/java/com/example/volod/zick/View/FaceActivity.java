package com.example.volod.zick.View;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.volod.zick.Presenter.FacePresenterImpl;
import com.example.volod.zick.Presenter.Presenters;
import com.example.volod.zick.R;
import com.example.volod.zick.UserData;

public class FaceActivity extends AppCompatActivity implements FaceInterface{
    private TextView cur_name;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.face);
        Presenters.FacePresenter presenter = new FacePresenterImpl(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.face);
        Button next = findViewById(R.id.next);
        cur_name = findViewById(R.id.cur_name);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.getNextCandidate();
            }
        });
    }

    @Override
    public void ViewUserData(UserData userData) {
        cur_name.setText(userData.getUsername());
    }
}
