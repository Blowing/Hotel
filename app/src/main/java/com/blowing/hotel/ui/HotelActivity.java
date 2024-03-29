package com.blowing.hotel.ui;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.MyLocationStyle;
import com.blowing.hotel.R;
import com.blowing.hotel.ui.adapter.HomeAdapter;
import com.blowing.hotel.ui.adapter.PictureAdapeter;
import com.eightbitlab.supportrenderscriptblur.SupportRenderScriptBlur;

import java.util.ArrayList;
import java.util.List;

import eightbitlab.com.blurview.BlurView;

public class HotelActivity extends AppCompatActivity implements View.OnClickListener{

    private ViewPager mViewPager;
    private ProgressBar mSeekBar;
    private PictureAdapeter pictureAdapeter;
    private MapView mMapView;

    private TextView timeSelectTv;
    private RelativeLayout timeShowRv;
    private BlurView timeOkBtn;

    private LinearLayout selectHomeLy;
    private ViewPager homeViewPager;
    private TabLayout homeTableLayout;

    private BlurView blurViewTop;
    private BlurView blurViewSelectTime;
    private BlurView blurViewOk;
    private BlurView blurViewVp;
    private BlurView blurViewTb;

    private ViewGroup root;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel);
        initView();
        initPictureAdapter();
        initMapView(savedInstanceState);
        initActionView();
        initHomeAdapter();
    }

    private void initView() {
        root = findViewById(R.id.root);
        mViewPager = findViewById(R.id.hotel_vp_bg);
        mSeekBar = findViewById(R.id.hotel_seekbar_picturs);
        findViewById(R.id.hotel_lv_picture).setOnClickListener(this);
        findViewById(R.id.hotel_rv_map).setOnClickListener(this);
        findViewById(R.id.hotel_iv_close).setOnClickListener(this);
        initBlurView();
    }

    private void initBlurView() {
        blurViewTop = findViewById(R.id.blurview_top);
        blurViewOk = findViewById(R.id.hotel_bv_time_ok);
        blurViewSelectTime = findViewById(R.id.blurview_slectTime);
        blurViewVp = findViewById(R.id.blurview_vp);
        blurViewTb = findViewById(R.id.blurview_tb);


        final float radius = 25f;
        final float minBlurRadius = 10f;
        final float step = 4f;

        //set background, if your root layout doesn't have one
        final Drawable windowBackground = getWindow().getDecorView().getBackground();

        blurViewTop.setupWith(root)
                .setFrameClearDrawable(windowBackground)
                .setBlurAlgorithm(new SupportRenderScriptBlur(this))
                .setBlurRadius(radius)
                .setHasFixedTransformationMatrix(true);

        blurViewOk.setupWith(root)
                .setFrameClearDrawable(windowBackground)
                .setBlurAlgorithm(new SupportRenderScriptBlur(this))
                .setBlurRadius(radius)
                .setHasFixedTransformationMatrix(true);

        blurViewSelectTime.setupWith(root)
                .setFrameClearDrawable(windowBackground)
                .setBlurAlgorithm(new SupportRenderScriptBlur(this))
                .setBlurRadius(radius)
                .setHasFixedTransformationMatrix(true);

        blurViewVp.setupWith(root)
                .setFrameClearDrawable(windowBackground)
                .setBlurAlgorithm(new SupportRenderScriptBlur(this))
                .setBlurRadius(radius)
                .setHasFixedTransformationMatrix(true);
        blurViewTb.setupWith(root)
                .setFrameClearDrawable(windowBackground)
                .setBlurAlgorithm(new SupportRenderScriptBlur(this))
                .setBlurRadius(radius)
                .setHasFixedTransformationMatrix(true);


    }


    private void initActionView() {
        timeSelectTv = findViewById(R.id.hotel_tv_time_select);
        timeShowRv = findViewById(R.id.hotel_rv_show_time);
        timeOkBtn = findViewById(R.id.hotel_bv_time_ok);

        timeSelectTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeOkBtn.setVisibility(View.VISIBLE);
            }
        });

        findViewById(R.id.hotel_btn_time_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeOkBtn.setVisibility(View.GONE);
                timeShowRv.setVisibility(View.VISIBLE);
                selectHomeLy.setVisibility(View.VISIBLE);
            }
        });

        timeShowRv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeSelectTv.setVisibility(View.VISIBLE);
                timeOkBtn.setVisibility(View.VISIBLE);
                selectHomeLy.setVisibility(View.GONE);
            }
        });

        homeViewPager = findViewById(R.id.hotel_vp_home);
        homeTableLayout = findViewById(R.id.hotel_tab);
        selectHomeLy = findViewById(R.id.hotel_lv_select_home);

    }

    private void initMapView(Bundle savedInstanceState) {
         mMapView = (MapView) findViewById(R.id.hotel_map);
        mMapView.onCreate(savedInstanceState);// 此方法必须重写
        AMap aMap = mMapView.getMap();

        aMap.setTrafficEnabled(true);// 显示实时交通状况
        //地图模式可选类型：MAP_TYPE_NORMAL,MAP_TYPE_SATELLITE,MAP_TYPE_NIGHT
        aMap.setMapType(AMap.MAP_TYPE_NORMAL);// 卫星地图模式
        MyLocationStyle myLocationStyle;
        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType
        // (MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);
        // 连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        myLocationStyle.interval(2000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_FOLLOW);
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        aMap.getUiSettings().setMyLocationButtonEnabled(true);//设置默认定位按钮是否显示，非必需设置。
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false
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

    private void initHomeAdapter(){
        List<View> views = new ArrayList<>();

        View view1 =
                LayoutInflater.from( mViewPager.getContext()).inflate(R.layout.layout_select_home, null);

        TextView timePriceTv1 = view1.findViewById(R.id.hotel_tv_time_price);
        timePriceTv1.setText("¥ 137");
        TextView nightPriceTv1 = view1.findViewById(R.id.hotel_tv_night_price);
        nightPriceTv1.setText("¥ 334");
        view1.findViewById(R.id.hotel_btn_time_order).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

        view1.findViewById(R.id.hotel_btn_night_order).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

        views.add(view1);

        View view2 = LayoutInflater.from( mViewPager.getContext()).inflate(R.layout.layout_select_home, null);
        TextView timePriceTv2= view2.findViewById(R.id.hotel_tv_time_price);
        timePriceTv2.setText("¥ 237");
        TextView nightPriceTv2 = view2.findViewById(R.id.hotel_tv_night_price);
        nightPriceTv2.setText("¥ 434");
        view2.findViewById(R.id.hotel_btn_time_order).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

        view2.findViewById(R.id.hotel_btn_night_order).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

        views.add(view2);

        View view3 = LayoutInflater.from( mViewPager.getContext()).inflate(R.layout.layout_select_home, null);
        TextView timePriceTv3= view3.findViewById(R.id.hotel_tv_time_price);
        timePriceTv3.setText("¥ 337");
        TextView nightPriceTv3 = view3.findViewById(R.id.hotel_tv_night_price);
        nightPriceTv3.setText("¥ 534");
        view3.findViewById(R.id.hotel_btn_time_order).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

        view3.findViewById(R.id.hotel_btn_night_order).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

        views.add(view3);



        HomeAdapter homeAdapter = new HomeAdapter(HotelActivity.this, views);
        homeViewPager.setAdapter(homeAdapter);

        homeTableLayout.setupWithViewPager(homeViewPager);


    }

    private void showDialog() {
        new AlertDialog.Builder(HotelActivity.this)
                .setTitle("提示")
                .setMessage("预定成功")
                .setCancelable(true)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
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
            case R.id.hotel_iv_close:
                finish();
                break;
        }
    }
}
