package com.example.switchlib;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

/**
 * @author 492515074@qq.com on 2020.10.19
 */
public class ToggleSwitchButton {
    private View view;
    private TextView textView;
    private View separator;

    public ToggleSwitchButton(Context context) {
        this(LayoutInflater.from(context).inflate(R.layout.toggle_switch_button, null));
    }

    public ToggleSwitchButton(View view) {
        this.view = view;
        this.textView = view.findViewById(R.id.text_view);
        this.separator = view.findViewById(R.id.separator);
    }

    public View getView() {
        return view;
    }

    public TextView getTextView() {
        return textView;
    }

    public void showSeparator() {
        separator.setVisibility(View.VISIBLE);
    }

    public void hideSeparator() {
        separator.setVisibility(View.INVISIBLE);
    }
}