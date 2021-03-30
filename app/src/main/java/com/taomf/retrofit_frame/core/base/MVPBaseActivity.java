package com.taomf.retrofit_frame.core.base;


import android.os.Bundle;

public abstract class MVPBaseActivity<P extends IPresenter> extends BaseActivity {

    private P presenter;


    @Override
    protected void bindingPresenter() {
        presenter = createPresenter();
        if (presenter != null) presenter.attachView(this);
    }

    protected void initOnCreate(Bundle savedInstanceState) {

    }

    protected abstract P createPresenter();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) presenter.detachView(false);
    }

    public P getPresenter() {
        return presenter;
    }


    @Override
    protected void toDoOnStart() {

    }

    @Override
    protected void toDoOnResume() {

    }

    @Override
    protected void toDoOnPause() {

    }

    @Override
    protected void toDoOnStop() {

    }

    @Override
    protected void toDoOnDestroy() {

    }
}
