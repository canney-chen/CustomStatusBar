package me.kaini.example;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import me.kaini.statusbar.AbsStatusBar;
import me.kaini.statusbar.base.BaseActivity;


public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnContent = (Button)findViewById(R.id.btn_contentact);
        Button btnEditact = (Button)findViewById(R.id.btn_editact);

        btnContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ContentActivity.class));
            }
        });

        btnEditact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, EditActivity.class));
            }
        });

        Intent service = new Intent(this, CustomStatusBar.class);
        startService(service);
    }

    @Override
    protected void onDestroy() {
        stopService(new Intent(this, CustomStatusBar.class));
        super.onDestroy();
    }
}
