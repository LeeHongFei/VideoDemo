package com.phoenix.videodemo;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.phoenix.videodemo.fragment.CartFragment;
import com.phoenix.videodemo.fragment.HomeFragment;
import com.phoenix.videodemo.fragment.MainFragment;
import com.phoenix.videodemo.fragment.MessageFragment;
import com.phoenix.videodemo.fragment.MineFragment;

public class MainActivity extends FragmentActivity implements View.OnClickListener {
    ImageView img_home;
    ImageView img_mine;
    ImageView img_main_icon;
    ImageView img_meaage;
    ImageView img_cart;

    //Fragment事务
    private FragmentTransaction ft;
    //当前选中的Fragment索引
    private int chooseIndex = -1;
    private FragmentOnTouchListener fragmentOnTouchListener;
    //Activity是否发生了回收
    private boolean isRecycle = false;

    private ScaleAnimation scaleAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scaleAnimation = new ScaleAnimation(0.7f, 1f, 0.7f, 1f, Animation.RELATIVE_TO_SELF, 0.5f,Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(200);
        img_home = (ImageView) findViewById(R.id.img_home);
        img_mine = (ImageView) findViewById(R.id.img_mine);
        img_main_icon = (ImageView) findViewById(R.id.img_main_icon);
        img_meaage = (ImageView) findViewById(R.id.img_meaage);
        img_cart = (ImageView) findViewById(R.id.img_cart);

        img_home.setOnClickListener(this);
        img_mine.setOnClickListener(this);
        img_main_icon.setOnClickListener(this);
        img_meaage.setOnClickListener(this);
        img_cart.setOnClickListener(this);

        if(savedInstanceState==null){
            //默认选中首页
            viewOnClick(img_home);
        }



    }

    @Override
    public void onClick(View v) {
        viewOnClick(v);
    }

    public void viewOnClick(View view){
        ft = getSupportFragmentManager().beginTransaction();
        switch (view.getId()) {
            case R.id.img_home:
                if(chooseIndex!=0){
                    chooseIndex = 0;
                    tabBgChange(chooseIndex);
                    //1哪个布局加载碎片，2加载哪个碎片，3标识符，2调用instantiate()方法的第三个参数，如果有数据传递就传一个Bundle，如果没有数据传递就传null
                    ft.replace(R.id.fl_main, HomeFragment.instantiate(MainActivity.this, HomeFragment.class.getName(), null), "homefragment");
                }
                img_home.setImageResource(R.mipmap.home_p);
                img_home.startAnimation(scaleAnimation);
                break;
            case R.id.img_mine:
                if(chooseIndex!=1){
                    chooseIndex = 1;
                    tabBgChange(chooseIndex);
                    ft.replace(R.id.fl_main, MineFragment.instantiate(MainActivity.this, MineFragment.class.getName(), null), "findfragment");
                }
                img_mine.startAnimation(scaleAnimation);
                break;
            case R.id.img_main_icon:
                if(chooseIndex!=2){
                    chooseIndex = 2;
                    tabBgChange(chooseIndex);
                    ft.replace(R.id.fl_main, MainFragment.instantiate(MainActivity.this, MainFragment.class.getName(), null), "pricefragment");
                }
                break;
            case R.id.img_meaage:
                if(chooseIndex!=3){
                    chooseIndex = 3;
                    tabBgChange(chooseIndex);
                    ft.replace(R.id.fl_main, MessageFragment.instantiate(MainActivity.this, MessageFragment.class.getName(), null), "questionfragment");
                }
                img_meaage.startAnimation(scaleAnimation);
                break;
            case R.id.img_cart:
                if(chooseIndex!=4){
                    chooseIndex = 4;
                    tabBgChange(chooseIndex);
                    ft.replace(R.id.fl_main, CartFragment.instantiate(MainActivity.this, CartFragment.class.getName(), null), "selffragment");
                }
                img_cart.startAnimation(scaleAnimation);
                break;
        }
        ft.commit();
    }

    //切换tab背景
    private void tabBgChange(int index){
        switch (index) {
            case 0:
                //给图标设置背景选择器，在这里通过setSelected改变当前选中或非选中的状态
                img_home.setSelected(true);

                img_mine.setSelected(false);
                img_main_icon.setSelected(false);
                img_meaage.setSelected(false);
                img_cart.setSelected(false);
                break;
            case 1:
                //略
                break;
            case 2:
                //略
                break;
            case 3:
                //略
                break;
            case 4:
                //略
                break;
        }
    }

    //只要屏幕被触摸就会被触发
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        //如果自定义的接口不为空，就将当前的触摸事件传递到接口当中
        if(fragmentOnTouchListener != null){
            fragmentOnTouchListener.onTouch(ev);
        }
        //接着执行这句代码保证原来的逻辑不会改变
        return super.dispatchTouchEvent(ev);
    }

    public interface FragmentOnTouchListener{
        public boolean onTouch(MotionEvent ev);
    }
    //注册自定义接口
    public void registerFragmentOnTouchListener(FragmentOnTouchListener fragmentOnTouchListener){
        this.fragmentOnTouchListener = fragmentOnTouchListener;
    }
    //注销自定义接口
    public void unregisterMyOnTouchListener(FragmentOnTouchListener myOnTouchListener){
        this.fragmentOnTouchListener = null;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //记录Fragment索引，防止Activity被系统回收时，Fragment错乱的问题
        outState.putBoolean("isRecycle", true);
        outState.putInt("chooseIndex", chooseIndex);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        isRecycle = savedInstanceState.getBoolean("isRecycle");
        chooseIndex = savedInstanceState.getInt("chooseIndex");
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(isRecycle){
            tabBgChange(chooseIndex);
        }
    }
}
