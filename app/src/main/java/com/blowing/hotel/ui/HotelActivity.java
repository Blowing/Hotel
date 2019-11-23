package com.blowing.hotel.ui;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.blowing.hotel.R;
import com.blowing.hotel.ui.adapter.PictureAdapeter;

import java.util.ArrayList;
import java.util.List;

public class HotelActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private ProgressBar mSeekBar;
    private PictureAdapeter pictureAdapeter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel);
        initView();
        initPictureAdapter();
    }

    private void initView() {
        mViewPager = findViewById(R.id.hotel_vp_bg);
        mSeekBar = findViewById(R.id.hotel_seekbar_picturs);

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
}
