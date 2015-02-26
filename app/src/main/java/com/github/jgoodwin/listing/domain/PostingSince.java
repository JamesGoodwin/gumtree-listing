package com.github.jgoodwin.listing.domain;

import java.util.Calendar;
import java.util.Date;

public class PostingSince {

    private final Date date;

    public PostingSince(Date date) {
        this.date = date;
    }

    public boolean moreThanAMonthAgo() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        return date.compareTo(calendar.getTime()) > 0;
    }
    
    public boolean moreThanAYearAgo() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -1);
        return date.compareTo(calendar.getTime()) > 0;
    }

}
