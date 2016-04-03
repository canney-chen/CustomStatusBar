package me.kaini.statusbar.base;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import me.kaini.statusbar.AbsStatusBar;


/**
 * 控制应用至后台或恢复到前端状态栏的隐藏及显示
 * @author Canney[chen.canney@mail.com]
 */
public class StatusBarApplication extends Application{

    private Handler mHandler = new Handler();

    private ActivityStack<Activity> mActStack;

    private Intent mStatusBarStateIntent = new Intent(AbsStatusBar.ACTION_STATUS_BAR_STATE);

    private ActivityLifecycleCallbacks mActCallback = new ActivityLifecycleCallbacks(){

        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            mActStack.push(activity);
        }

        @Override
        public void onActivityStarted(Activity activity) {

        }

        @Override
        public void onActivityResumed(Activity activity) {

            mStatusBarStateIntent.putExtra(AbsStatusBar.EXTRA_STATE, View.VISIBLE);
            sendBroadcast(mStatusBarStateIntent);
        }

        @Override
        public void onActivityPaused(final Activity activity) {
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Activity act = mActStack.peek();

                    if(act == activity){
                        mStatusBarStateIntent.putExtra(AbsStatusBar.EXTRA_STATE, View.GONE);
                        sendBroadcast(mStatusBarStateIntent);
                    }
                }
            }, 1);
        }

        @Override
        public void onActivityStopped(Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            mActStack.remove(activity);
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        mActStack = new ActivityStack<Activity>();

        registerActivityLifecycleCallbacks(mActCallback);
    }

    @Override
    public void onTerminate() {
        unregisterActivityLifecycleCallbacks(mActCallback);
        mActStack.clear();
        mActStack = null;

        super.onTerminate();
    }
}
