package com.github.jgoodwin.listing.adapter;


import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.github.jgoodwin.listing.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ImagePagerAdapter extends PagerAdapter {

    private List<String> images;
    private LayoutInflater layoutInflater;

    public ImagePagerAdapter(LayoutInflater layoutInflater, List<String> images) {
        this.images = images;
        this.layoutInflater = layoutInflater;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = layoutInflater.inflate(R.layout.listing_image, null);

        final ImageView imageView = (ImageView) view.findViewById(R.id.image_view);

        if (position < images.size()) {
            String imageUrl = images.get(position);
            Picasso.with(imageView.getContext())
                    .load(imageUrl)
                    .noFade().
                    into(imageView);
        }
        
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
        unbindDrawables((View) object);
        object = null;
    }

    protected void unbindDrawables(View view) {
        if (view.getBackground() != null) {
            view.getBackground().setCallback(null);
        }
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                unbindDrawables(((ViewGroup) view).getChildAt(i));
            }
            ((ViewGroup) view).removeAllViews();
        }
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }


}
