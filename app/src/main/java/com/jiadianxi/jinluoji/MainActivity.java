package com.jiadianxi.jinluoji;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jiadianxi.jinluoji.fragment.InvestFragment;
import com.jiadianxi.jinluoji.fragment.MeFragment;
import com.jiadianxi.jinluoji.fragment.MoreFragment;
import com.jiadianxi.jinluoji.util.UIUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @Bind(R.id.fl_main)
    FrameLayout flMain;
    @Bind(R.id.iv_main_invest)
    ImageView ivMainInvest;
    @Bind(R.id.tv_main_invest)
    TextView tvMainInvest;
    @Bind(R.id.ll_main_invest)
    LinearLayout llMainInvest;
    @Bind(R.id.iv_main_me)
    ImageView ivMainMe;
    @Bind(R.id.tv_main_me)
    TextView tvMainMe;
    @Bind(R.id.ll_main_me)
    LinearLayout llMainMe;
    @Bind(R.id.iv_main_more)
    ImageView ivMainMore;
    @Bind(R.id.tv_main_more)
    TextView tvMainMore;
    @Bind(R.id.ll_main_more)
    LinearLayout llMainMore;
    private FragmentTransaction transaction;

    @Override
    protected void initData() {

    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @OnClick({R.id.ll_main_invest, R.id.ll_main_me, R.id.ll_main_more})
    public void showTab(View view) {
//        Toast.makeText(MainActivity.this, "选择了具体的Tab", Toast.LENGTH_SHORT).show();
        switch (view.getId()) {
            case R.id.ll_main_invest://首页
                setSelect(0);
                break;
            case R.id.ll_main_me://投资
                setSelect(1);
                break;
            case R.id.ll_main_more://我的资产
                setSelect(2);
                break;
        }
    }


    private InvestFragment investFragment;
    private MeFragment meFragment;
    private MoreFragment moreFragment;

    //提供相应的fragment的显示
    private void setSelect(int i) {
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();

        //隐藏所有Fragment的显示
        hideFragments();
        //重置ImageView和TextView的显示状态
        resetTab();

        switch (i) {
            case 0:
                if (investFragment == null) {
                    investFragment = new InvestFragment();
                    transaction.add(R.id.fl_main, investFragment);
                }
                transaction.show(investFragment);

                //改变选中项的图片和文本颜色的变化
                ivMainInvest.setImageResource(R.drawable.invest);
                tvMainInvest.setTextColor(UIUtils.getColor(R.color.text_press));

                break;
            case 1:
                if (meFragment == null) {
                    meFragment = new MeFragment();
                    transaction.add(R.id.fl_main, meFragment);
                }
                transaction.show(meFragment);

                //改变选中项的图片和文本颜色的变化
                ivMainMe.setImageResource(R.drawable.me);
                tvMainMe.setTextColor(UIUtils.getColor(R.color.text_press));

                break;
            case 2:
                if (moreFragment == null) {
                    moreFragment = new MoreFragment();
                    transaction.add(R.id.fl_main, moreFragment);
                }
                transaction.show(moreFragment);
                //改变选中项的图片和文本颜色的变化
                ivMainMore.setImageResource(R.drawable.more);
                tvMainMore.setTextColor(UIUtils.getColor(R.color.text_press));
                break;
        }
        transaction.commit();//提交事务

    }

    private void hideFragments() {

        if (investFragment != null) {
            transaction.hide(investFragment);
        }
        if (meFragment != null) {
            transaction.hide(meFragment);
        }
        if (moreFragment != null) {
            transaction.hide(moreFragment);
        }

    }

    private void resetTab() {

        ivMainInvest.setImageResource(R.drawable.invest1);
        ivMainMe.setImageResource(R.drawable.me1);
        ivMainMore.setImageResource(R.drawable.more1);

        tvMainInvest.setTextColor(UIUtils.getColor(R.color.home_back_unselected));
        tvMainMe.setTextColor(UIUtils.getColor(R.color.home_back_unselected));
        tvMainMore.setTextColor(UIUtils.getColor(R.color.home_back_unselected));
        //这种方式也可以
//        tvMainMore.setTextColor(ContextCompat.getColor(this, R.color.home_back_unselected));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        //默认选择第一个页面
        setSelect(0);
    }
}

//        initToolbar();
//        expand();
//scrollview滚动状态监听
//        mScrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
//            @Override
//            public void onScrollChanged() {
//                //改变toolbar的透明度
//                changeToolbarAlpha();
//                //往上拉
//                if (mScrollView.getScrollY() > 0 && !isExpand) {
//                    reduce();
//                    isExpand = true;
//                }
//                //往上拉后，往下拉
//                else if (mScrollView.getScrollY() <= 0 && isExpand) {
//                    expand();
//                    isExpand = false;
//                }
//            }
//        });


//    private void initToolbar() {
//        LinearLayout.LayoutParams LayoutParams = (LinearLayout.LayoutParams) mSearchLayout.getLayoutParams();
//        LayoutParams.width = LayoutParams.MATCH_PARENT;
//        LayoutParams.setMargins(dip2px(10), dip2px(10), dip2px(10), dip2px(10));
//        mSearchLayout.setLayoutParams(LayoutParams);
//    }
//
//
//    private void changeToolbarAlpha() {
//        int scrollY = mScrollView.getScrollY();
//        //快速下拉会引起瞬间scrollY<0
//        if (scrollY < 0) {
//            toolbar.getBackground().mutate().setAlpha(0);
//            return;
//        }
//        //计算当前透明度比率
//        float radio = Math.min(1, scrollY / (ivImg.getHeight() - toolbar.getHeight() * 1f));
//        //设置透明度
//        toolbar.getBackground().mutate().setAlpha((int) (radio * 0xFF));
//    }
//
//
//    private void expand() {
//        //设置伸展状态时的布局
//        tvSearch.setText("搜索简书的内容和朋友");
//        LinearLayout.LayoutParams LayoutParams = (LinearLayout.LayoutParams) mSearchLayout.getLayoutParams();
//        LayoutParams.width = LayoutParams.MATCH_PARENT;
//        LayoutParams.setMargins(dip2px(10), dip2px(10), dip2px(10), dip2px(10));
//        mSearchLayout.setLayoutParams(LayoutParams);
//        //开始动画
//        beginDelayedTransition(mSearchLayout);
//    }
//
//    private void reduce() {
//        //设置收缩状态时的布局
//        tvSearch.setText("搜索");
//        LinearLayout.LayoutParams LayoutParams = (LinearLayout.LayoutParams) mSearchLayout.getLayoutParams();
//        LayoutParams.width = dip2px(80);
//        LayoutParams.setMargins(dip2px(10), dip2px(10), dip2px(10), dip2px(10));
//        mSearchLayout.setLayoutParams(LayoutParams);
//        //开始动画
//        beginDelayedTransition(mSearchLayout);
//    }
//
//    void beginDelayedTransition(ViewGroup view) {
//        mSet = new AutoTransition();
//        mSet.setDuration(300);
//        TransitionManager.beginDelayedTransition(view, mSet);
//    }
//
//    private int dip2px(float dpVale) {
//        final float scale = getResources().getDisplayMetrics().density;
//        return (int) (dpVale * scale + 0.5f);
//    }


