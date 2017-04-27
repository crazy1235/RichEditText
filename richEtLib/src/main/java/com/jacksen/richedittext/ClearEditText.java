package com.jacksen.richedittext;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by jacksen on 2015/12/21.
 */
public class ClearEditText extends AppCompatEditText implements View.OnFocusChangeListener, View.OnTouchListener, TextWatcher {

    /**
     * show the clear icon or not
     */
    private boolean showClearIcon = false;

    /**
     * clear icon drawable
     */
    private Drawable clearDrawable = null;

    private Drawable clearDrawable_down = null;

    private Drawable clearDrawable_up = null;

    public ClearEditText(Context context) {
        this(context, null);
    }

    public ClearEditText(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.editTextStyle);
    }

    public ClearEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ClearEditText, defStyleAttr, 0);
        showClearIcon = typedArray.getBoolean(R.styleable.ClearEditText_clear, false);

        if (!showClearIcon) {
            return;
        }

        clearDrawable = typedArray.getDrawable(R.styleable.ClearEditText_clear_icon);
        clearDrawable_up = typedArray.getDrawable(R.styleable.ClearEditText_clear_icon_up);
        clearDrawable_down = typedArray.getDrawable(R.styleable.ClearEditText_clear_icon_down);

        typedArray.recycle();

        this.setOnFocusChangeListener(this);
        this.setOnTouchListener(this);
        this.addTextChangedListener(this);
        init(context);
    }

    /**
     *
     */
    private void init(Context context) {
        if (clearDrawable != null) {
            return;
        }
        if (clearDrawable_up != null && clearDrawable_down != null) {
            clearDrawable = getStateListDrawable();
        } else {
            if (clearDrawable_up != null) {
                clearDrawable = clearDrawable_up;
            } else if (clearDrawable_down != null) {
                clearDrawable = clearDrawable_down;
            } else {
                Drawable wrappedDrawable = DrawableCompat.wrap(ContextCompat.getDrawable(context, R.drawable.ic_close_black_24dp));
                DrawableCompat.setTint(wrappedDrawable, getCurrentHintTextColor());
                clearDrawable = wrappedDrawable;
            }
        }
    }

    /**
     * @return stateListDrawable --> selector
     */
    private StateListDrawable getStateListDrawable() {
        StateListDrawable sld = new StateListDrawable();
        sld.addState(new int[]{android.R.attr.state_pressed}, clearDrawable_down);
        sld.addState(new int[]{android.R.attr.state_checked}, clearDrawable_down);
        sld.addState(new int[]{android.R.attr.state_selected}, clearDrawable_down);
        sld.addState(new int[]{}, clearDrawable_up);
        return sld;
    }

    /**
     * show the clear drawable
     *
     * @param flag
     */
    private void showClearDrawable(boolean flag) {
        clearDrawable.setVisible(flag, false);
        Drawable[] compoundDrawables = getCompoundDrawables();
        this.setCompoundDrawablesWithIntrinsicBounds(compoundDrawables[0], compoundDrawables[1],
                flag ? clearDrawable : null, compoundDrawables[3]);
        this.setCompoundDrawablePadding(getCompoundDrawablePadding());
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            showClearDrawable(getText().length() > 0);
        } else {
            showClearDrawable(false);
        }
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        if (clearDrawable.isVisible()
                && getWidth() - getPaddingRight() - clearDrawable.getIntrinsicWidth() < x
                && getWidth() - getPaddingRight() > x
                && getHeight() - getPaddingBottom() - clearDrawable.getIntrinsicHeight() < y
                && getHeight() - getPaddingBottom() > y) {
            if (MotionEvent.ACTION_UP == event.getAction()) {
                setText("");
            }
            return true;
        }
        return false;
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        showClearDrawable(s.length() > 0);
    }

    @Override
    public void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {

    }
}
