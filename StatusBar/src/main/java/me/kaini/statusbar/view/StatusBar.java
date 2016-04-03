package me.kaini.statusbar.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import me.kaini.statusbar.R;

/**
 * 状态栏
 * 使用{@link #addItem(BarItem)}添加状态栏显示的图标
 * @author Canney [chen.canney@gmail.com]
 */
public class StatusBar extends LinearLayout {

    public StatusBar(Context context) {
        super(context);
        init(null, 0);
    }

    public StatusBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public StatusBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {

    }

    public void addItem(BarItem item){
        addItem(item, -1);
    }

    public void addItem(BarItem item, int index){
        addView(item, index);
    }

}
