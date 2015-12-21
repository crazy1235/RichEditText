package com.jacksen.richedittext.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import com.jacksen.richedittext.RichEditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.phone_edit_text)
    RichEditText phoneEditText;
    @Bind(R.id.test_btn)
    Button testBtn;
    @Bind(R.id.idcard_edit_text)
    RichEditText idcardEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
//        phoneEditText.setError("12");
        phoneEditText.getText().toString();

    }

    @OnClick(R.id.test_btn)
    void onBtnClick() {
        Toast.makeText(this, phoneEditText.getRealText(), Toast.LENGTH_SHORT).show();
    }
}
