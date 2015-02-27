package com.github.jgoodwin.listing.view;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.jgoodwin.listing.R;
import com.github.jgoodwin.listing.domain.Listing;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ListingDescriptionFragment extends Fragment {

    public static final String LISTING_KEY = "listing";
    
    private Listing listing;

    @InjectView(R.id.description_text)
    TextView descriptionText;

    @InjectView(R.id.price_text)
    TextView priceText;

    @InjectView(R.id.location_text)
    TextView locationText;
    
    @InjectView(R.id.ad_ref_text)
    TextView adRefText;
    
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listing_description, container, false);

        Bundle arguments = getArguments();
        if(arguments.containsKey(LISTING_KEY)) {
            this.listing = (Listing) arguments.getSerializable(LISTING_KEY);
        } else {
            throw new RuntimeException(String.format("%s requires %s argument",
                    ListingDescriptionFragment.class.getName(),
                    LISTING_KEY));
        }

        ButterKnife.inject(this, view);    
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialiseText();
    }

    private void initialiseText() {   
        descriptionText.setText(listing.description());
        
        locationText.setText(listing.location());
        
        adRefText.setText(String.format("Ad ref %d", listing.id()));
        
        priceText.setText("£" + listing.price());
    }
}
