package me.kaini.statusbar.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;


/**
 * 软键盘弹出或隐藏的回调的视图
 * {@link #setOnKeyboardStateListener(OnKeyboardStateListenter)} 可设置监听
 * @author Canney [chen.canney@gmail.com]
 */
public class RootFrameLayout extends FrameLayout {

    private OnKeyboardStateListenter mKeyboradListener;

    public RootFrameLayout(Context context) {
        super(context);
    }

    public RootFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RootFrameLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        boolean visible = false;
        if (0 != oldw && 0 != oldh) {
            if (h < oldh) {
                visible = true;
            } else {
                visible = false;
            }
            performKeyboardState(visible);
        }
    }

    public void setOnKeyboardStateListener(OnKeyboardStateListenter l){
        mKeyboradListener = l;
    }

    protected void performKeyboardState(boolean visible){
        if(mKeyboradListener != null){
            mKeyboradListener.onState(visible);
        }
    }

    /**
     * 键盘状态变更监听
     */
    public interface OnKeyboardStateListenter{

        /**
         *
         * @param state
         */
        void onState(boolean visible);
    }
}
