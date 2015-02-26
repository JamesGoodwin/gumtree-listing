package com.github.jgoodwin.listing.view;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.ShareActionProvider;
import android.view.Menu;
import android.view.MenuItem;

import com.github.jgoodwin.listing.R;
import com.github.jgoodwin.listing.domain.Listing;
import com.github.jgoodwin.listing.loader.ListingLoader;

import butterknife.ButterKnife;

public class ListingDescriptionActivity extends ActionBarActivity implements LoaderManager.LoaderCallbacks<Listing> {

    private Listing listing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_listing_description);

        getLoaderManager().initLoader(ListingDescriptionActivity.class.hashCode(), null, this);

        ButterKnife.inject(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_listing_description, menu);

        MenuItem shareItem = menu.findItem(R.id.action_share);
        ShareActionProvider shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(shareItem);
        setShareIntent(shareActionProvider);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void createFragments() {
        getFragmentManager().beginTransaction()
                .replace(R.id.gallery_container, createImageGalleryFragment())
                .replace(R.id.content_container, createItemDescriptionFragment())
                .replace(R.id.contact_buttons_container, createContactButtonsFragment())
                .commit();
    }

    private ListingDescriptionFragment createItemDescriptionFragment() {
        ListingDescriptionFragment descriptionTextFragment = new ListingDescriptionFragment();
        Bundle arguments = new Bundle();
        arguments.putSerializable(ListingDescriptionFragment.LISTING_KEY, listing);

        descriptionTextFragment.setArguments(arguments);
        return descriptionTextFragment;
    }

    private ImageGalleryFragment createImageGalleryFragment() {
        ImageGalleryFragment galleryFragment = new ImageGalleryFragment();
        Bundle arguments = new Bundle();

        arguments.putSerializable(ImageGalleryFragment.IMAGES_KEY, listing.images());

        galleryFragment.setArguments(arguments);
        return galleryFragment;
    }

    private ContactButtonsFragment createContactButtonsFragment() {
        ContactButtonsFragment contactButtonsFragment = new ContactButtonsFragment();
        Bundle arguments = new Bundle();

        arguments.putSerializable(ContactButtonsFragment.LISTING_KEY, listing);

        contactButtonsFragment.setArguments(arguments);

        return contactButtonsFragment;
    }

    private void setShareIntent(ShareActionProvider shareActionProvider) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("plain/text");
        intent.putExtra(Intent.EXTRA_TEXT, listing.shareContent());
        shareActionProvider.setShareIntent(intent);
    }

    @Override
    public Loader<Listing> onCreateLoader(int id, Bundle args) {
        return new ListingLoader(this);
    }

    @Override
    public void onLoadFinished(Loader<Listing> loader, final Listing listing) {
        this.listing = listing;
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                setTitle(listing.title());
                createFragments();
            }
        });
    }

    @Override
    public void onLoaderReset(Loader<Listing> loader) {

    }
}
