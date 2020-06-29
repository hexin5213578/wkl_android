package com.example.wkl_android.widget.edit;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * @author li
 * @since 2019-07-26
 */
public class AmountWatcher implements TextWatcher {

    private EditText editText;
    private int max = Integer.MAX_VALUE;

    public AmountWatcher() {
    }

    public AmountWatcher(int max) {
        this.max = max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public void bindEditText(EditText editText) {
        this.editText = editText;
        editText.addTextChangedListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (editText == null) {
            return;
        }
        if (!TextUtils.isEmpty(s.toString())) {
            if (Integer.valueOf(s.toString()) > max) {
                editText.setText(String.valueOf(max));
                editText.setSelection(editText.length());
            }
        }
    }
}
