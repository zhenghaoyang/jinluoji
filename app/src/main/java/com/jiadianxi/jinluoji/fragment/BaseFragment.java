package com.jiadianxi.jinluoji.fragment;

import android.support.v4.app.Fragment;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/3/21.
 */
public abstract class BaseFragment extends Fragment {

    //初始化界面的数据
    protected abstract void initData(String content);

    //初始化title
    protected abstract void initTitle();

    //提供布局
    public abstract int getLayoutId();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
