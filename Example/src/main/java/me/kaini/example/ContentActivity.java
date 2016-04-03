package me.kaini.example;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.TextView;

import me.kaini.statusbar.base.BaseActivity;

/**
 * 显示状态栏被遮挡处理后的效果
 * @author Canney [chen.canney@gmail.com]
 */
public class ContentActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        TextView tv = (TextView)findViewById(R.id.tv_text);

        StringBuilder builder = new StringBuilder();

        for (int i =0 ; i < 200 ; i++){
            builder.append(String.valueOf(i+1)+".处理Activity遮挡示例共200行\n");
        }

        tv.setText(builder.toString());

    }
}
