package com.example.wkl_android.widget.edit;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * @author li
 * @since 2019-07-26
 */
public class PriceWatcher implements TextWatcher {

    private EditText editText;
    private double max = Double.MAX_VALUE;

    public PriceWatcher() {
    }

    public PriceWatcher(double max) {
        this.max = max;
    }

    public void setMax(double max) {
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
        if (editText == null) {
            return;
        }
        // 输入小数位数限制
        if (s.toString().contains(".")) {
            if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                s = s.toString().subSequence(0,
                        s.toString().indexOf(".") + 3);
                editText.setText(s);
                editText.setSelection(s.length());
            }
        }
        if (s.toString().trim().equals(".")) {
            s = "0" + s;
            editText.setText(s);
            editText.setSelection(2);
        }

        if (s.toString().startsWith("0")
                && s.toString().trim().length() > 1) {
            if (!s.toString().substring(1, 2).equals(".")) {
                editText.setText(s.subSequence(1, 2));
                editText.setSelection(1);
            }
        }
    }

    @Override
    public void afterTextChanged(Editable s) {
        if (editText == null) {
            return;
        }
        if (!TextUtils.isEmpty(s.toString()) && !s.toString().startsWith(".")) {
            Double d = Double.valueOf(s.toString());
            if (d > max) {
                editText.setText(String.valueOf(max));
                editText.setSelection(editText.length());
            }
        }
    }
}
