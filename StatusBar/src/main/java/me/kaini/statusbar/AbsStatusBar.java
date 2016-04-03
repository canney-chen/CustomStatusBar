package me.kaini.statusbar;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import me.kaini.statusbar.view.BarItem;
import me.kaini.statusbar.view.StatusBar;

/**
 * 自定义状态栏
 * {@link #getStatusBarLayoutId()} 通过Xml配置状态栏
 * {@link #initStatusBar(StatusBar)}通过代码创建状态栏
 * @author Canney mail:chen.canney@gmail.com
 */
public abstract class AbsStatusBar extends Service {

    /**
     * {@link android.view.View#GONE}、{@link android.view.View#VISIBLE}
     */
    public static final String EXTRA_STATE = "me.kaini.extra.STATE";

    /**
     * 状态栏的状态
     * {@link #EXTRA_STATE}
     */
    public static final String ACTION_STATUS_BAR_STATE = "me.kaini.intent.action.STATUS_BAR_STATE";

    private WindowManager winMgr;

    private StatusBar statusBar;

    private int mStatusBarHeight;

    private BroadcastReceiver mStatusBarStateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int visibility = intent.getExtras().getInt(EXTRA_STATE, View.VISIBLE);
            setStatusBarVisibility(visibility);
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        winMgr = (WindowManager)getSystemService(Context.WINDOW_SERVICE);

        statusBar = getStatusBar();

        initStatusBar(statusBar);

        statusBar.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mStatusBarHeight = statusBar.getMeasuredHeight();

        winMgr.addView(statusBar, getStatusBarLayoutParams(mStatusBarHeight));

        IntentFilter statusBarFilter = new IntentFilter(ACTION_STATUS_BAR_STATE);
        registerReceiver(mStatusBarStateReceiver, statusBarFilter);

    }

    protected void setStatusBarVisibility(int visibility){
        statusBar.setVisibility(visibility);
    }

    private StatusBar getStatusBar(){
        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        ViewGroup v = (ViewGroup)inflater.inflate(getStatusBarLayoutId(), null);

        StatusBar statusBar;
        if(v instanceof  StatusBar){
            statusBar = (StatusBar)v;
        }else{
            statusBar = new StatusBar(this);
            int childCount = v.getChildCount();
            for (int i = 0; i < childCount; i++){
                BarItem item = (BarItem)v.getChildAt(i);
                statusBar.addItem(item, i);
            }
        }
        return statusBar;
    }

    protected WindowManager.LayoutParams getStatusBarLayoutParams(int statusBarHieght){
        WindowManager.LayoutParams wlp = new WindowManager.LayoutParams();

        wlp.format = PixelFormat.TRANSPARENT;

        wlp.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM;

        wlp.x = 0;
        wlp.y = 0;

        wlp.height = statusBarHieght;
        wlp.width = ViewGroup.LayoutParams.MATCH_PARENT;
        wlp.type = WindowManager.LayoutParams.TYPE_PHONE;
        wlp.gravity = getStatusBarGravity();
        return wlp;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /**
     * 获取状态栏布局,Root
     * @return
     */
    protected abstract int getStatusBarLayoutId();

    /**
     * 初始化状态图标
     * @param statusBar
     */
    protected void initStatusBar(StatusBar statusBar){}

    protected int getStatusBarGravity(){
        return Gravity.LEFT | Gravity.BOTTOM;
    }

    @Override
    public void onDestroy() {
        unregisterReceiver(mStatusBarStateReceiver);
        winMgr.removeView(statusBar);
        super.onDestroy();
    }
}
