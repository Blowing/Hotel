package com.blowing.hotel.ui;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.blowing.hotel.R;
import com.blowing.hotel.ui.adapter.PictureAdapeter;

import java.util.ArrayList;
import java.util.List;

public class HotelActivity extends AppCompatActivity implements View.OnClickListener{

    private ViewPager mViewPager;
    private ProgressBar mSeekBar;
    private PictureAdapeter pictureAdapeter;
    private MapView mMapView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel);
        initView();
        initPictureAdapter();
        initMapView(savedInstanceState);
    }

    private void initView() {
        mViewPager = findViewById(R.id.hotel_vp_bg);
        mSeekBar = findViewById(R.id.hotel_seekbar_picturs);
        findViewById(R.id.hotel_lv_picture).setOnClickListener(this);
        findViewById(R.id.hotel_rv_map).setOnClickListener(this);
    }

    private void initMapView(Bundle savedInstanceState) {
         mMapView = (MapView) findViewById(R.id.hotel_map);
        mMapView.onCreate(savedInstanceState);// 此方法必须重写
        AMap aMap = mMapView.getMap();

        aMap.setTrafficEnabled(true);// 显示实时交通状况
        //地图模式可选类型：MAP_TYPE_NORMAL,MAP_TYPE_SATELLITE,MAP_TYPE_NIGHT
        aMap.setMapType(AMap.MAP_TYPE_NORMAL);// 卫星地图模式
    }

    private void initPictureAdapter() {
        final List<ImageView> imageViews = new ArrayList<>();
        ImageView imageView = new ImageView(this);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setImageResource(R.mipmap.bg_1);
        imageViews.add(imageView);

        ImageView imageView1 = new ImageView(this);
        imageView1.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        imageView1.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView1.setImageResource(R.mipmap.bg_2);
        imageViews.add(imageView1);


        ImageView imageView2 = new ImageView(this);
        imageView2.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        imageView2.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView2.setImageResource(R.mipmap.bg_3);
        imageViews.add(imageView2);


        pictureAdapeter = new PictureAdapeter(this, imageViews);
        mViewPager.setAdapter(pictureAdapeter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                int progress = 0;
                if (i == 0) {
                    progress = 33;
                } else if (i == 1) {
                    progress = 66;
                } else {
                    progress = 100;
                }
                mSeekBar.setProgress(progress);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.hotel_lv_picture:
                mViewPager.setVisibility(View.VISIBLE);
                mMapView.setVisibility(View.GONE);
                break;
            case R.id.hotel_rv_map:
                mMapView.setVisibility(View.VISIBLE);
                mViewPager.setVisibility(View.GONE);
                break;
        }
    }
}
