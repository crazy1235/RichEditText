package com.jacksen.richedittext;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.InputType;
import android.text.method.DigitsKeyListener;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by jacksen on 2015/12/9.
 */
public class RichEditText extends EditText {


    private String separator = Constants.SPACE;

    /**
     * 分割类型 eg:335 、344
     */
    private int splitStyle = 0;

    private int oldTextLength = 0;
    private int currentTextLength = 0;
    private int maxTextLength = 13;

    private int[] pattern = new int[]{3, 3, 5};

    private int[] insertPosition = new int[]{4, 8, 14};

    private MyTextWatcher myTextWatcher;

    private boolean flag = false;

    /**
     * input text type
     * eg: phone or idCard
     */
    private int type = -1;

    private int textLength = 0;


    public RichEditText(Context context) {
        this(context, null);
    }

    public RichEditText(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.editTextStyle);
    }

    public RichEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RichEditText, defStyleAttr, 0);
        type = typedArray.getInt(R.styleable.RichEditText_type, -1);
        if (-1 == type || 2 == type) {
            return;
        }
        splitStyle = typedArray.getInt(R.styleable.RichEditText_splitStyle, -1);
        int splitType = typedArray.getInt(R.styleable.RichEditText_separator, 0);
        if (-1 != splitType) {
            switch (type) {
                case 0:
                    textLength = Constants.PHONE_LENGTH;
                    maxTextLength = Constants.MAX_PHONE_LENGTH;
                    splitStyle = Constants.DEFAULT_PHONE_SPLIT_TYPE;
                    break;
                case 1:
                    textLength = Constants.ID_CARD_LENGTH;
                    maxTextLength = Constants.MAX_ID_CARD_LENGTH;
                    splitStyle = Constants.DEFAULT_ID_CARD_SPLIT_TYPE;
                    break;
                default:
                    textLength = Constants.PHONE_LENGTH;
                    maxTextLength = Constants.MAX_PHONE_LENGTH;
                    splitStyle = Constants.DEFAULT_PHONE_SPLIT_TYPE;
                    break;
            }
        }
        switch (splitType) {
            case 0:
                separator = Constants.SPACE;
                break;
            case 1:
                separator = Constants.HYPHEN;
                break;
            case 2:
                separator = Constants.BIAS;
                break;
            default:
                separator = Constants.SPACE;
                break;
        }
        typedArray.recycle();

        init();
    }

    /**
     * inputType="number" 的话是没法插入空格的
     */
    private void init() {
        switch (type) {
            case 0:
            default:
                if (InputType.TYPE_CLASS_PHONE != getInputType()) {
                    setInputType(InputType.TYPE_CLASS_PHONE);
                }
                setKeyListener(DigitsKeyListener.getInstance(" -/0123456789"));
                break;
            case 1:
                setInputType(InputType.TYPE_CLASS_NUMBER);
                setKeyListener(DigitsKeyListener.getInstance(" -/0123456789x"));
                break;
        }
        /*if (null == getHint()) {
            if (0 == type) {
                setHint(R.string.hint_phone);
            } else if (1 == type) {
                setHint(R.string.hint_inCard);
            }
        }*/
        myTextWatcher = new MyTextWatcher();
        addTextChangedListener(myTextWatcher);
        resetPattern();
    }

    /**
     *
     */
    private void resetPattern() {
        String temp = String.valueOf(splitStyle);
        if (3 != temp.length()) {
            if (0 == type) {
                pattern = Constants.DEFAULT_PHONE_PATTERN;
            } else if (1 == type) {
                pattern = Constants.DEFAULT_ID_CARD_PATTERN;
            }
        } else {
            if (splitStyle / 100 + splitStyle % 100 / 10 + splitStyle % 100 % 10 != textLength) {
                if (0 == type) {
                    pattern = Constants.DEFAULT_PHONE_PATTERN;
                } else if (1 == type) {
                    pattern = Constants.DEFAULT_ID_CARD_PATTERN;
                }
            } else {
                pattern = new int[]{splitStyle / 100, splitStyle % 100 / 10, splitStyle % 100 % 10};
            }
        }

        int tempLength = 0;
        for (int i = 0; i < pattern.length; i++) {
            tempLength += pattern[i] + 1;
            insertPosition[i] = tempLength;
        }
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
                if (currentTextLength == insertPosition[i]) {
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

    /**
     * return the text with no separator
     */
    public String getRealText() {
        return getText().toString().replaceAll(separator, "");
    }

}
