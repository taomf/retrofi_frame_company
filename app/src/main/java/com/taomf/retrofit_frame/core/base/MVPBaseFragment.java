package com.taomf.retrofit_frame.core.base;

public abstract class MVPBaseFragment<P extends IPresenter,V extends IView> extends BaseFragment {

    private P presenter;

    @Override
    protected void init() {
        presenter = createPresenter();
        presenter.attachView((V)this);
    }

    @Override
    public void onDestroyView() {
        presenter.detachView(false);
        super.onDestroyView();
    }

    protected abstract P createPresenter();

    public P getPresenter() {
        return presenter;
    }
}
