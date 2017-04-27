package com.jacksen.richedittext.demo;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jacksen.richedittext.ClearEditText;
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
    @Bind(R.id.clear_et)
    ClearEditText clearEt;
    @Bind(R.id.textView)
    TextView textView;
    @Bind(R.id.editText)
    EditText editText;
    @Bind(R.id.clear_et2)
    ClearEditText clearEt2;
    @Bind(R.id.textInputLayout)
    TextInputLayout textInputLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
//        phoneEditText.setError("12");
        Log.d("MainActivity", phoneEditText.getText().toString());

//        testBtn.setBackgroundDrawable(getStateListDrawable());

        Drawable[] drawables = textView.getCompoundDrawables();
        textView.setCompoundDrawablesWithIntrinsicBounds(drawables[0], drawables[1], getStateListDrawable(), drawables[3]);

        Drawable[] drawabless = editText.getCompoundDrawables();
        Drawable drawable = getStateListDrawable();
        editText.setCompoundDrawablesWithIntrinsicBounds(drawabless[0], drawabless[1], getResources().getDrawable(R.drawable.selector_clear_icon), drawabless[3]);
        textInputLayout.setError("error");
    }

    private StateListDrawable getStateListDrawable() {
        StateListDrawable sld = new StateListDrawable();
        sld.addState(new int[]{android.R.attr.state_pressed}, getResources().getDrawable(R.drawable.clear_input_down));
        sld.addState(new int[]{}, getResources().getDrawable(R.drawable.clear_input));
        return sld;
    }

    @OnClick(R.id.test_btn)
    void onBtnClick() {
        Toast.makeText(this, phoneEditText.getRealText(), Toast.LENGTH_SHORT).show();
    }
}
