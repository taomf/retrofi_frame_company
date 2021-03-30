package com.taomf.retrofit_frame.core.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * date   : 2021/3/29/17:47
 * author : taomf
 * Desc   : Fragment的基类
 */

public abstract class BaseFragment extends RootFragment {
    private View contentView;

    protected Context mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        return contentView;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        init(view);
        process(savedInstanceState);
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
//        getActivity().overridePendingTransition(R.anim.slide_right_in,R.anim.slide_right_out);
    }

    protected abstract int layoutContentId();

    protected abstract void init();

    protected abstract void init(View view);

    protected abstract void process(Bundle savedInstanceState);
}
