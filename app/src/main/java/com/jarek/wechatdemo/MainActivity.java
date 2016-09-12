package com.jarek.wechatdemo;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.jarek.wechatdemo.adapter.TabFragmentAdapter;
import com.jarek.wechatdemo.view.TabContainerView;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;
import static android.content.Intent.FLAG_ACTIVITY_SINGLE_TOP;

/**
 * Activity主页
 * Created by Jarek(王健) on 2016/3/10.
 */
public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener{

    public static void startMainActivity (Activity activity, int tab) {
        Intent intent = new Intent(activity, MainActivity.class);
        intent.putExtra("tab", tab);
        intent.addFlags(FLAG_ACTIVITY_CLEAR_TOP | FLAG_ACTIVITY_SINGLE_TOP);
        activity.startActivity(intent);

    }

    /**
     * tab图标集合
     */
    private final int ICONS_RES[][] = {
            {
                    R.mipmap.ic_home_normal,
                    R.mipmap.ic_home_focus
            },
            {
                    R.mipmap.ic_message_normal,
                    R.mipmap.ic_message_focus
            },

            {
                    R.mipmap.ic_mine_normal,
                    R.mipmap.ic_mine_focus
            }
    };

    /** tab 颜色值*/
    private final int[] TAB_COLORS = new int []{
            R.color.main_bottom_tab_textcolor_normal,
            R.color.main_bottom_tab_textcolor_selected};

    private Fragment[] fragments = {
            new HomeFragment(),
            new MessageFragment(),
            new MineFragment()
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        initViews();

    }



    private void initViews() {
        TabFragmentAdapter mAdapter = new TabFragmentAdapter(getSupportFragmentManager(), fragments);
        ViewPager mPager = (ViewPager) findViewById(R.id.tab_pager);
        //设置当前可见Item左右可见page数，次范围内不会被销毁
        mPager.setOffscreenPageLimit(1);
        mPager.setAdapter(mAdapter);

        TabContainerView mTabLayout = (TabContainerView) findViewById(R.id.ll_tab_container);
        mTabLayout.setOnPageChangeListener(this);

        mTabLayout.initContainer(getResources().getStringArray(R.array.tab_main_title), ICONS_RES, TAB_COLORS, true);

        int width = getResources().getDimensionPixelSize(R.dimen.tab_icon_width);
        int height = getResources().getDimensionPixelSize(R.dimen.tab_icon_height);
        mTabLayout.setContainerLayout(R.layout.tab_container_view, R.id.iv_tab_icon, R.id.tv_tab_text, width, height);
//        mTabLayout.setSingleTextLayout(R.layout.tab_container_view, R.id.tv_tab_text);
//        mTabLayout.setSingleIconLayout(R.layout.tab_container_view, R.id.iv_tab_icon);

        mTabLayout.setViewPager(mPager);

        mPager.setCurrentItem(getIntent().getIntExtra("tab", 0));

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        for (int index = 0, len = fragments.length; index < len; index ++) {
            fragments[index].onHiddenChanged(index != position);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

    }
}
