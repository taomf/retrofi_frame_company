package com.taomf.retrofit_frame.core.base;


public abstract class MVPBaseActivity<P extends IPresenter> extends BaseActivity {

    private P presenter;


    @Override
    protected void bindingPresenter() {
        presenter = createPresenter();
        if (presenter != null) presenter.attachView(this);
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
}
