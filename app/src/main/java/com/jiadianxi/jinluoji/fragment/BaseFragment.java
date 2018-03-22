package com.jiadianxi.jinluoji.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jiadianxi.jinluoji.R;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/3/21.
 */
public abstract class BaseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), getLayoutId(), null);

        return view;
    }

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
