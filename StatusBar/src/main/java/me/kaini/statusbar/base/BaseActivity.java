package me.kaini.statusbar.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.ViewGroup;

import me.kaini.statusbar.AbsStatusBar;
import me.kaini.statusbar.R;
import me.kaini.statusbar.view.RootFrameLayout;

/**
 * 解决状态栏遮挡
 * 1. 对输入法遮挡的处理
 * 2. 对Activity内容遮挡的处理
 * @author Canney[chen.canney@gmail.com]
 */
public abstract class BaseActivity extends ActionBarActivity {

    private Intent mStatusBarStateIntent = new Intent(AbsStatusBar.ACTION_STATUS_BAR_STATE);

    /**
     * 解决遮挡Activity内容的控件
     */
    private RootFrameLayout mRootContainer;

    private View mContentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mRootContainer = new RootFrameLayout(this);
        super.setContentView(mRootContainer);

        mRootContainer.setOnKeyboardStateListener(new RootFrameLayout.OnKeyboardStateListenter() {
            @Override
            public void onState(boolean visible) {

                int visibility = visible ? View.GONE : View.VISIBLE;
                mStatusBarStateIntent.putExtra(AbsStatusBar.EXTRA_STATE, visibility);
                sendBroadcast(mStatusBarStateIntent);

                fixedStatusBarBlock(visible);
            }
        });
    }

    @Override
    public void setContentView(View view) {
        setContentView(view,null);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        mContentView = view;
        mRootContainer.addView(view);
        if(params != null){
            mRootContainer.setLayoutParams(params);
        }

        fixedStatusBarBlock(false);
    }

    private void fixedStatusBarBlock(boolean statusBarVisible){
        changeBottomPadding(statusBarVisible);
    }

    /**
     * 调整BottomPadding解决状态栏遮挡的问题
     * @param statusBarVisible
     */
    private void changeBottomPadding(boolean statusBarVisible){
        int statusBarHeight = statusBarVisible ? 0 : getStatusBarHeight();
        mRootContainer.setPadding(mRootContainer.getPaddingLeft(), mRootContainer.getPaddingLeft(), mRootContainer.getPaddingRight(), mContentView.getPaddingBottom() + statusBarHeight);
    }

    /**
     * 可重写返回真实的自定义状态栏高度
     * @return
     */
    protected int getStatusBarHeight(){
        return (int)getResources().getDimension(R.dimen.status_bar_height);
    }

    @Override
    public void setContentView(int layoutResID) {
        View v = getLayoutInflater().inflate(layoutResID, null);
        setContentView(v);
    }

}
