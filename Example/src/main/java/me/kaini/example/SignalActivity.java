package me.kaini.example;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import me.kaini.statusbar.base.BaseActivity;

/**
 * 示例，显示信号信息的界面
 */
public class SignalActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signal);
    }
}
