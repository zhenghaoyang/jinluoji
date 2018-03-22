package com.jiadianxi.jinluoji.bean;


import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Environment;

import com.jiadianxi.jinluoji.R;
import com.jiadianxi.jinluoji.util.MyApplication;

import java.util.List;

/**
 * Created by Administrator on 2018/3/22.
 */

public class Index {
    //    public Product product;
    public List<Image> images;
    Resources resources = MyApplication.context.getResources();
//Drawable.createFromPath(new File(Environment.getExternalStorageDirectory(), "camera.jpg").getAbsolutePath())
    Drawable drawable = resources.getDrawable(R.drawable.banner);
}
