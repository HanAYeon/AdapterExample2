package com.example.ccei.arrayadapterpj;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableWrapper;

/**
 * Created by ccei on 2016-07-08.
 */
public class TARAValueObject {
    public String memberName;
    public Drawable memeberImage;
    public int  likeCount;

    public TARAValueObject(String memberName, Drawable memeberImage,int likeCount) {
        this.memberName = memberName;
        this.memeberImage = memeberImage;
        this.likeCount = likeCount;
    }
}
