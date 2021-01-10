package com.xq.bilibilidemo.common;

import android.content.Context;
import android.text.InputType;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.BaseInputConnection;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

class KeyboardInputConnection extends BaseInputConnection {

    private static final String TAG = "XQ";

    private OnCommitTextListener onCommitTextListener;

    public KeyboardInputConnection(View targetView, boolean fullEditor) {
        super(targetView, fullEditor);
    }

    // 输入法的按键信息
    @Override
    public boolean sendKeyEvent(KeyEvent event) {
        switch (event.getKeyCode()) {
            case KeyEvent.KEYCODE_DEL:
                if (event.getAction() == KeyEvent.ACTION_UP
                        && onCommitTextListener != null) {
                    onCommitTextListener.onDeleteText();
                }
                break;
        }
        return super.sendKeyEvent(event);
    }

    // 输入法提交了一个 text
    @Override
    public boolean commitText(CharSequence text, int newCursorPosition) {
        if (onCommitTextListener != null) {
            onCommitTextListener.commitText(text, newCursorPosition);
        }
        return true;
    }

    @Override
    public boolean deleteSurroundingText(int beforeLength, int afterLength) {
        Log.d(TAG, "deleteSurroundingText " + "beforeLength=" + beforeLength + " afterLength=" + afterLength);
        return true;
    }

    @Override
    public boolean finishComposingText() {
        //结束组合文本输入的时候
        Log.d(TAG, "finishComposingText");
        return true;
    }

    public void setOnCommitTextListener(OnCommitTextListener onCommitTextListener) {
        this.onCommitTextListener = onCommitTextListener;
    }

    public interface OnCommitTextListener {
        boolean commitText(CharSequence text, int newCursorPosition);

        void onDeleteText();
    }
}

public class Keyboard extends androidx.appcompat.widget.AppCompatButton {

    private static final String TAG = "XQ";
    private StringBuilder content = new StringBuilder();
    private final InputMethodManager inputMethodManager;
    private KeyboardInputConnection keyboardInputConnection;

    public Keyboard(Context context) {
        super(context);
        inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
//        setFocusable(true);
//        setFocusableInTouchMode(true);
//        requestFocus();
    }

    public void click() {
        inputMethodManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        inputMethodManager.restartInput(this);
        Log.d(TAG, "click: " + true);
    }

    @Override
    public boolean onCheckIsTextEditor() {
        Log.d(TAG, "onCheckIsTextEditor: " + true);
        return true;
    }

    @Override
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        if (keyboardInputConnection != null) {
            return keyboardInputConnection;
        }
        editorInfo.imeOptions = EditorInfo.IME_FLAG_NO_EXTRACT_UI;
        editorInfo.inputType = InputType.TYPE_NULL;
        keyboardInputConnection = new KeyboardInputConnection(this, false);
        keyboardInputConnection.setOnCommitTextListener(new KeyboardInputConnection.OnCommitTextListener() {
            @Override
            public boolean commitText(CharSequence text, int newCursorPosition) {
                content.append(text);
                Log.d(TAG, "commitText: " + content);
                return true;
            }

            @Override
            public void onDeleteText() {
                if (content.length() <= 0) {
                    return;
                }
                content.deleteCharAt(content.length() - 1);
                Log.d(TAG, "onDeleteText: " + content);
            }
        });
        return keyboardInputConnection;
    }


}
