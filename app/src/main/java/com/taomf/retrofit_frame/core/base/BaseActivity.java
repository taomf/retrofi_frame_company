package com.taomf.retrofit_frame.core.base;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.taomf.retrofit_frame.R;
import com.taomf.retrofit_frame.core.Config;

/**
 * date   : 2021/3/29/17:37
 * author : taomf
 * Desc   : Activity基础类
 */
public abstract class BaseActivity extends RootActivity {

    private FrameLayout contentView;//具体内容页
    private View titleBar; //标题
    private TextView tvBack,tvLeftTitle,tvTite,tvRightDesc,tvRightDescIcon; //标题

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去除标题栏
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //去除状态栏

//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_base);
        contentView = findViewById(R.id.fl_content);
        titleBar = findViewById(R.id.titlebar_layout);

        contentView.addView(View.inflate(this, layoutContentId(), null));

        initTitlView();

        init(savedInstanceState);
        process(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();

        bindingPresenter();
        setTitleClickListener();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        listener = null;
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.slide_right_in,R.anim.slide_right_out);
    }

    protected  void setTitleClickListener(){
        if(tvBack == null || tvRightDescIcon == null)
            return;
        tvBack.setOnClickListener(listener);
        tvRightDescIcon.setOnClickListener(listener);
    }

    private  View.OnClickListener listener =  new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            if(v.equals(tvBack)){
                handleTitleBarEvent(Config.LEFT,v);
            }else if(v.equals(tvRightDescIcon)){
                handleTitleBarEvent(Config.RIGHT,v);
            }
        }
    };
    /**
     * date   : 2021/3/29/18:00
     * author : taomf
     * Desc   : 处理tiltleBar的点击事件
     */
    protected abstract void handleTitleBarEvent(int flag, View v);

    /**
     * date   : 2021/3/29/16:27
     * author : taomf
     * Desc   : 初始化title
     */
    protected  void initTitlView(){
        tvBack = titleBar.findViewById(R.id.tv_back_left_btn);
        tvLeftTitle = titleBar.findViewById(R.id.tv_back_left_title);
        tvTite = titleBar.findViewById(R.id.tv_title);
        tvRightDesc = titleBar.findViewById(R.id.tv_right_desc);
        tvRightDescIcon = titleBar.findViewById(R.id.tv_right_desc_icon);

    }

    public <T extends View> T getView(int id){
      return (T) titleBar.findViewById(id);
    }


    protected abstract int layoutContentId();

    protected abstract void bindingPresenter();

    protected abstract void init(Bundle savedInstanceState);

    protected abstract void process(Bundle savedInstanceState);


    protected  void setTitle(String title){
        this.tvTite.setText(title);
    }
    protected  void setLeftTitle(String title){
        this.tvLeftTitle.setVisibility(View.VISIBLE);
        this.tvLeftTitle.setText(title);
    }


    protected void setLeftBackBtn(int leftBackDraw){
        setLeftOrRight(tvBack,leftBackDraw,"");
    }

    protected void setLeftBackBtn(String leftBackText){
        setLeftOrRight(tvBack,0,leftBackText);
    }

    protected void setLeftBackTitle(int leftBackDraw){
        setLeftOrRight(tvLeftTitle,leftBackDraw,"");
    }

    protected void setLeftBackTitle(String leftBackText){
        setLeftOrRight(tvLeftTitle,0,leftBackText);
    }


    protected void setRightBtn(int rightBtnDraw){
        setLeftOrRight(tvRightDesc,rightBtnDraw,"");
    }

    protected void setRightBtn(String rightText){
        setLeftOrRight(tvRightDesc,0,rightText);
    }

    protected void setRightBtnIcon(int rightBtnDraw){
        setLeftOrRight(tvRightDescIcon,rightBtnDraw,"");
    }

    protected void setRightBtnIcon(String rightText){
        setLeftOrRight(tvRightDescIcon,0,rightText);
    }


    protected void setLeftOrRight(TextView btn, int res, String value){
        btn.setVisibility(View.VISIBLE);
        if (res > 0) {
            btn.setBackgroundResource(res);
        } else if (!TextUtils.isEmpty(value)) {
            btn.setText(value);
        } else {
            btn.setVisibility(View.GONE);
        }
    }
    protected void setTitleVisiable(boolean visiable){
        tvTite.setVisibility(visiable ? View.VISIBLE : View.GONE);
    }
    protected void setTitleBarVisiable(boolean visiable){
        titleBar.setVisibility(visiable ? View.VISIBLE : View.GONE);
    }

    protected void setBtnLeftVisiable(boolean visiable){
        tvBack.setVisibility(visiable ? View.VISIBLE : View.GONE);
    }

    protected void setBtnRightVisiable(boolean visiable){
        tvRightDesc.setVisibility(visiable ? View.VISIBLE : View.GONE);
    }

}
