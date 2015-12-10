package com.jacksen.richedittext;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.InputType;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by jacksen on 2015/12/9.
 */
public class PhoneEditText extends EditText {

    private static final String SPACE = " ";
    private static final String HYPHEN = "-";
    private static final String BIAS = "/";


    private String separator = SPACE;
    /**
     * 分割类型 eg:335 、344
     */
    private int sepStyle = 0;

    private int oldTextLength = 0;
    private int currentTextLength = 0;
    private int maxTextLength = 13;

    private int[] pattern = new int[]{3, 3, 5};
    private int[] intervals = new int[]{4, 8, 13};

    private MyTextWatcher myTextWatcher;

    private boolean flag = false;


    public PhoneEditText(Context context) {
        this(context, null);
    }

    public PhoneEditText(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.editTextStyle);
    }

    public PhoneEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.phoneEditText, defStyleAttr, 0);
        int sepType = typedArray.getInt(R.styleable.phoneEditText_separator, 0);
        switch (sepType) {
            case 0:
                separator = SPACE;
                break;
            case 1:
                separator = HYPHEN;
                break;
            case 2:
                separator = BIAS;
                break;
            default:
                separator = SPACE;
                break;
        }
        sepStyle = typedArray.getInt(R.styleable.phoneEditText_sepStyle, 335);
        typedArray.recycle();

        init();
    }

    /**
     *
     */
    private void init() {
        if (InputType.TYPE_CLASS_PHONE != getInputType()) {
            setInputType(InputType.TYPE_CLASS_PHONE);
        }
        myTextWatcher = new MyTextWatcher();
        addTextChangedListener(myTextWatcher);
        resetPattern();
    }

    /**
     *
     */
    private void resetPattern() {

    }

    /**
     *
     */
    private class MyTextWatcher implements android.text.TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            oldTextLength = s.length();
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            currentTextLength = s.length();
            if (currentTextLength > maxTextLength) {
                getText().delete(currentTextLength - 1, currentTextLength);
                return;
            }

            for (int i = 0; i < pattern.length; i++) {
                if (currentTextLength == intervals[i]) {
                    if (currentTextLength > oldTextLength) {
                        if (currentTextLength < maxTextLength) {
                            removeTextChangedListener(myTextWatcher);
                            flag = true;
                            getText().insert(currentTextLength - 1, separator);
                        }
                    } else if (oldTextLength <= maxTextLength) {
                        removeTextChangedListener(myTextWatcher);
                        flag = true;
                        getText().delete(currentTextLength - 1, currentTextLength);
                    }
                    if (flag) {
                        addTextChangedListener(myTextWatcher);
                    }
                    break;
                }
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

}
