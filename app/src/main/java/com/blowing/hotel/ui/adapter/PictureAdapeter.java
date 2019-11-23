package com.blowing.hotel.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by wujie
 * on 2019/11/23/023.
 */
public class PictureAdapeter extends PagerAdapter {

    private Context context;
    private List<ImageView> imageIds ;

    public PictureAdapeter(Context context, List<ImageView> imageIds) {
        this.context = context;
        this.imageIds = imageIds;
    }

    @Override
    public int getCount() {
        return imageIds.size();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(imageIds.get(position));
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
       ImageView imageView = imageIds.get(position);
        ViewGroup parentViewGroup = (ViewGroup) imageView.getParent();
        if (parentViewGroup != null ) {
            parentViewGroup.removeView(imageView);
        }
        container.addView(imageView);

        return imageView;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }
}
