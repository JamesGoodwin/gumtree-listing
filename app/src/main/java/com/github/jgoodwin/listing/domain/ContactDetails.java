package com.github.jgoodwin.listing.domain;

import android.net.Uri;

import java.io.Serializable;

public class ContactDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String telephone;
    private final String sms;
    private final String email;

    public ContactDetails(String telephone, String sms, String email) {
        this.telephone = telephone;
        this.sms = sms;
        this.email = email;
    }

    public String telephone() {
        return telephone;
    }
    
    public Uri callTelephoneUri() {
        return Uri.parse("tel:" + telephone());
    }

    public String sms() {
        return sms;
    }
    
    public Uri sendSmsUri() {
        return Uri.parse("smsto:" + telephone());
    }

    public String email() {
        return email;
    }

    public Uri sendEmailUri() {
        return Uri.parse("mailto:" + email());
    }
    
    public boolean hasEmail() {
        return email != null && email.length() > 0;
    }
    
    public boolean hasSms() {
        return sms != null && sms.length() > 0;
    }
    
    public boolean hasTelephone() {
        return telephone != null && telephone.length() > 0;
    }

}
