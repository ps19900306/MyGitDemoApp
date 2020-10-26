package com.ningwenqiang.glory.mygitdemoapp.ui_function.anylayer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.ningwenqiang.glory.mygitdemoapp.R;
import com.ningwenqiang.glory.mygitdemoapp.ui_function.notify.NotificationFunction;

public class AnylayerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        findViewById(R.id.tv_activity_normal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AnylayerActivity.this, NormalActivity.class));
            }
        });
        findViewById(R.id.tv_activity_full_screen).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AnylayerActivity.this, FullScreenActivity.class));
            }
        });
        findViewById(R.id.tv_activity_drag).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AnylayerActivity.this, DragActivity.class));
            }
        });
    }
}

