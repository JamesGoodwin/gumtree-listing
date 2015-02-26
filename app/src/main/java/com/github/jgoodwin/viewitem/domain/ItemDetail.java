package com.github.jgoodwin.viewitem.domain;

import java.util.List;

public class ItemDetail {
    
    private final String title;
    private final String description;
    private final List<String> images;
    private final ItemContactDetails contactDetails;

    public ItemDetail(String title, String description,
                      List<String> images,
                      ItemContactDetails contactDetails) {
        this.title = title;
        this.description = description;
        this.images = images;
        this.contactDetails = contactDetails;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getImages() {
        return images;
    }

    public ItemContactDetails getContactDetails() {
        return contactDetails;
    }

    public String getTitle() {
        return title;
    }
}
