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
        initialiseDescriptionText();
    }

    private void initialiseDescriptionText() {
        descriptionText.setText(listing.description());
    }
}
