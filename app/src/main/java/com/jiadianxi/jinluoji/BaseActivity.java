package com.jiadianxi.jinluoji;



import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/3/21.
 */

public abstract class BaseActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayoutId());
        ButterKnife.bind(this);

        //将当前的activity添加到ActivityManager中
        ActivityManager.getInstance().add(this);

        initTitle();

        initData();

    }


    protected abstract void initData();

    protected abstract void initTitle();

    protected abstract int getLayoutId();

    //销毁当前的Activity
    public void removeCurrentActivity(){
        ActivityManager.getInstance().removeCurrent();
    }

    //销毁所有的activity
    public void removeAll(){
        ActivityManager.getInstance().removeAll();
    }

}
