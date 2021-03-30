package com.taomf.retrofit_frame;


import android.os.Bundle;
import android.view.View;

import com.taomf.retrofit_frame.core.base.IPresenter;
import com.taomf.retrofit_frame.core.base.MVPBaseActivity;

public class MainActivity extends MVPBaseActivity {


    @Override
    protected IPresenter createPresenter() {
        return null;
    }
    /**
     * date   : 2021/3/30/10:11
     * author : taomf
     * Desc   : 处理title点击事件
     */
    @Override
    protected void handleTitleBarEvent(int flag, View v) {

    }

    @Override
    protected int layoutContentId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }

    @Override
    protected void process(Bundle savedInstanceState) {

    }
}