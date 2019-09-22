package com.example.contactv1;

import java.io.Serializable;

public class Contact implements Serializable {
    private String mName;
    private String mMobile;
    private String mAvatar;


    public Contact(String mName, String mMobile, String mAvatar) {
        this.mName = mName;
        this.mMobile = mMobile;
        this.mAvatar = mAvatar;
    }

    public Contact() {
        this.mName = "Nguyễn Văn Đại";
        this.mMobile = "0123456789";
    }


    public String getmName() {
        return mName;
    }

    public String getmMobile() {
        return mMobile;
    }

    public String getmAvatar() {
        return mAvatar;
    }
}
