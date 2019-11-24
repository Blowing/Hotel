package com.blowing.hotel.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wujie
 * on 2019/11/24/024.
 */
public class HomeAdapter extends PagerAdapter {

    private Context  mContext;
    private List<View> views;
    private List<String> tabTitles;

    public HomeAdapter(Context mContext, List<View> views) {
        this.mContext = mContext;
        this.views = views;
        tabTitles = new ArrayList<>();
        //        homeTableLayout.addTab(homeTableLayout.newTab().setText("优享大床").setIcon(R.mipmap.ic_launcher));
//        homeTableLayout.addTab(homeTableLayout.newTab().setText("商务双床").setIcon(R.mipmap.ic_launcher));
//        homeTableLayout.addTab(homeTableLayout.newTab().setText("豪华大床").setIcon(R.mipmap.ic_launcher));
        tabTitles.add("优享大床");
        tabTitles.add("商务双床");
        tabTitles.add("豪华大床");
    }

    @Override
    public int getCount() {
        return views.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(views.get(position));
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        container.addView(views.get(position));
        return views.get(position);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles.get(position);
    }
}
