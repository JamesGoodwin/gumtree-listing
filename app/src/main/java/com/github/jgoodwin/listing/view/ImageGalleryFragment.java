package com.github.jgoodwin.listing.view;

import android.app.Fragment;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.github.jgoodwin.listing.R;
import com.github.jgoodwin.listing.adapter.ImagePagerAdapter;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ImageGalleryFragment extends Fragment {

    public static final String IMAGES_KEY = "images";
    private List<String> images;

    @InjectView(R.id.image_gallery_pager)
    ViewPager imageGallery;
    
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image_gallery, container, false);

        Bundle arguments = getArguments();
        if(arguments.containsKey(IMAGES_KEY)) {
            this.images = (List<String>) arguments.getSerializable(IMAGES_KEY);    
        } else {
            throw new RuntimeException(String.format("%s requires %s argument",
                    ImageGalleryFragment.class.getName(),
                    IMAGES_KEY));
        }

        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialiseImageGallery();
    }

    private void initialiseImageGallery() {
        Resources resources = getActivity().getResources();
        int displayWidth = getDisplayPortraitWidth(resources);

        double maxGalleryImageWidth = convertDpToPixels(resources, 700);
        int scaledWidth = (int) Math.round(Math.min(displayWidth, maxGalleryImageWidth) / 1.2);
        int scaledHeight = getHeight(scaledWidth);
        
        imageGallery.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, scaledHeight));
        imageGallery.setAdapter(new ImagePagerAdapter(LayoutInflater.from(getActivity()), images));
    }

    public static int convertDpToPixels(Resources resources, int dpValue){
        final float scale = resources.getDisplayMetrics().density;
        return (int) Math.floor((dpValue * scale + 0.5f));
    }

    private int getDisplayPortraitWidth(Resources resources) {
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        return Math.min(displayMetrics.widthPixels, displayMetrics.heightPixels); //Minimal value of the display metrics is the portrait width (taking into account orientation)
    }

    public int getHeight(int width) {
        if (width > 0) {
            return (width / 6) * 5;
        }

        return 0;
    }

    public int getWidth(int height) {
        if (height > 0) {
            return (height / 5) * 6;
        }

        return 0;
    }

}
