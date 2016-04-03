package me.kaini.example;

import android.os.Bundle;

import me.kaini.statusbar.base.BaseActivity;

/**
 * 这里显示输入状态时对状态栏的处理
 * @author Canney [chen.canney@gmail.com]
 */
public class EditActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
    }
}
