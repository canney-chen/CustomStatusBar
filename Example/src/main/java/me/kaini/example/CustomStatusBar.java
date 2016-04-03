package me.kaini.example;

import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

import me.kaini.statusbar.AbsStatusBar;
import me.kaini.statusbar.view.BarItem;
import me.kaini.statusbar.view.StatusBar;

/**
 * 自定义状态栏的内容
 * @author Canney [chen.canney@gmail.com]
 */
public class CustomStatusBar extends AbsStatusBar {

    private ViewHolder mHolder = new ViewHolder();

    @Override
    protected int getStatusBarLayoutId() {
        //Xml创建的状态栏
        return R.layout.status_bar;
    }

    @Override
    protected void initStatusBar(StatusBar statusBar) {

        //查找从Xml加载的状态栏
        mHolder.signal = (BarItem)statusBar.findViewById(R.id.sb_signal);

        //代码创建
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.MATCH_PARENT);
        mHolder.icon2 = new BarItem(this);
        mHolder.icon2.setLayoutParams(lp);

        mHolder.icon2.setIcon(R.mipmap.signal);
        mHolder.icon2.setText("20");
        //设置文字可见状态
        mHolder.icon2.setTextVisibility(View.GONE);

        statusBar.addItem(mHolder.icon2);

        mHolder.signal.setBarItemClickListener(new BarItem.OnBarItemClickListener() {
            @Override
            public void onClick(BarItem item) {
                Intent intent = new Intent(CustomStatusBar.this, SignalActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
    }

    @Override
    protected int getStatusBarGravity(){
        return Gravity.BOTTOM;
    }

    static class ViewHolder{
        BarItem signal;
        BarItem icon2;
    }
}
