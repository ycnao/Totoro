package com.nadia.totoro.widget.verifycode;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.nadia.totoro.R;

/**
 * 验证码
 * author: Created by 闹闹 on 2019/1/26
 * version: 1.0.0
 */
public class VerifyCodeView extends RelativeLayout {

    public interface InputCompleteListener {

        void inputComplete();

        void invalidContent();
    }

    private static int MAX = 6;

    private EditText editText;
    private String inputContent;
    private TextView[] textViews;

    private InputCompleteListener inputCompleteListener;

    public VerifyCodeView(Context context) {
        this(context, null);
    }

    public VerifyCodeView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VerifyCodeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context, R.layout.view_verify_code, this);

        textViews = new TextView[MAX];
        textViews[0] = findViewById(R.id.tv_0);
        textViews[1] = findViewById(R.id.tv_1);
        textViews[2] = findViewById(R.id.tv_2);
        textViews[3] = findViewById(R.id.tv_3);
        textViews[4] = findViewById(R.id.tv_4);
        textViews[5] = findViewById(R.id.tv_5);
        editText = findViewById(R.id.edit_text_view);

        editText.setCursorVisible(false);//隐藏光标
        setEditTextListener();
    }

    private void setEditTextListener() {
        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                inputContent = editText.getText().toString();

                if (inputCompleteListener != null) {
                    if (inputContent.length() >= MAX) {
                        inputCompleteListener.inputComplete();
                    } else {
                        inputCompleteListener.invalidContent();
                    }
                }

                for (int i = 0; i < MAX; i++) {
                    if (i < inputContent.length()) {
                        textViews[i].setText(String.valueOf(inputContent.charAt(i)));
                    } else {
                        textViews[i].setText("");
                    }
                }
            }
        });
    }

    public void setInputCompleteListener(InputCompleteListener inputCompleteListener) {
        this.inputCompleteListener = inputCompleteListener;
    }

    public String getEditContent() {
        return inputContent;
    }

}