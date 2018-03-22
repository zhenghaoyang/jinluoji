package com.jiadianxi.jinluoji.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jiadianxi.jinluoji.R;
import com.jiadianxi.jinluoji.util.MyApplication;
import com.jiadianxi.jinluoji.util.UIUtils;
import com.squareup.picasso.Picasso;
import com.viewpagerindicator.TabPageIndicator;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/3/21.
 */

public class InvestFragment extends Fragment {


    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_search)
    EditText tvSearch;
    @Bind(R.id.iv_serach)
    ImageView ivSerach;
    @Bind(R.id.banner)
    Banner banner;
    @Bind(R.id.tv_news)
    TextView tvNews;
    @Bind(R.id.tabpage_invest)
    TabPageIndicator tabpageInvest;
    @Bind(R.id.vp_invest)
    ViewPager vpInvest;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_invest, null);
        ButterKnife.bind(this, view);

        initTitle();
        setBanner();
        initFragments();
        return view;
    }

    private List<Fragment> fragmentList = new ArrayList<>();

    private void initFragments() {
        //1.加载4个不同的Fragment
        ProductListFragment productListFragment = new ProductListFragment();
        ProductSuperFragment productRecommondFragment = new ProductSuperFragment();
        ProductYearFragment productHotFragment = new ProductYearFragment();
        ProductVIPFragment productVIPFragment = new ProductVIPFragment();
        //添加到集合中
        fragmentList.add(productListFragment);
        fragmentList.add(productRecommondFragment);
        fragmentList.add(productHotFragment);
        fragmentList.add(productVIPFragment);

        //2.ViewPager设置三个Fragment的显示
        MyAdapter adapter = new MyAdapter(getFragmentManager());
        vpInvest.setAdapter(adapter);
        //将TabPagerIndicator与ViewPager关联
        tabpageInvest.setViewPager(vpInvest);

    }

    /**
     * 提供PagerAdapter的实现
     * 如果ViewPager中加载的是Fragment,则提供的Adpater可以继承于具体的：FragmentStatePagerAdapter或FragmentPagerAdapter
     * FragmentStatePagerAdapter:适用于ViewPager中加载的Fragment过多，会根据最近最少使用算法，实现内存中Fragment的清理，避免溢出
     * FragmentPagerAdapter:适用于ViewPager中加载的Fragment不多时，系统不会清理已经加载的Fragment。
     */
    class MyAdapter extends FragmentPagerAdapter {


        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList == null ? 0 : fragmentList.size();
        }

        //提供TabPageIndicator的显示内容
        @Override
        public CharSequence getPageTitle(int position) {
            //方式一：
//            if(position == 0){
//                return "全部理财";
//            }else if(position == 1){
//                return "推荐理财";
//            }else if(position == 2){
//                return "热门理财";
//            }
            //方式二：
            return UIUtils.getStringArr(R.array.invest_tab)[position];
        }
    }


    private void setBanner() {
        //设置banner样式
        banner.setBannerStyle(BannerConfig.NOT_INDICATOR);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片地址构成的集合
        Uri uri = Uri.parse("android.resource://" + MyApplication.context.getPackageName() + "/" + R.drawable.banner);
        ArrayList<Uri> imagesUrl = new ArrayList<Uri>(1);
        imagesUrl.add(uri);
        banner.setImages(imagesUrl);
        banner.start();
    }

    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            /**
             常用的图片加载库：
             Universal Image Loader：一个强大的图片加载库，包含各种各样的配置，最老牌，使用也最广泛。
             Picasso: Square出品，必属精品。和OkHttp搭配起来更配呦！
             Volley ImageLoader：Google官方出品，可惜不能加载本地图片~
             Fresco：Facebook出的，天生骄傲！不是一般的强大。
             Glide：Google推荐的图片加载库，专注于流畅的滚动。
             */
            //Picasso 加载图片简单用法
            Picasso.with(context).load((Uri) path).into(imageView);

        }
    }


    private void initTitle() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
