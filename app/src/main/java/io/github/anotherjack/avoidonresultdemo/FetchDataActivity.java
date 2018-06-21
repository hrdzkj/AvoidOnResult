package io.github.anotherjack.avoidonresultdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;

import communicate.AvoidOnResult;

import static io.github.anotherjack.avoidonresultdemo.EventCodeConstants.EVENT_CODE_PWS_FIRST_IDENTIFIER;


/**
 * Created by LiuYi on 2018/6/20.
 */

public class FetchDataActivity extends Activity{
    private EditText text;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetch_data);
        text = findViewById(R.id.text);
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("text",text.getText().toString());
                AvoidOnResult.postData(EVENT_CODE_PWS_FIRST_IDENTIFIER,intent);
            }
        });

        findViewById(R.id.binderCallBack).setOnClickListener(v -> finish());

        findViewById(R.id.back).performClick();
    }



}

