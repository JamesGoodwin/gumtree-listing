package com.github.jgoodwin.viewitem.domain;

import java.io.Serializable;

public class ItemContactDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String telephone;
    private final String sms;
    private final String email;

    public ItemContactDetails(String telephone, String sms, String email) {
        this.telephone = telephone;
        this.sms = sms;
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getSms() {
        return sms;
    }

    public String getEmail() {
        return email;
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
