package com.example.switchlib;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 492515074@qq.com on 2020.10.19
 */
public class ToggleSwitch extends LinearLayout implements View.OnClickListener {

    private Context mContext;
    private LinearLayout mContainer;

    private List<String> mTitles = new ArrayList<>();
    private int mCheckedPosition;

    public ToggleSwitch(Context context) {
        this(context, null);
    }

    public ToggleSwitch(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ToggleSwitch(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        LayoutInflater.from(context).inflate(R.layout.toggle_switch_widget, this);
        mContainer = findViewById(R.id.toggle_switch_container);
    }

    private void addToggleButton(String str) {
        ToggleSwitchButton tsb = new ToggleSwitchButton(mContext);
        TextView tv = tsb.getTextView();
        tv.setText(str);
        tsb.getTextView().setOnClickListener(this);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, LayoutParams.MATCH_PARENT);
        layoutParams.weight = 1f;
        int margin = dp2px(2f);
        layoutParams.setMargins(margin, margin, margin, margin);
        mContainer.addView(tsb.getView(), layoutParams);
    }

    protected ToggleSwitchButton getToggleSwitchButton(int position) {
        return new ToggleSwitchButton(mContainer.getChildAt(position));
    }

    @Override
    public void onClick(View v) {
        LinearLayout toggleSwitchButton = (LinearLayout) v.getParent();
        int position = mContainer.indexOfChild(toggleSwitchButton);
        setCheckedTogglePosition(position);
    }

    /**
     * 选中当前项
     */
    public void setCheckedTogglePosition(int position) {
        disableAll();
        activate(position);
        setSeparatorVisibility(position);
        mCheckedPosition = position;
        if (null != mOnToggleSwitchChangeListener) {
            mOnToggleSwitchChangeListener.onToggleSwitchChangeListener(position);
        }
    }

    /**
     * 设置分隔的状态
     */
    private void setSeparatorVisibility(int activeIndex) {
        for (int i = 0; i < mContainer.getChildCount() - 1; i++) {
            ToggleSwitchButton toggleSwitchButton = new ToggleSwitchButton(mContainer.getChildAt(i));
            if (i == activeIndex || i == (activeIndex - 1)) {
                toggleSwitchButton.hideSeparator();
            } else {
                toggleSwitchButton.showSeparator();
            }
        }
    }

    private void disableAll() {
        for (int i = 0; i < mContainer.getChildCount(); i++) {
            disable(i);
        }
    }

    /**
     * 未选中状态
     */
    protected void disable(int position) {
        setColors(position, R.drawable.toggle_switch_btn_normal);
    }

    /**
     * 选中状态
     */
    protected void activate(int position) {
        setColors(position, R.drawable.toggle_switch_btn_checked);
    }

    protected void setColors(int position, @DrawableRes int resId) {
        getToggleSwitchButton(position).getView().setBackgroundResource(resId);
    }

    /**
     * 设置显示的数据列表
     * @param titles 要显示的数据
     */
    public void setTitles(List<String> titles) {
        if (titles == null || titles.isEmpty()) {
            throw new RuntimeException("The list of titles must contains at least 2 elements");
        }
        mTitles.clear();
        mTitles.addAll(titles);
        mContainer.removeAllViews();
        createToggleButtons();
    }

    /**
     * 根据Titles创建显示的数据
     */
    protected void createToggleButtons() {
        for (String str : mTitles) {
            addToggleButton(str);
        }
    }

    protected boolean isLast(int position) {
        return position == mContainer.getChildCount() - 1;
    }

    public int getCheckedPosition() {
        return mCheckedPosition;
    }

    public interface OnToggleSwitchChangeListener {
        /**
         * 当前选中的项
         *
         * @param position 当前项的位置
         */
        void onToggleSwitchChangeListener(int position);
    }

    private OnToggleSwitchChangeListener mOnToggleSwitchChangeListener = null;

    public void setOnToggleSwitchChangeListener(OnToggleSwitchChangeListener onToggleSwitchChangeListener) {
        this.mOnToggleSwitchChangeListener = onToggleSwitchChangeListener;
    }

    private int dp2px(float dpVal) {
        final float scale = Resources.getSystem().getDisplayMetrics().density;
        return (int) (dpVal * scale + 0.5f);
    }
}
