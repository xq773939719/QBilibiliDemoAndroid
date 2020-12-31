package com.xq.bilibilidemo.common;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class Keyboard {
    View currentView;
    InputMethodManager inputMethodManager;

    public Keyboard(View view) {
        currentView = view;
        inputMethodManager = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
    }

    public void show() {
        inputMethodManager.showSoftInput(currentView, InputMethodManager.SHOW_IMPLICIT);
    }

    public void hide() {
        inputMethodManager.hideSoftInputFromWindow(currentView.getWindowToken(), 0);
    }

}
