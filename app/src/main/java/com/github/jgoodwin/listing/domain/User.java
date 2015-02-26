package com.github.jgoodwin.listing.domain;

import java.io.Serializable;

public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String name;
    private final String postingSince;

    public User(String name, String postingSince) {
        this.name = name;
        this.postingSince = postingSince;
    }

    public String name() {
        return name;
    }

    public String postingSince() {
        return postingSince;
    }
}
