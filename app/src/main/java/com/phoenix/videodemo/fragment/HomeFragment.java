package com.phoenix.videodemo.fragment;

import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.phoenix.videodemo.R;
import com.phoenix.videodemo.adapter.MyHomeFragmentAdapter;
import com.phoenix.videodemo.adapter.ViewPagerAdapter;
import com.phoenix.videodemo.utils.DisplayUtil;
import com.phoenix.videodemo.utils.ObservableScrollView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by flashing on 2017/3/20.
 */

public class HomeFragment extends Fragment implements View.OnClickListener {
    TextView video_tv;
    TextView favorite_tv;
    TextView movie_tv;
    //游标
    ImageView cursor_iv;
    //ViewPager
    ViewPager vp;
    //ViewPager数据源
    private List<View> viewList = new ArrayList<>();
    //当前选中项
    private int currIndex = 0;
    //图片居中位移
    private int offset;
    //游标图片宽度
    private int bmpW;

    private MyHomeFragmentAdapter mAdapter;

    private Button menu_btn;
    private DrawerLayout mDrawerLayout = null;
    private ObservableScrollView sdfsd;
    private RelativeLayout ll_main;
    public static final int PAGE_ONE = 0;
    public static final int PAGE_TWO = 1;
    public static final int PAGE_THREE = 2;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        video_tv = (TextView) view.findViewById(R.id.video_tv);
        favorite_tv = (TextView) view.findViewById(R.id.favorite_tv);
        movie_tv = (TextView) view.findViewById(R.id.movie_tv);
        cursor_iv = (ImageView) view.findViewById(R.id.cursor_iv);
        menu_btn= (Button) view.findViewById(R.id.menu_btn);
        ll_main= (RelativeLayout) view.findViewById(R.id.ll_main);
        vp = (ViewPager) view.findViewById(R.id.viewpager);
        mDrawerLayout = (DrawerLayout)view.findViewById(R.id.drawer_layout);

        video_tv.setOnClickListener(this);
        favorite_tv.setOnClickListener(this);
        movie_tv.setOnClickListener(this);
        menu_btn.setOnClickListener(this);

        mAdapter=new MyHomeFragmentAdapter(this.getActivity().getSupportFragmentManager());


        //游标初始化
        initCursor();
        //页卡初始化
        initViewPager();

        return view;
    }

//    @Override
//    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        sdfsd = (ObservableScrollView) view.findViewById(R.id.sdfsd);
//        sdfsd.setScrollListener(new ObservableScrollView.ScrollListener() {
//            @Override
//            public void scrollOritention(int oritention) {
//                if (oritention==0x01){
////                    向上滑动的回调
//                    ll_main.setVisibility(View.VISIBLE);
////                    ll_main_title.setVisibility(View.VISIBLE);
////                    img_main_icon.setVisibility(View.VISIBLE);
//                }else if(oritention==0x10) {
////                    向下滑动的回调
//                    ll_main.setVisibility(View.GONE);
////                    ll_main_title.setVisibility(View.GONE);
////                    img_main_icon.setVisibility(View.GONE);
//                }else {
//                    return;
//                }
//            }
//        });
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.video_tv:
                vp.setCurrentItem(0);
                break;
            case R.id.favorite_tv:
                vp.setCurrentItem(1);
                break;
            case R.id.movie_tv:
                vp.setCurrentItem(2);
                break;
            case R.id.menu_btn:
                mDrawerLayout.openDrawer(Gravity.LEFT);
                break;
        }
    }

    //初始化游标
    private void initCursor(){
        //得到屏幕的宽度
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenW = dm.widthPixels- DisplayUtil.dip2px(getContext(), 120+20);

        //得到游标图片的宽度
        bmpW = BitmapFactory.decodeResource(getResources(), R.mipmap.zitixiaxian).getWidth();
//        bmpW = (screenW / 3) - DisplayUtil.dip2px(getContext(), 20);

        //计算图片居中需要的位移
        offset = (screenW / 3 - bmpW) / 2;
        //设置初始化位置
        Matrix matrix = new Matrix();
        matrix.postTranslate(offset, 0);
        cursor_iv.setImageMatrix(matrix);
    }

    //初始化ViewPager
    private void initViewPager(){
        //切换的四个界面初始化
        LinearLayout video_layout = (LinearLayout) getLayoutInflater(null).inflate(R.layout.fragment_home_video, null);
        LinearLayout favorite_layout = (LinearLayout) getLayoutInflater(null).inflate(R.layout.fragment_home_favorite, null);
        LinearLayout movie_layout = (LinearLayout) getLayoutInflater(null).inflate(R.layout.fragment_home_movie, null);


        //初始化视频布局
        initVideoLayout(video_layout);
        //初始化红人布局
        initFavoriteLayout(favorite_layout);
        //初始化电影布局
        initMovieLayout(movie_layout);

        //将四个界面的视图添加到ViewPager的数据源中
        viewList.add(video_layout);
        viewList.add(video_layout);
        viewList.add(video_layout);

        //ViewPager绑定适配器
        vp.setAdapter(new ViewPagerAdapter(viewList));
        //ViewPager初始选中第一视图
        vp.setCurrentItem(currIndex);
        //ViewPager绑定切换监听器
        vp.addOnPageChangeListener(new DefineOnPageChangeListener());
    }

    //初始化视频布局
    private void initVideoLayout(LinearLayout layout) {
//        TextView fragment_video_tv = (TextView) layout.findViewById(R.id.fragment_video_tv);
    }

    //初始化红人布局
    private void initFavoriteLayout(LinearLayout layout) {
        //同上，略
    }

    //初始化电影布局
    private void initMovieLayout(LinearLayout layout) {
        //同上，略
    }

    //ViewPager视图切换监听器
    public class DefineOnPageChangeListener implements ViewPager.OnPageChangeListener {
        //游标移动一个单位长度
        int one = offset * 2 + bmpW;
        //两个单位长度
        int two = one * 2;

        @Override
        public void onPageScrollStateChanged(int arg0) {}
        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {}

        @Override
        public void onPageSelected(int arg0) {
            Animation animation = null;
            switch (arg0) {
                case 0://视频
                    //如果当前在第二页卡
                    if(currIndex == 1){
                        //初始化切换页卡动画，平移与Y轴无关，所以Y值均为0，在X轴由one位置移动到0位置
                        animation = new TranslateAnimation(one, 0, 0, 0);
                    }
                    //如果当前在第三页卡
                    if(currIndex == 2){
                        //初始化切换页卡动画
                        animation = new TranslateAnimation(two, 0, 0, 0);
                    }
                    break;
                case 1://红人
                    //如果当前在第一页卡
                    if(currIndex == 0){
                        //初始化切换页卡动画
                        animation = new TranslateAnimation(0, one, 0, 0);
                    }
                    //如果当前在第三页卡
                    if(currIndex == 2){
                        //初始化切换页卡动画
                        animation = new TranslateAnimation(two, one, 0, 0);
                    }
                    break;
                case 2://电影
                    //如果当前在第一页卡
                    if(currIndex == 0){
                        //初始化切换页卡动画
                        animation = new TranslateAnimation(0, two, 0, 0);
                    }
                    //如果当前在第二页卡
                    if(currIndex == 1){
                        //初始化切换页卡动画
                        animation = new TranslateAnimation(one, two, 0, 0);
                    }
                    break;
            }
            currIndex = arg0;
            //切换页卡动画
            animation.setFillAfter(true);
            animation.setDuration(300);
            cursor_iv.startAnimation(animation);
        }

    }
}
