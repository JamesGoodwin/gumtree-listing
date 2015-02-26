package com.github.jgoodwin.viewitem.view;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.github.jgoodwin.viewitem.R;
import com.github.jgoodwin.viewitem.domain.ItemContactDetails;

public class ViewItemActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_item);
        if (savedInstanceState == null) {
            createFragments();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_view_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    private void createFragments() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.content_container, new ImageGalleryFragment())
                .add(R.id.content_container, new ViewItemDescriptionFragment())
                .add(R.id.overlay_container, createContactButtonsFragment())
                .commit();
    }

    private ContactButtonsFragment createContactButtonsFragment() {
        ContactButtonsFragment contactButtonsFragment = new ContactButtonsFragment();
        Bundle arguments = new Bundle();

        ItemContactDetails itemContactDetails = new ItemContactDetails("02012345678",
                "07701234567",
                "item.forsale@domain.com");
        
        arguments.putSerializable(ContactButtonsFragment.CONTACT_DETAILS_KEY, itemContactDetails);
        contactButtonsFragment.setArguments(arguments);
        
        return contactButtonsFragment;
    }


}
