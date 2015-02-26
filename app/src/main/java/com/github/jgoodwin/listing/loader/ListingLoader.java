package com.github.jgoodwin.listing.loader;

import android.content.Context;
import android.content.Loader;

import com.github.jgoodwin.listing.domain.ContactDetails;
import com.github.jgoodwin.listing.domain.Listing;
import com.github.jgoodwin.listing.domain.PostingSince;
import com.github.jgoodwin.listing.domain.User;

import java.util.ArrayList;
import java.util.Date;

public class ListingLoader extends Loader<Listing> {

    public ListingLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        ArrayList<String> images = images();
        Listing listing = new Listing(12345L,
                "http://www.gumtree.com/p/other-outdoor-toys/feber-ferrari-f430-6v-battery-operated-ride-in-car-used-in-good-condion/1099393342",
                "Ferrari",
                125000,
                "Rosso Corsa, with Nero Leather Interior, Nero Dashboard and Carpets, Rosso Stitching, 20” Diamond Finish Alloy Wheels",
                "Knightsbridge",
                images, new Date(), listingUser(),
                contactDetails());
        
        deliverResult(listing);
    }

    private User listingUser() {
        return new User("John Smith", "6+ months");
    }

    private ArrayList<String> images() {
        ArrayList<String> images = new ArrayList<>();
        images.add("https://madwhips.s3.amazonaws.com/photo_1_rosso_mugello_ferrari_458_in_paris_5_101262_original.jpg");
        images.add("http://madwhips.s3.amazonaws.com/photo_1_rosso_mugello_ferrari_458_in_paris_4_101261_original.jpg");
        images.add("http://madwhips.s3.amazonaws.com/photo_1_rosso_mugello_ferrari_458_in_paris_6_101263_original.jpg");

        return images;
    }

    private ContactDetails contactDetails() {
        return new ContactDetails("02012345678",
                    "07701234567",
                    "item.forsale@domain.com");
    }
}
