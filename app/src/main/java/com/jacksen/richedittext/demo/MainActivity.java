package com.jacksen.richedittext.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jacksen.richedittext.PhoneEditText;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.phone_edit_text)
    PhoneEditText phoneEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
//        phoneEditText.setError("12");

//        phoneEditText.getText().insert(1, "sdfds");
    }
}
