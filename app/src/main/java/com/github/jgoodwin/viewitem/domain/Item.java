package com.github.jgoodwin.viewitem.domain;

import java.util.List;

public class Item {
    
    private final String description;
    private final List<String> images;

    public Item(String description, List<String> images) {
        this.description = description;
        this.images = images;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getImages() {
        return images;
    }
}
