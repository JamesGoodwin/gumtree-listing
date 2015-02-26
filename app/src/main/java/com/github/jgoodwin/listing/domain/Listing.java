package com.github.jgoodwin.listing.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Listing implements Serializable {

    private static final long serialVersionUID = 1L;

    private final long id;
    private final String url;
    private final String title;
    private final int price;
    private final String description;
    private final String location;
    private final ArrayList<String> images;
    private final Date datePosted;
    private final User user;
    private final ContactDetails contactDetails;

    public Listing(long id,
                   String url,
                   String title,
                   int price,
                   String description,
                   String location,
                   ArrayList<String> images,
                   Date datePosted,
                   User listingPoster,
                   ContactDetails contactDetails) {
        this.id = id;
        this.url = url;
        this.title = title;
        this.price = price;
        this.description = description;
        this.location = location;
        this.images = images;
        this.datePosted = datePosted;
        this.user = listingPoster;
        this.contactDetails = contactDetails;
    }

    public String description() {
        return description;
    }
    
    public String shareContent() {
        return String.format("\n\n%s\n\n%s", url, shortenedDescription());
    }

    public String shortenedDescription() {
        return description.length() > 500 ? description.substring(0, 500) + "..." : description;
    }

    public ArrayList<String> images() {
        return images;
    }

    public ContactDetails contactDetails() {
        return contactDetails;
    }

    public String title() {
        return title;
    }

    public User listingPoster() {
        return user;
    }

    public String location() {
        return location;
    }

    public Integer price() {
        return price;
    }

    public Date datePosted() {
        return datePosted;
    }
}
