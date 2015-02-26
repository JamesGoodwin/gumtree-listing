package com.github.jgoodwin.listing.view;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.github.jgoodwin.listing.R;
import com.github.jgoodwin.listing.domain.ContactDetails;
import com.github.jgoodwin.listing.domain.Listing;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class ContactButtonsFragment extends Fragment {

    public static final String LISTING_KEY = "listing";
    
    private Listing listing;
    
    @InjectView(R.id.email_button)
    Button emailButton;
    
    @InjectView(R.id.sms_button)
    Button smsButton;
    
    @InjectView(R.id.call_button)
    Button callButton;
    
    @InjectView(R.id.posting_user)
    TextView postingUserText;
    
    @InjectView(R.id.posting_since)
    TextView postingSinceText;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact_buttons, container, false);

        Bundle arguments = getArguments();

        if(arguments.containsKey(LISTING_KEY)) {
            listing = (Listing) arguments.getSerializable(LISTING_KEY);
        } else {
            throw new RuntimeException(String.format("%s requires %s argument",
                    ContactButtonsFragment.class.getName(),
                    LISTING_KEY));
        }

        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialiseButtons();
        initialisePostingUserText();
        initialisePostingSinceText();
    }

    private void initialisePostingSinceText() {
        postingSinceText.setText(listing.listingPoster().postingSince());
    }

    private void initialisePostingUserText() {
        postingUserText.setText(listing.listingPoster().name());
    }

    @OnClick(R.id.call_button)
    void callTelephone() {
        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(listing.contactDetails().callTelephoneUri());
        ContactButtonsFragment.this.startActivity(callIntent);
    }

    @OnClick(R.id.sms_button)
    void sendSmsMessage() {
        Intent smsIntent = new Intent(Intent.ACTION_SENDTO, listing.contactDetails().sendSmsUri());
        startActivity(smsIntent);  
    }

    @OnClick(R.id.email_button)
    void sendEmail() {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, listing.contactDetails().sendEmailUri());
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Item for sale on Gumtree");

        startActivity(Intent.createChooser(emailIntent, "Choose email client"));
    }

    private void initialiseButtons() {
        if(listing.contactDetails().hasEmail()) {
            initialiseEmailButton();
        }
        
        if(listing.contactDetails().hasSms()) {
            initialiseSmsButton();
        }
        
        if(listing.contactDetails().hasTelephone()) {
            initialiseCallButton();
        }
    }

    private void initialiseCallButton() {
        callButton.setVisibility(View.VISIBLE);
    }

    private void initialiseSmsButton() {
        smsButton.setVisibility(View.VISIBLE);
    }

    private void initialiseEmailButton() {
        emailButton.setVisibility(View.VISIBLE);
    }
}
